package se.ernell.java.streamprocessor.processor;

import se.ernell.java.streamprocessor.filters.IFilter;

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
 * Interface defining a Processor
 * 
 * @see se.ernell.java.streamprocessor.processor.BaseProcessor
 * @see se.ernell.java.streamprocessor.processor.FilterProcessor
 * @author Robert Andersson <rob@ernell.se> <http://www.ernell.se>
 */
public interface IProcessor {

    /** method to add a custom filter. used by extended classes */
    public void addFilter(IFilter arg_filter);

    public void start();

}
