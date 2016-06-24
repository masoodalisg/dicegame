package com.game;
/**
 * 
 */


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mehak
 *
 */
public class DiceGame {

	private Dice[] diceArry;
	private int score = 0;

	enum scores {
		SEQ_ONE(1000), SEQ_TWO(200), SEQ_THREE(300), SEQ_FOUR(400), SEQ_FIVE(500), SEQ_SIX(600), ONE(100), FOUR(40);

		int number;		
		private scores(int number) {
			this.number = number;
		}

		private int getScore() {
			return number;
		}

	}

	public DiceGame() {
		diceArry = new Dice[5];
		diceArry[0] = new Dice();
		diceArry[1] = new Dice();
		diceArry[2] = new Dice();
		diceArry[3] = new Dice();
		diceArry[4] = new Dice();
	}

	public Dice[] getDiceArry() {
		return diceArry;
	}

	public void rollDice() {
		for (Dice d : diceArry) {
			d.rollDice();
		}
	}

	public void computeScore() {
		Map<Integer, AtomicInteger> segDiceValue = new HashMap<>(5);
		int value = 0;
		for (Dice d : diceArry) {
			value = d.getValue();
			segDiceValue.putIfAbsent(value, new AtomicInteger(0));
			segDiceValue.get(value).incrementAndGet();
		}
		int score = 0, count = 0;
		for (Entry<Integer, AtomicInteger> dice : segDiceValue.entrySet()) {
			value = dice.getKey();
			count = dice.getValue().get();
			score = score + (isSequence(count) ? getSequenceValue(value, count) : getNonSeqenceValue(value, count));
		}
		this.score = score;

	}

	protected boolean isSequence(int count) {
		return count >= 3;
	}

	/**
	 * Computes the non sequence dice scores
	 * 
	 * @param value
	 * @param count
	 * @return
	 */
	protected int getNonSeqenceValue(int value, int count) {
		int score = 0;
		if (count == 0)
			return score;
		
		if (value == 4) {
			score = scores.FOUR.getScore();
		} else if (value == 1) {
			score = scores.ONE.getScore();
		}
		
		score = count == 2 ? score * 2 : score;
		return score;
	}

	/**
	 * computes sequence dice scores
	 * 
	 * @param value
	 * @param count
	 * @return
	 */
	protected int getSequenceValue(int value, int count) {
		int score = 0;
		switch (value) {
		case 1:
			score = scores.SEQ_ONE.getScore();
			break;
		case 2:
			score = scores.SEQ_TWO.getScore();
			break;
		case 3:
			score = scores.SEQ_THREE.getScore();
			break;
		case 4:
			score = scores.SEQ_FOUR.getScore();
			break;
		case 5:
			score = scores.SEQ_FIVE.getScore();
			break;
		case 6:
			score = scores.SEQ_SIX.getScore();
			break;
		}
		score = score + getNonSeqenceValue(value, count - 3);
		return score;
	}

	public int getScore() {
		return score;
	}

}
