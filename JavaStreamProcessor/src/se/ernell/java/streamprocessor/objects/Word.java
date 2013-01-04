package se.ernell.java.streamprocessor.objects;

public class Word implements IStreamObject {

    // Final Constants
    private final static int INDEX_VOWELS = 0;
    private final static int INDEX_CONSONANTS = 1;

    // Word data
    /** string representing a word */
    public final String word;

    /** length of the word */
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
     * Count Vowels & Consonants
     * 
     * @param arg_word
     *            Word to count
     * @return int[]: index 0 = number of vowels; index 1 = number of consonants
     */
    private final static int[] getVowCons(String arg_word) {

	int len = arg_word.length();
	int answer[] = { 0, 0 };
	for (int i = 0; i < len; i++) {
	    char c = arg_word.charAt(i);

	    if (c == 'E' || c == 'A' || c == 'I' || c == 'O' || c == 'U')
		answer[INDEX_VOWELS]++;
	    else if (c == 'N' || c == 'R' || c == 'T' || c == 'L' || c == 'S'
		    || c == 'D' || c == 'G' || c == 'B' || c == 'C' || c == 'M'
		    || c == 'P' || c == 'F' || c == 'H' || c == 'V' || c == 'W'
		    || c == 'Y' || c == 'K' || c == 'J' || c == 'X' || c == 'Q'
		    || c == 'Z')
		answer[INDEX_CONSONANTS]++;

	}
	return answer;
    }

    /**
     * Count Vowels & Consonants
     * 
     * @param arg_word
     *            Word to count
     * @param len
     *            Length of arg_word
     * @return int[]: index 0 = number of vowels; index 1 = number of consonants
     */
    private final static int[] getVowCons(char[] arg_word, int len) {

	int answer[] = { 0, 0 };
	for (int i = 0; i < len; i++) {
	    char c = arg_word[i];

	    if (c == 'E' || c == 'A' || c == 'I' || c == 'O' || c == 'U')
		answer[INDEX_VOWELS]++;
	    else if (c == 'N' || c == 'R' || c == 'T' || c == 'L' || c == 'S'
		    || c == 'D' || c == 'G' || c == 'B' || c == 'C' || c == 'M'
		    || c == 'P' || c == 'F' || c == 'H' || c == 'V' || c == 'W'
		    || c == 'Y' || c == 'K' || c == 'J' || c == 'X' || c == 'Q'
		    || c == 'Z')
		answer[INDEX_CONSONANTS]++;

	}
	return answer;
    }

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

    private Word(String arg_word, int arg_score) {
	word = arg_word;
	score = arg_score;
	length = (byte) arg_word.length();
	unique = false;
	vowcons = getVowCons(arg_word);
    }

    private Word(String arg_word, int arg_score, boolean arg_unique) {
	word = arg_word;
	score = arg_score;
	length = (byte) arg_word.length();
	unique = arg_unique;
	vowcons = getVowCons(arg_word);
    }

    // + experimental
    private Word(char[] arg_word, int arg_score, int len, boolean arg_unique) {
	word = new String(arg_word);// .toString();// + experimental, no trim.
				    // does not work.
	score = arg_score;
	length = len;
	unique = arg_unique;
	vowcons = getVowCons(arg_word, len);
    }

    private Word(StringBuilder arg_word, int arg_score, boolean arg_unique) {
	word = arg_word.toString();
	score = arg_score;
	length = (byte) arg_word.length();
	unique = arg_unique;
	vowcons = getVowCons(arg_word.toString());
    }

    private Word(String arg_word) {
	word = arg_word;
	score = 0;
	length = (byte) arg_word.length();
	unique = false;
	vowcons = getVowCons(arg_word);
    }

    public String getWord() {
	return word;
    }

    public int getScore() {
	return score;
    }

    public String getScoreString() {
	return String.valueOf(score);
    }

}
