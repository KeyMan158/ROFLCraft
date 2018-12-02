package bruce.roflcraft.rpg.currency;

/**
 * Report class to inform an item, block or player of its
 * currency containers. This should only be used on server
 * side updates
 * 
 * @author Lorrtath
 *
 */
public class RPGCurencyStatement 
{
	/**
	 * The new balance / amount of the container
	 */
	public int balance;
	
	/**
	 * The amount added to prompt the update
	 */
	public int paidIn;
	
	/**
	 * The amount removed to prompt the update
	 */
	public int paidOut;
}
