package com.game.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.game.Dice;
import com.game.DiceGame;



public class DicegameApplicationTests {
	DiceGame game;
	
	@Before
	public void init(){
		game = new DiceGame();
	}

	@Test
	public void testDiceGameInitilization(){		
		assertNotNull("Dice Game is initilized", game.getDiceArry());
		assertEquals("Only 5 Dices exists", game.getDiceArry().length, 5);
	}
	
	@Test
	public void testDiceGameRollDice(){
		game.rollDice();
		Dice[] arry = game.getDiceArry();
		Dice diceOne = arry[0];
		assertNotNull("Dice1 value initilized", diceOne.getValue());		
		assertTrue("Dice value between 1 to 6", (diceOne.getValue() >= 1 && diceOne.getValue() <= 6));
		
	}
	
	@Test
	public void testDiceGameComputeScore(){		
		Dice[] arry = game.getDiceArry();
		arry[0] = new Dice(1);
		arry[1] = new Dice(2);
		arry[2] = new Dice(3);
		arry[3] = new Dice(4);
		arry[4] = new Dice(5);
		game.computeScore();
		assertEquals("With numbers 1,2,3,4,5", 140,  game.getScore());	
		
		arry = game.getDiceArry();
		arry[0] = new Dice(1);
		arry[1] = new Dice(1);
		arry[2] = new Dice(1);
		arry[3] = new Dice(2);
		arry[4] = new Dice(2);
		game.computeScore();
		assertEquals("With numbers 1,1,1,2,2", 1000,  game.getScore());	
		
		arry = game.getDiceArry();
		arry[0] = new Dice(5);
		arry[1] = new Dice(4);
		arry[2] = new Dice(5);
		arry[3] = new Dice(4);
		arry[4] = new Dice(5);
		game.computeScore();
		assertEquals("With numbers 5,4,5,4,5", 580,  game.getScore());	
		
		arry = game.getDiceArry();
		arry[0] = new Dice(4);
		arry[1] = new Dice(4);
		arry[2] = new Dice(4);
		arry[3] = new Dice(4);
		arry[4] = new Dice(4);
		game.computeScore();
		assertEquals("With numbers 4,4,4,4,4", 480,  game.getScore());	
		
		arry = game.getDiceArry();
		arry[0] = new Dice(1);
		arry[1] = new Dice(5);
		arry[2] = new Dice(5);
		arry[3] = new Dice(5);
		arry[4] = new Dice(1);
		game.computeScore();
		assertEquals("With numbers 1,5,5,5,1", 700,  game.getScore());	
	}
}
