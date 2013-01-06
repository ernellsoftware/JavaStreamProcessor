package se.ernell.java.streamprocessor.filters;

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

import com.eaio.stringsearch.BNDMWildcardsCI;

/**
 * <p>
 * <b>Filterclass: Begins with</b><br>
 * Check if a line 'begins with' the given pattern.<br>
 * Use '?' as wildcard.<br>
 * This class is using the StringSearch-2 library by Johann Burkard.
 * </p>
 * <b>Example of patterns:</b>
 * 
 * <pre>
 * 	A
 * 	A?S
 * 	ABC?E?F
 * </pre>
 * 
 * @see se.ernell.java.streamprocessor.filters.IFilter
 * @see se.ernell.java.streamprocessor.filters.BaseFilter
 * @author Robert Andersson <rob@ernell.se> <http://www.ernell.se>
 */
public class FilterBeginsWith extends BaseFilter {

    /** StringSearch-2 object */
    private BNDMWildcardsCI bndm;

    /** StringSearch-2 pattern */
    private char[] bndm_pattern;

    /** StringSearch-2 compiled pattern */
    private Object compiledpattern;

    /** Constructor */
    public FilterBeginsWith(String arg_pattern, int arg_length) {
	super(arg_pattern, arg_length);

	// StringSearch object
	bndm = new BNDMWildcardsCI('?');
	bndm_pattern = new char[arg_length];
	for (int i = 0; i < arg_length; i++)
	    bndm_pattern[i] = pattern.charAt(i);
	compiledpattern = bndm.processChars(bndm_pattern);

    }

    /** @inheritDoc */
    @Override
    public boolean match(char[] line, int line_length) {
	int patternPos = bndm.searchChars(line, bndm_pattern, compiledpattern);
	// if match at position 0
	if (patternPos == 0)
	    return true;
	// no match
	return false;
    }

}
