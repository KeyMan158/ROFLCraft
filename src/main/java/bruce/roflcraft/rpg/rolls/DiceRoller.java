package bruce.roflcraft.rpg.rolls;

import java.util.Date;
import java.util.Random;

/**
 * This class acts as a dice rolling tool.
 * By default, this will always roll a d20 dice!
 * @author Lorrtath
 *
 */
public class DiceRoller 
{
	private static final int DEFAULT_SIDES = 20;
	
	/**
	 * Rolls a D20 Dice and returns the result
	 * @return The dice roll
	 */
	public static int RollDice()
	{
		return RollDice(DEFAULT_SIDES);
	}
	
	/**
	 * Rolls an "N" sided dice
	 * @param sides The number of sides to the dice
	 * @return The dice roll
	 */
	public static int RollDice(int sides)
	{
		Random generator = new Random();
		generator.setSeed(getSeed());
		int roll =  generator.nextInt(sides);
		// Cannot roll a 0 on any dice!
		if(roll == 0)
		{
			return 1;
		}
		else
		{
			return roll;
		}
	}
	
	public static boolean flipCoin()
	{
		Random generator = new Random();
		generator.setSeed(getSeed());
		return generator.nextBoolean();
	}
	
	private static long getSeed()
	{
		Date date = new Date();
		return date.getTime();
	}
}
