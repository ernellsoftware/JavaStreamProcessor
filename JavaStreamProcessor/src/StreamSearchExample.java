import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import se.ernell.java.streamprocessor.Score;
import se.ernell.java.streamprocessor.Sync;
import se.ernell.java.streamprocessor.comparators.ScoreDescendingComparator;
import se.ernell.java.streamprocessor.config.ProcessorConfiguration;
import se.ernell.java.streamprocessor.filters.FilterBuild;
import se.ernell.java.streamprocessor.filters.FilterLength;
import se.ernell.java.streamprocessor.filters.IFilter;
import se.ernell.java.streamprocessor.io.IStreamObject;
import se.ernell.java.streamprocessor.objects.Line;
import se.ernell.java.streamprocessor.processor.BaseProcessor;
import se.ernell.java.streamprocessor.processor.FilterProcessor;
import se.ernell.java.streamprocessor.processor.IProcessor;
import se.ernell.java.streamprocessor.processor.Logic;
import se.ernell.java.time.SimpleTimer;

/**
 * Run an example of the StreamProcessor
 * 
 * @author rob@ernell.se
 */
public class StreamSearchExample {

    private static final String TEXT_TESTFILE1 = "twl.utf8.txt";
    private static final String TEXT_TESTFILE2 = "csw.utf8.txt";

    private static ArrayList<IStreamObject> myList;
    private static Comparator<IStreamObject> comparator;
    private static long totalTime = 0;

    public StreamSearchExample() {
	run();
    }

    private void run() {

	Sync.processing_done = false;

	myList = new ArrayList<IStreamObject>(512);

	FilterProcessor.resetCounter();

	try {

	    // file objects
	    File[] files = new File[2];
	    files[0] = new File(TEXT_TESTFILE1);
	    files[1] = new File(TEXT_TESTFILE2);

	    // create url's of the fileobjects
	    URL[] urls = new URL[2];
	    urls[0] = files[0].toURI().toURL();
	    urls[1] = files[1].toURI().toURL();

	    // filter 1
	    IFilter filter1 = new FilterLength(3, 4);
	    filter1.setLogic(Logic.AND);
	    // filter 2
	    String the_word = "JASER??????????";
	    IFilter filter2 = new FilterBuild(the_word, the_word.length());
	    filter2.setLogic(Logic.NOT);

	    // File 1 Processor
	    ProcessorConfiguration ipc0 = new ProcessorConfiguration(urls[0]);
	    ipc0.min_length = 0;
	    ipc0.max_length = 16;
	    ipc0.buffer_size = 16;// max length of lines in this file
	    ipc0.unique = false;
	    ipc0.setGame(Score.Game.ENGLISH_WORDFEUD);
	    // ipc0.setGame(Score.Game.ENGLISH_SCRABBLE);

	    // File 2 Processor
	    ProcessorConfiguration ipc1 = new ProcessorConfiguration(urls[1]);
	    ipc1.min_length = 0;
	    ipc1.max_length = 16;
	    ipc1.buffer_size = 16;// max length of lines in this file
	    ipc1.unique = true;
	    ipc1.setGame(Score.Game.ENGLISH_WORDFEUD);
	    // ipc1.setGame(Score.Game.ENGLISH_SCRABBLE);

	    // processor
	    IProcessor[] cpus = new FilterProcessor[2];

	    cpus[0] = new FilterProcessor(ipc0, myList);
	    cpus[0].addFilter(filter1);
	    cpus[0].addFilter(filter2);

	    cpus[1] = new FilterProcessor(ipc1, myList);
	    cpus[1].addFilter(filter1);
	    cpus[1].addFilter(filter2);

	    startStreamProcessorThread(cpus, cpus.length
		    + " files read sequentially.");

	    // Comparator<IStreamObject> comp = new AlphaDescendingComparator();
	    comparator = new ScoreDescendingComparator();
	    startSortingThread();

	    // wait for thread to finish
	    while (!Sync.processing_done) {
		pause(50);
	    }

	} catch (MalformedURLException e) {
	    System.out.println("MalformedURLException: " + e.getMessage());
	    e.printStackTrace();
	} catch (Exception e) {
	    System.out.println("Exception: " + e.getMessage());
	    e.printStackTrace();
	}

	pause(200);

	// Do something with the result (arraylist)
	try {
	    // null-check
	    if (BaseProcessor.arraylist != null) {
		int size = FilterProcessor.arraylist.size();
		// zero-size-check
		if (size > 0) {
		    for (int i = 0; i < size; i++) {
			System.out
				.println("["
					+ i
					+ "] = '"
					+ ((Line) BaseProcessor.arraylist
						.get(i)).line
					+ "' "
					+ ((Line) BaseProcessor.arraylist
						.get(i)).score);
			System.out.println("[" + i + "] = '"
				+ ((Line) myList.get(i)).line + "' "
				+ ((Line) myList.get(i)).score);
		    }
		}
	    }
	} catch (NullPointerException e) {
	    System.out.println("NullPointerException: " + e.getMessage());
	    e.printStackTrace();
	}

	System.out.println("LineCounter = " + FilterProcessor.getCount());
	System.out.println("Total Time  = " + totalTime + "ms");

    }

    /** Process url's sequentially */
    public void startStreamProcessorThread(final IProcessor[] cpus,
	    final String name) {

	new Thread(new Runnable() {
	    @Override
	    public void run() {
		SimpleTimer time = new SimpleTimer();
		time.start();
		for (int i = 0; i < cpus.length; i++) {
		    if (!cpus[i].start()) {
			System.out.println("Processor Thread " + i + " [FAIL]");
		    } else {
			System.out.println("Processor Thread " + i + " [OK]");
		    }
		}
		time.stop(name);
		totalTime += time.getTime();
		Sync.processing_done = true;
	    }
	}).start();

    }

    /** Start the Sorting mechanism as a thread */
    public void startSortingThread() {

	new Thread(new Runnable() {
	    @Override
	    public void run() {

		while (!Sync.processing_done) {
		    pause(50);
		    synchronized (Sync.lock) {
			if (myList.size() > 0) {
			    Collections.sort(myList, comparator);
			}
		    }
		}

		pause(100);
		System.out.println("Sorting finished");

	    }
	}).start();

    }

    /**
     * Start the Sorting mechanism as a thread. Comparator as argument
     */
    public void startSortingThread(
	    final Comparator<IStreamObject> arg_comparator) {

	new Thread(new Runnable() {
	    @Override
	    public void run() {
		/** run as long as processing is running */
		while (!Sync.processing_done) {
		    pause(50);
		    synchronized (Sync.lock) {
			if (myList.size() > 0) {
			    Collections.sort(myList, arg_comparator);
			}
		    }
		}

		/** do a last sort just to make sure */
		pause(50);
		synchronized (Sync.lock) {
		    if (myList.size() > 0) {
			Collections.sort(myList, arg_comparator);
		    }
		}
		System.out.println("Sorting finished");

	    }
	}).start();

    }

    public void pause(int len) {
	try {
	    Thread.sleep(len);
	} catch (InterruptedException e) {

	}
    }

}
