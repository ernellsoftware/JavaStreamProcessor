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
 * Interface: IFilter<br>
 * Common behaviour of all filters.<br>
 * 
 * @author rob@ernell.se
 * 
 */
public interface IFilter {

    /** Matching function */
    public boolean match(char[] line, int line_length);

    /**
     * Set the logical function of a filter.<br>
     * When using logical NOT, it is valid for the filter it is set to.<br>
     * 
     * */
    public void setLogic(Logic arg_logic);

    /** get logic operator for this filter */
    public Logic getLogic();

}
