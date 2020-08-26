package bruce.roflcraft.rpg.currency;

/**
 * Enum for sorting different types of holders and their owners
 * primarily for networking lookups
 * @author Lorrtath
 *
 */
public enum CurrencyHolderType 
{
	HOLDER_TYPE_NULL,
	HOLDER_TYPE_PLAYER,
	HOLDER_TYPE_ITEM,
	HOLDER_TYPE_BLOCK
}
