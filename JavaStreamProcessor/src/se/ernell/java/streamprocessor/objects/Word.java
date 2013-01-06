package se.ernell.java.streamprocessor.objects;

import se.ernell.java.streamprocessor.io.IStreamObject;
import se.ernell.java.streamprocessor.utils.WordUtilities;

public class Word implements IStreamObject {

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
    public static Word create(char[] arg_word, int score, int len,
	    boolean unique) {
	return new Word(arg_word, score, len, unique);
    }

    public static Word create(String arg_word, int score, boolean unique) {
	return new Word(arg_word, score, unique);
    }

    public static Word create(String arg_word, int score) {
	return new Word(arg_word, score);
    }

    public static Word create(StringBuilder arg_word, int score, boolean unique) {
	return new Word(arg_word, score, unique);
    }

    public static Word create(String arg_word) {
	return new Word(arg_word);
    }

    public Word(String arg_word, int arg_score) {
	line = arg_word;
	length = arg_word.length();
	score = arg_score;
	unique = false;
	vowcons = WordUtilities.getVowCons(arg_word);
    }

    public Word(String arg_word, int arg_score, boolean arg_unique) {
	line = arg_word;
	length = arg_word.length();
	score = arg_score;
	unique = arg_unique;
	vowcons = WordUtilities.getVowCons(arg_word);
    }

    // + experimental
    public Word(char[] arg_word, int arg_score, int len, boolean arg_unique) {
	line = new String(arg_word).trim();
	length = arg_word.length;
	score = arg_score;
	unique = arg_unique;
	vowcons = WordUtilities.getVowCons(arg_word, len);
    }

    public Word(StringBuilder arg_word, int arg_score, boolean arg_unique) {
	line = arg_word.toString();
	length = arg_word.length();
	score = arg_score;
	unique = arg_unique;
	vowcons = WordUtilities.getVowCons(arg_word.toString());
    }

    public Word(String arg_word) {
	line = arg_word;
	length = arg_word.length();
	score = 0;
	unique = false;
	vowcons = WordUtilities.getVowCons(arg_word);
    }

    public int[] getWovCons() {
	return vowcons;
    }

    public int getScore() {
	return score;
    }

    public String getScoreString() {
	return String.valueOf(score);
    }

    public String getWord() {
	return line;
    }

    public int getLength() {
	return length;
    }

}
