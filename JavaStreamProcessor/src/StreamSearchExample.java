import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import se.ernell.java.streamprocessor.config.ProcessorConfiguration;
import se.ernell.java.streamprocessor.filters.FilterBuild;
import se.ernell.java.streamprocessor.filters.IFilter;
import se.ernell.java.streamprocessor.io.IStreamObject;
import se.ernell.java.streamprocessor.objects.Line;
import se.ernell.java.streamprocessor.processor.BaseProcessor;
import se.ernell.java.streamprocessor.processor.FilterProcessor;
import se.ernell.java.streamprocessor.processor.IProcessor;
import se.ernell.java.time.SimpleTimer;

/**
 * Run an example of the StreamProcessor
 * 
 * @author rob@ernell.se
 */
public class StreamSearchExample {

    private static ArrayList<IStreamObject> myList;
    private static final String TEXT_TESTFILE1 = "twl.utf8.txt";
    private static final String TEXT_TESTFILE2 = "csw.utf8.txt";
    private static long totalTime = 0;
    public static boolean done = false;

    public StreamSearchExample() {
	run();
    }

    private void run() {

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
	    String word1 = "ASSE";
	    IFilter filter1 = new FilterBuild(word1, word1.length());
	    // filter 2
	    // String word2 = "J";
	    // IFilter filter2 = new FilterContains(word2, word2.length());

	    // File 1 Processor
	    ProcessorConfiguration ipc0 = new ProcessorConfiguration(urls[0]);
	    ipc0.min_length = 0;
	    ipc0.max_length = 16;
	    ipc0.buffer_size = 16;// max length of lines in this file
	    ipc0.unique = false;

	    // File 2 Processor
	    ProcessorConfiguration ipc1 = new ProcessorConfiguration(urls[1]);
	    ipc1.min_length = 0;
	    ipc1.max_length = 16;
	    ipc1.buffer_size = 16;// max length of lines in this file
	    ipc1.unique = true;

	    // processor
	    IProcessor[] cpus = new FilterProcessor[2];

	    cpus[0] = new FilterProcessor(ipc0, myList);
	    cpus[0].addFilter(filter1);
	    // cpus[0].addFilter(filter2);

	    cpus[1] = new FilterProcessor(ipc1, myList);
	    cpus[1].addFilter(filter1);
	    // cpus[1].addFilter(filter2);

	    startStreamProcessorThread(cpus, cpus.length
		    + " files read sequentially.");

	    // wait for thread to finish
	    while (!done) {
		pause(25);
	    }
	    done = false;

	} catch (MalformedURLException e) {
	    System.out.println("MalformedURLException: " + e.getMessage());
	} catch (Exception e) {
	    System.out.println("Exception: " + e.getMessage());
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
		done = true;
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
