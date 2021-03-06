package se.ernell.java.streamprocessor.processor;

/*
 * Copyright (C) 2013 Robert Andersson <http://www.ernell.se>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import se.ernell.java.streamprocessor.Score;
import se.ernell.java.streamprocessor.Sync;
import se.ernell.java.streamprocessor.config.IProcessorConfiguration;
import se.ernell.java.streamprocessor.filters.IFilter;
import se.ernell.java.streamprocessor.io.IStreamObject;
import se.ernell.java.streamprocessor.io.InputStreamReader;
import se.ernell.java.streamprocessor.objects.Line;

/**
 * Processor base class
 * 
 * @see se.ernell.java.streamprocessor.processor.IProcessor
 * @see se.ernell.java.streamprocessor.processor.FilterProcessor
 * @author Robert Andersson <rob@ernell.se> <http://www.ernell.se>
 */
public abstract class BaseProcessor implements IProcessor,
	IStreamObjectRecievable {

    /** configuration object */
    protected IProcessorConfiguration ipc;

    /** The array to hold objects */
    public static ArrayList<IStreamObject> arraylist;

    /** keep count of processed lines */
    protected static int line_counter = 0;

    /** Constructor */
    public BaseProcessor(IProcessorConfiguration arg_ipc,
	    ArrayList<IStreamObject> arg_list) {

	ipc = arg_ipc;
	arraylist = arg_list;

	// error check
	if (ipc == null)
	    System.out.println("ERROR! ipc==null");
	if (arraylist == null)
	    System.out.println("ERROR! arrayList==null");

    }

    /** @inheritDoc */
    @Override
    public abstract void addFilter(IFilter arg_filter);

    /** @inheritDoc */
    @Override
    public void process(char[] line, int line_length) {
	synchronized (Sync.lock) {
	    arraylist.add(Line.create(line, Score.getScore(line, line_length),
		    line_length, false));
	    line_counter++;
	}
    }

    /**
     * Start reading from the URL(s) Here 'this' is sent to the reader and this
     * class implements the IStreamObjectRecievable interface
     */
    @Override
    public boolean start() {
	try {
	    // open stream from URL stored in the IProcessorConfiguration object
	    final InputStream is = ipc.getStreamURL().openStream();
	    final InputStreamReader isr = new InputStreamReader(this);
	    isr.setRestrictedMinLength(ipc.getMinLength());
	    isr.setRestrictedMaxLength(ipc.getMaxLength());
	    InputStreamReader.readLinesFromStream(is, ipc.getCharBufferSize(),
		    ipc.getBufferSize());
	    is.close();
	    return true;
	} catch (IOException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    /**
     * Reset the static counter
     */
    public static void resetCounter() {
	line_counter = 0;
    }

    /**
     * Get the line count
     */
    public static int getCount() {
	return line_counter;
    }

    // is this needed???
    public static void pause(int len) {
	try {
	    Thread.sleep(len);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

}
