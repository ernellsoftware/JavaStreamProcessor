package se.ernell.java.streamprocessor.filters;

import se.ernell.java.streamprocessor.processor.Logic;

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

/**
 * <p>
 * <b>Filterclass: FilterLength</b><br>
 * Check if a line is within certain length range.<br>
 * </p>
 * 
 * @author Robert Andersson <rob@ernell.se> <http://www.ernell.se>
 */
public class FilterLength implements IFilter {

    private int min;
    private int max;

    /** the logic to use for this filter together with the following filter */
    protected Logic logic;

    public FilterLength(int arg_min_length, int arg_max_length) {
	min = arg_min_length;
	max = arg_max_length;
    }

    @Override
    public boolean match(char[] line, int line_length) {

	if (line_length >= min && line_length <= max) {
	    return true;
	}
	return false;

    }

    @Override
    public void setLogic(Logic arg_logic) {
	logic = arg_logic;
    }

    @Override
    public Logic getLogic() {
	return logic;
    }

}
