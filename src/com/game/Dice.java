package com.game;

import java.util.Random;

/**
 * This class represent the Dice information and handles stuff
 * 
 * @author mehak
 *
 */

public class Dice {

	public Dice() {

	}

	public Dice(int value) {
       this.value = value;
	}

	private int value;

	public void rollDice() {
		value = new Random().nextInt(5) + 1;
	}

	public int getValue() {
		return value;
	}
}
