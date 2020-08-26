package bruce.roflcraft.rpg.currency;

/**
 * Interface for classes that have a currency holder. This should
 * be implemented in any object that can get access to a currency
 * holder and be looked up within an instance registry across a
 * network
 * @author Lorrtath
 *
 */
public interface ICurrencyOwner 
{
	/**
	 * Gets the currency holder that this object owns
	 * @return The currency holder that this object owns
	 */
	public ICurrencyHolder getCurrencyHolder();
}
