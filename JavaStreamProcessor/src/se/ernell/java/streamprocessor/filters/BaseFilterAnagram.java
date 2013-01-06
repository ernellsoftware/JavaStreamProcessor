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
 * <b>Filterclass: BaseAnagram</b><br>
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
public class BaseFilterAnagram extends BaseFilter {

    /** Constructor */
    public BaseFilterAnagram(String arg_pattern, int arg_length) {
	super(arg_pattern, arg_length);

    }

    /** @inheritDoc */
    @Override
    public boolean match(char[] line, int line_length) {

	// check if line is an anagram
	if (isAnagram(line, line_length, char_pattern, blanks))
	    return true;
	// no match
	return false;

    }

    /**
     * Anagram/Subanagram checker for char[] For wordgames like Scrabble &
     * Wordfeud. Find out if the dictionary word is writable with the supplied
     * letters + blanks
     * 
     * @author rob@ernell.se
     * 
     * @param char[] dictionaryWord : The word from a dictionary to compare to
     * @param int dwlen : The length of the dictionary word
     * @param char[] letters : array containing the letters in the rack
     * @param int blanks : the number of blanks in the rack
     */
    public final static boolean isAnagram(char[] dictionaryWord, int dwlen,
	    char[] letters, int blanks) {

	// If the dictionary word is shorter than the number of blanks = match
	if (dwlen <= blanks)
	    return true;
	// If dictionaryword length > letters total length = no match
	int len = letters.length;
	int wlen = len + blanks;
	if (dwlen > wlen)
	    return false;

	int i = 0;// for loops
	int j = 0;// for loops
	char d; // char from dictionary word
	char l; // char from letters

	// make a copy of the dictionary word
	char[] dict = new char[dwlen];
	System.arraycopy(dictionaryWord, 0, dict, 0, dwlen);

	// tlen : letters remaining in dict[]
	int tlen = dwlen;// set initial length to tlen

	// remove the number of blanks from length (could be negative value)
	tlen -= blanks;

	// Subtract letters to find anagram (tlen <= 0)
	for (i = 0; i < len; i++) { // letters[i]

	    // match. all letters from dictword are matched
	    if (tlen <= 0)
		return true;

	    dictloop:

	    for (j = 0; j < dwlen; j++) { // dict[i]
		// pick char from letters
		l = letters[i];
		// pick char from dictionary word
		d = dict[j];
		if (l == d) {
		    // 'clear' the found character with a space character
		    dict[j] = '\u0020';
		    // one less character in dict[0]
		    tlen--;
		    // quit this loop
		    break dictloop;
		}

	    }

	}

	if (tlen == 0)
	    return true;

	if (blanks == 0)
	    return false;

	return false;

    }

}
