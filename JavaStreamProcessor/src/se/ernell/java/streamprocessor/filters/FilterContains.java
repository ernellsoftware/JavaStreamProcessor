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

public class FilterContains extends BaseFilter {

    private BNDMWildcardsCI bndm;
    private char[] bndm_pattern;
    private Object compiledpattern;

    public FilterContains(String arg_pattern, int arg_length) {
	super(arg_pattern, arg_length);

	// StringSearch object
	bndm = new BNDMWildcardsCI('?');
	bndm_pattern = new char[arg_length];
	for (int i = 0; i < arg_length; i++)
	    bndm_pattern[i] = pattern.charAt(i);
	compiledpattern = bndm.processChars(bndm_pattern);

    }

    @Override
    public boolean match(char[] line, int line_length) {
	int patternPos = bndm.searchChars(line, bndm_pattern, compiledpattern);
	int patternPosMax = line_length - pattern_length;
	if (patternPos >= 0 && isContained(patternPos, patternPosMax)) {
	    // match
	    return true;
	}
	// no match
	return false;
    }

    private final static boolean isContained(int patternPos, int patternPosMax) {
	return patternPos <= patternPosMax;
    }
}
