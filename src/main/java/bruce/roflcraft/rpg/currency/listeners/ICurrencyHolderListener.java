package bruce.roflcraft.rpg.currency.listeners;

/**
 * Listener interface for notifications that a balance holder has changed the
 * amount being held. This is useful for things such as HUD updates to 
 * monetary changes
 * @author Lorrtath
 *
 */
public interface ICurrencyHolderListener 
{
	/**
	 * Tells the listener that the currency holder in question has changed by a given amount
	 * @param changedAmount THe amount changed by
	 */
	public void currencyBalanceChanged(int changedAmount);
}
