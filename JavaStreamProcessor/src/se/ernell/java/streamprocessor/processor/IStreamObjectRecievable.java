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

/**
 * Interface making a class a reciever of IStreamObject's
 * 
 * <pre>
 *   Implement this interface to your class. Pass the object when 
 *   calling the reader. The object's process(...) method will be 
 *   called from the reader for each new line found.
 * </pre>
 * 
 * @see se.ernell.java.streamprocessor.processor.BaseProcessor
 * @author Robert Andersson <rob@ernell.se> <http://www.ernell.se>
 */
public interface IStreamObjectRecievable {

    /**
     * Method that recieves data from an InputStreamReader
     * 
     * @param line
     *            char-data (probably a word)
     * @param line_length
     *            length of char-data
     */
    public void process(char[] line, int line_length);

}
