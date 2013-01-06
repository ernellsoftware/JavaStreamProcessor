package se.ernell.java.streamprocessor.processor;

/*
 * Copyright (C) 2012 Robert Andersson <http://www.ernell.se>
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
import java.util.ArrayList;

import se.ernell.java.streamprocessor.config.IProcessorConfiguration;
import se.ernell.java.streamprocessor.filters.IFilter;
import se.ernell.java.streamprocessor.io.IStreamObject;

/**
 * Extended Processor class with filtering option
 * 
 * @see se.ernell.java.streamprocessor.processor.IProcessor
 * @see se.ernell.java.streamprocessor.processor.BaseProcessor
 * @author Robert Andersson <rob@ernell.se> <http://www.ernell.se>
 */
public final class FilterProcessor extends BaseProcessor {

    /** list of filters */
    protected ArrayList<IFilter> filters;

    /** size of list of filters */
    protected int filters_count;

    /** Constructor */
    public FilterProcessor(IProcessorConfiguration ipc,
	    ArrayList<IStreamObject> arg_list) {
	super(ipc, arg_list);

	// init filter array and set the size
	filters = new ArrayList<IFilter>();
	filters_count = filters.size();

    }

    /** add a filter to the filterlist */
    @Override
    public void addFilter(IFilter arg_filter) {
	filters.add(arg_filter);
	filters_count = filters.size();
    }

    /** @inheritDoc */
    @Override
    public void process(char[] line, int line_length) {

	boolean match = false;

	// Process with filter
	if (filters_count == 1 && filters.get(0).match(line, line_length)) {

	    match = true;

	} else if (filters_count > 1) {

	    match = true;
	    for (int i = 0; i < filters_count; i++) {
		if (!filters.get(i).match(line, line_length)) {
		    match = false;
		}
	    }

	}

	// If there where a match, then proceed with adding to the list
	if (match) {
	    super.process(line, line_length);
	}

    }

}
