package se.ernell.java.streamprocessor;

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

import java.util.HashMap;

/**
 * Score (Singleton class)
 * 
 * @author rob@ernell.se
 * 
 */
public class Score {

    private static Score instance;

    protected Score() {

    }

    public static Score getInstance() {
	if (instance == null)
	    instance = new Score();
	return instance;
    }

    /** Score definitions (new in v2.109) */
    public enum Game {
	ENGLISH_SCRABBLE, ENGLISH_WORDFEUD, ENGLISH_WORDS4FRIENDS, SWEDISH_WORDFEUD
    };

    public static HashMap<Character, Integer> score_table;
    public static Game active_game;

    static {
	score_table = setscore_ENGLISH_SCRABBLE();
    }

    public static int getScore(char[] word, int len) {
	int points = 0;
	int temp = 0;

	try {
	    // do loopcount backwards???
	    for (int i = 0; i < len; i++) {
		temp = score_table.get(word[i]);
		// if (temp > 0)
		points += temp;
	    }

	} catch (NullPointerException e) {
	    e.printStackTrace();
	}

	return points;

    }

    public static String getGameString() {
	return active_game.toString();
    }

    public static Game getGame() {
	return active_game;
    }

    /**
     * 
     * @param score_table
     *            Use the public constants 'SCORE_<LANG>_<GAME>'
     * @return A score table ( HashMap<Character, Integer> )
     */
    public static void setScoreTable(Game score_game) {

	active_game = score_game;

	// debug
	// System.out.println("setScoreTable: " + active_game.toString());

	switch (score_game) {

	case ENGLISH_SCRABBLE:
	    score_table = setscore_ENGLISH_SCRABBLE();
	    break;
	case ENGLISH_WORDFEUD:
	    score_table = setscore_ENGLISH_WORDFEUD();
	    break;

	case SWEDISH_WORDFEUD:
	    score_table = setscore_SWEDISH_WORDFEUD();
	    break;

	case ENGLISH_WORDS4FRIENDS:
	    score_table = setscore_ENGLISH_WORDS4FRIENDS();
	    break;

	default:
	    score_table = setscore_ENGLISH_SCRABBLE();
	    break;

	}

    }

    /** English - Wordfeud */
    private static HashMap<Character, Integer> setscore_ENGLISH_WORDFEUD() {
	HashMap<Character, Integer> score_hashmap = new HashMap<Character, Integer>();
	score_hashmap.clear();
	score_hashmap.put('A', Integer.valueOf(1));
	score_hashmap.put('B', Integer.valueOf(4));
	score_hashmap.put('C', Integer.valueOf(4));
	score_hashmap.put('D', Integer.valueOf(2));
	score_hashmap.put('E', Integer.valueOf(1));
	score_hashmap.put('F', Integer.valueOf(4));
	score_hashmap.put('G', Integer.valueOf(3));
	score_hashmap.put('H', Integer.valueOf(4));
	score_hashmap.put('I', Integer.valueOf(1));
	score_hashmap.put('J', Integer.valueOf(10));
	score_hashmap.put('K', Integer.valueOf(5));
	score_hashmap.put('L', Integer.valueOf(1));
	score_hashmap.put('M', Integer.valueOf(3));
	score_hashmap.put('N', Integer.valueOf(1));
	score_hashmap.put('O', Integer.valueOf(1));
	score_hashmap.put('P', Integer.valueOf(4));
	score_hashmap.put('Q', Integer.valueOf(10));
	score_hashmap.put('R', Integer.valueOf(1));
	score_hashmap.put('S', Integer.valueOf(1));
	score_hashmap.put('T', Integer.valueOf(1));
	score_hashmap.put('U', Integer.valueOf(2));
	score_hashmap.put('V', Integer.valueOf(4));
	score_hashmap.put('W', Integer.valueOf(4));
	score_hashmap.put('X', Integer.valueOf(8));
	score_hashmap.put('Y', Integer.valueOf(4));
	score_hashmap.put('Z', Integer.valueOf(10));
	score_hashmap.put('Å', Integer.valueOf(0));
	score_hashmap.put('Ä', Integer.valueOf(0));
	score_hashmap.put('Ö', Integer.valueOf(0));
	return score_hashmap;
    }

    /** English - Words 4 Friends */
    private static HashMap<Character, Integer> setscore_ENGLISH_WORDS4FRIENDS() {
	HashMap<Character, Integer> score_hashmap = new HashMap<Character, Integer>();
	score_hashmap.clear();
	score_hashmap.put('A', Integer.valueOf(1));
	score_hashmap.put('B', Integer.valueOf(4));
	score_hashmap.put('C', Integer.valueOf(4));
	score_hashmap.put('D', Integer.valueOf(2));
	score_hashmap.put('E', Integer.valueOf(1));
	score_hashmap.put('F', Integer.valueOf(4));
	score_hashmap.put('G', Integer.valueOf(3));
	score_hashmap.put('H', Integer.valueOf(3));
	score_hashmap.put('I', Integer.valueOf(1));
	score_hashmap.put('J', Integer.valueOf(10));
	score_hashmap.put('K', Integer.valueOf(5));
	score_hashmap.put('L', Integer.valueOf(2));
	score_hashmap.put('M', Integer.valueOf(4));
	score_hashmap.put('N', Integer.valueOf(2));
	score_hashmap.put('O', Integer.valueOf(1));
	score_hashmap.put('P', Integer.valueOf(4));
	score_hashmap.put('Q', Integer.valueOf(10));
	score_hashmap.put('R', Integer.valueOf(1));
	score_hashmap.put('S', Integer.valueOf(1));
	score_hashmap.put('T', Integer.valueOf(1));
	score_hashmap.put('U', Integer.valueOf(2));
	score_hashmap.put('V', Integer.valueOf(5));
	score_hashmap.put('W', Integer.valueOf(4));
	score_hashmap.put('X', Integer.valueOf(8));
	score_hashmap.put('Y', Integer.valueOf(3));
	score_hashmap.put('Z', Integer.valueOf(10));
	score_hashmap.put('Å', Integer.valueOf(0));
	score_hashmap.put('Ä', Integer.valueOf(0));
	score_hashmap.put('Ö', Integer.valueOf(0));
	return score_hashmap;
    }

    /** English - Scrabble */
    private static HashMap<Character, Integer> setscore_ENGLISH_SCRABBLE() {
	HashMap<Character, Integer> score_hashmap = new HashMap<Character, Integer>();
	score_hashmap.clear();
	score_hashmap.put('A', Integer.valueOf(1));
	score_hashmap.put('B', Integer.valueOf(3));
	score_hashmap.put('C', Integer.valueOf(3));
	score_hashmap.put('D', Integer.valueOf(2));
	score_hashmap.put('E', Integer.valueOf(1));
	score_hashmap.put('F', Integer.valueOf(4));
	score_hashmap.put('G', Integer.valueOf(2));
	score_hashmap.put('H', Integer.valueOf(4));
	score_hashmap.put('I', Integer.valueOf(1));
	score_hashmap.put('J', Integer.valueOf(8));
	score_hashmap.put('K', Integer.valueOf(5));
	score_hashmap.put('L', Integer.valueOf(1));
	score_hashmap.put('M', Integer.valueOf(3));
	score_hashmap.put('N', Integer.valueOf(1));
	score_hashmap.put('O', Integer.valueOf(1));
	score_hashmap.put('P', Integer.valueOf(3));
	score_hashmap.put('Q', Integer.valueOf(10));
	score_hashmap.put('R', Integer.valueOf(1));
	score_hashmap.put('S', Integer.valueOf(1));
	score_hashmap.put('T', Integer.valueOf(1));
	score_hashmap.put('U', Integer.valueOf(1));
	score_hashmap.put('V', Integer.valueOf(4));
	score_hashmap.put('W', Integer.valueOf(4));
	score_hashmap.put('X', Integer.valueOf(8));
	score_hashmap.put('Y', Integer.valueOf(4));
	score_hashmap.put('Z', Integer.valueOf(10));
	score_hashmap.put('Å', Integer.valueOf(0));
	score_hashmap.put('Ä', Integer.valueOf(0));
	score_hashmap.put('Ö', Integer.valueOf(0));
	return score_hashmap;
    }

    /** English - Scrabble */
    private static HashMap<Character, Integer> setscore_SWEDISH_WORDFEUD() {
	HashMap<Character, Integer> score_hashmap = new HashMap<Character, Integer>();
	score_hashmap.clear();
	score_hashmap.put('A', Integer.valueOf(1));
	score_hashmap.put('B', Integer.valueOf(3));
	score_hashmap.put('C', Integer.valueOf(3));
	score_hashmap.put('D', Integer.valueOf(2));
	score_hashmap.put('E', Integer.valueOf(1));
	score_hashmap.put('F', Integer.valueOf(4));
	score_hashmap.put('G', Integer.valueOf(2));
	score_hashmap.put('H', Integer.valueOf(4));
	score_hashmap.put('I', Integer.valueOf(1));
	score_hashmap.put('J', Integer.valueOf(8));
	score_hashmap.put('K', Integer.valueOf(5));
	score_hashmap.put('L', Integer.valueOf(1));
	score_hashmap.put('M', Integer.valueOf(3));
	score_hashmap.put('N', Integer.valueOf(1));
	score_hashmap.put('O', Integer.valueOf(1));
	score_hashmap.put('P', Integer.valueOf(3));
	score_hashmap.put('Q', Integer.valueOf(10));
	score_hashmap.put('R', Integer.valueOf(1));
	score_hashmap.put('S', Integer.valueOf(1));
	score_hashmap.put('T', Integer.valueOf(1));
	score_hashmap.put('U', Integer.valueOf(1));
	score_hashmap.put('V', Integer.valueOf(4));
	score_hashmap.put('W', Integer.valueOf(4));
	score_hashmap.put('X', Integer.valueOf(8));
	score_hashmap.put('Y', Integer.valueOf(4));
	score_hashmap.put('Z', Integer.valueOf(10));
	score_hashmap.put('Å', Integer.valueOf(0));
	score_hashmap.put('Ä', Integer.valueOf(0));
	score_hashmap.put('Ö', Integer.valueOf(0));
	return score_hashmap;
    }

}
