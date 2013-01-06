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
import se.ernell.java.Alpha;
import se.ernell.java.streamprocessor.utils.FilterUtilities;

/**
 * The Base of all extended Filter classes.<br>
 * Here we create all sorts of Strings and char[]<br>
 * to be used by extended classes as needed.
 * 
 * @see se.ernell.java.streamprocessor.filters.FilterUtilities
 * @author rob@ernell.se
 */
public abstract class BaseFilter implements IFilter {

    /** pattern to look for */
    protected String pattern;

    /** pattern to look for (as char[]) */
    protected char[] char_pattern;

    /** just the alpha's [a-z] as char[] */
    protected char[] alpha_only_char_pattern;

    /** just the alpha's [a-z] as String */
    protected String alpha_only_string_pattern;

    /** length of the pattern to look for */
    protected int pattern_length;

    /** number of blanks '?' */
    protected int blanks;

    /** number of wildcards '*' */
    protected int wildcards;

    /** Constructor */
    public BaseFilter(String arg_pattern, int arg_length) {

	// shrink all double asterix '**' down to one '*'
	// since its pointless to have doubles
	for (int i = arg_length; i > 0; i--)
	    arg_pattern = arg_pattern.replace("**", "*");

	pattern = arg_pattern;
	pattern_length = pattern.length();
	char_pattern = pattern.toCharArray();
	alpha_only_string_pattern = FilterUtilities.strip(pattern,
		Alpha.SET_UC_ALPHA);
	alpha_only_char_pattern = alpha_only_string_pattern.toCharArray();

	blanks = FilterUtilities.countChar(arg_pattern, '?');
	wildcards = FilterUtilities.countChar(arg_pattern, '*');

    }

    /** @inheritDoc */
    @Override
    public abstract boolean match(char[] line, int line_length);

}
