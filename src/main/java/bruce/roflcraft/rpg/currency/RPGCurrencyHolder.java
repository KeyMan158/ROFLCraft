package bruce.roflcraft.rpg.currency;

/**
 * Implementation of the ICurrencyHolder interface
 * @author Lorrtath
 *
 */
public class RPGCurrencyHolder implements ICurrencyHolder
{
	// The amount that can be stored in this container
	private int storedAmount;
	
	// THe max amount that can be stored, -1 = unlimited
	private int maxAmount;
	
	private static final int DEFAULT_MAX_CURRENCT = 30;

	public RPGCurrencyHolder()
	{
		storedAmount = 0;
		maxAmount = DEFAULT_MAX_CURRENCT;
	}
	
	/**
	 * Sets the max amount that can be stored in this container.
	 * A negative value will designate unlimited storage
	 * @param amount The new Max amount that can be stored
	 */
	public void SetMaxAmount(int amount)
	{
		maxAmount = amount;
	}
	
	@Override
	public int getAmount() 
	{
		return storedAmount;
	}

	@Override
	public int getLimit()
	{
		return maxAmount;
	}
	
	@Override
	public int addAmount(int amount) 
	{
		storedAmount += Math.abs(amount);
		if(amount > maxAmount && maxAmount >= 0)
		{
			int amountRemaining = storedAmount - maxAmount;
			amount = maxAmount;
			return amountRemaining;
		}
		return 0;
	}

	@Override
	public int removeAmount(int amount) 
	{
		storedAmount -= amount;
		if (storedAmount < 0)
		{
			int amountOwed = Math.abs(storedAmount);
			storedAmount = 0;
			return amountOwed;
		}
		return 0;
	}

	@Override
	public int removeAll() 
	{
		int amountRemoved = storedAmount;
		storedAmount = 0;
		return amountRemoved;
	}

}
