package se.ernell.java.streamprocessor.utils;

public class WordUtilities {

    // Final Constants
    private final static int INDEX_VOWELS = 0;
    private final static int INDEX_CONSONANTS = 1;

    /**
     * Count Vowels & Consonants
     * 
     * @param arg_word
     *            Word to count
     * @return int[]: index 0 = number of vowels; index 1 = number of consonants
     */
    public final static int[] getVowCons(String arg_word) {

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
    public final static int[] getVowCons(char[] arg_word, int len) {

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

}
