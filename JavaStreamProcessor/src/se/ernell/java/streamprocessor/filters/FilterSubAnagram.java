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

/**
 * <p>
 * <b>Filterclass: FilterSubAnagram</b><br>
 * Check if a line is an anagram or subanagram of the given pattern.<br>
 * - An Anagram is always same length as the pattern.<br>
 * - A Subanagram is always shorter than the pattern.<br>
 * - A 'Build' is the Anagrams + Subanagrams (same length or less than pattern)<br>
 * </p>
 * 
 * @see se.ernell.java.streamprocessor.filters.FilterAnagram
 * @see se.ernell.java.streamprocessor.filters.FilterSubAnagram
 * @see se.ernell.java.streamprocessor.filters.FilterBuild
 * @author Robert Andersson <rob@ernell.se> <http://www.ernell.se>
 */
public class FilterSubAnagram extends BaseFilterAnagram {

    public FilterSubAnagram(String arg_pattern, int arg_length) {
	super(arg_pattern, arg_length);
    }

    @Override
    public boolean match(char[] line, int line_length) {

	if (line_length < pattern_length) {
	    return super.match(line, line_length);
	}
	return false;

    }

}
