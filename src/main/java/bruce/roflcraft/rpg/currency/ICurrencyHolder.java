package bruce.roflcraft.rpg.currency;

/**
 * Base interface for all RPG currency containers and is
 * the subject for all currency actions (i.e. transactions)
 * @author Lorrtath
 *
 */
public interface ICurrencyHolder 
{
	/**
	 * Gets the amount of currency stored within this container
	 * @return THe amount of currency stored
	 */
	public int getAmount();
	
	/**
	 * Gets the maximum amount that can be stored in this container
	 * @return The amount that can be stored in this container
	 */
	public int getLimit();
	
	/**
	 * Adds an amount to the holder
	 * @param amount The amount to add
	 * @return The amount not added
	 */
	public int addAmount(int amount);
	
	/**
	 * Attempts to remove an amount from the holder
	 * @param amount The amount to remove
	 * @return The amount not removed
	 */
	public int removeAmount(int amount);
	
	/**
	 * Removes all currency from the container
	 * @return The amount removed from the container
	 */
	public int removeAll();
}
