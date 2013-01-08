package se.ernell.java.streamprocessor.utils;

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
 * Text processing utility class.<br>
 * All kinds of text processing functions optimized for speed.<br>
 * 
 * @author Robert Andersson <rob@ernell.se> <http://www.ernell.se>
 */
@SuppressWarnings("unused")
public class FilterUtilities {

    /** check if the char[] contains at least one of the 'target_char' char */
    public final static boolean containsChar(char[] line, char target_char) {
	char c;
	int len = line.length;
	for (int i = 0; i < len; i++) {
	    c = line[i];
	    if (c == target_char)
		return true;
	}
	return false;
    }

    /** count occurences of 'target_char' char */
    public final static int countChar(char[] line, char target_char) {
	char c;
	int len = line.length;
	int count = 0;
	for (int i = 0; i < len; i++) {
	    c = line[i];
	    if (c == target_char)
		count++;
	}
	return count;
    }

    /** count occurences of 'target_char' char */
    public final static int countChar(String line, char target_char) {
	char c;
	int count = 0;
	int len = line.length();
	for (int i = 0; i < len; i++) {
	    c = line.charAt(i);
	    if (c == target_char)
		count++;
	}
	return count;
    }

    /** remove all unwanted characters */
    public final static String strip(String line, String arg_alphabet) {

	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < line.length(); i++)
	    if (arg_alphabet.indexOf(line.charAt(i)) >= 0)
		sb.append(line.charAt(i));

	// Shrink all double asterix '*' down to one
	for (int i = sb.length(); i > 0; i--)
	    replaceAll(sb, "**", "*");
	return sb.toString();

    }

    /** replace all occurences of 'from' -> 'to' in the StringBuilder */
    public final static void replaceAll(StringBuilder builder, String from,
	    String to) {
	int index = builder.indexOf(from);
	while (index != -1) {
	    builder.replace(index, index + from.length(), to);
	    // Move to the end of the replacement
	    index += to.length();
	    index = builder.indexOf(from, index);
	}
    }

}
