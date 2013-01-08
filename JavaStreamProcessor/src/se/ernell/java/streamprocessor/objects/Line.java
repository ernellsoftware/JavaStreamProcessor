package se.ernell.java.streamprocessor.objects;

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

import se.ernell.java.streamprocessor.io.IStreamObject;
import se.ernell.java.streamprocessor.utils.WordUtilities;

public class Line implements IStreamObject {

    /** string representing the html version of the line */
    public String html_line;

    /** string representing a line */
    public final String line;

    /** length of the line */
    public final int length;

    /** integer score in a wordgame such as Scrabble or Wordfeud */
    public final int score;

    /** if true, the word will be marked as unique */
    public final boolean unique;

    /** integer pair with the numbers of vowels + consonants */
    public final int[] vowcons;

    /** probability of a word. implemented later */
    // public final int word_probability;

    /**
     * Static factory methods returns an object of this class.
     */
    public static Line create(char[] arg_word, int score, int len,
	    boolean unique) {
	return new Line(arg_word, score, len, unique);
    }

    public static Line create(String arg_word, int score, boolean unique) {
	return new Line(arg_word, score, unique);
    }

    public static Line create(String arg_word, int score) {
	return new Line(arg_word, score);
    }

    public static Line create(StringBuilder arg_word, int score, boolean unique) {
	return new Line(arg_word, score, unique);
    }

    public static Line create(String arg_word) {
	return new Line(arg_word);
    }

    public Line(String arg_word, int arg_score) {
	line = arg_word;
	length = arg_word.length();
	score = arg_score;
	unique = false;
	vowcons = WordUtilities.getVowCons(arg_word);
    }

    public Line(String arg_word, int arg_score, boolean arg_unique) {
	line = arg_word;
	length = arg_word.length();
	score = arg_score;
	unique = arg_unique;
	vowcons = WordUtilities.getVowCons(arg_word);
    }

    // + experimental
    public Line(char[] arg_word, int arg_score, int len, boolean arg_unique) {
	line = new String(arg_word).trim();
	length = arg_word.length;
	score = arg_score;
	unique = arg_unique;
	vowcons = WordUtilities.getVowCons(arg_word, len);
    }

    public Line(StringBuilder arg_word, int arg_score, boolean arg_unique) {
	line = arg_word.toString();
	length = arg_word.length();
	score = arg_score;
	unique = arg_unique;
	vowcons = WordUtilities.getVowCons(arg_word.toString());
    }

    public Line(String arg_word) {
	line = arg_word;
	length = arg_word.length();
	score = 0;
	unique = false;
	vowcons = WordUtilities.getVowCons(arg_word);
    }

    @Override
    public int[] getWovCons() {
	return vowcons;
    }

    @Override
    public int getScore() {
	return score;
    }

    public String getScoreString() {
	return String.valueOf(score);
    }

    @Override
    public IStreamObject getItem() {
	return this;
    }

    @Override
    public String getLine() {
	return line;
    }

    @Override
    public int getLength() {
	return length;
    }

    private static String makeColorShiftHtml(char[] arg_line) {
	StringBuilder sb = new StringBuilder(20);

	for (int i = 0; i < arg_line.length; i++) {
	    // line.charAt(i);
	}
	return "";
    }

    public String toHTML(int style) {

	switch (style) {

	// case HOOKS

	// case NORMAL

	default:
	    break;
	}

	return "";
    }

    @Override
    public String toString() {
	return line;
    }

}
