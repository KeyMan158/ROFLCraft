package bruce.roflcraft.client.gui.component.character;

import bruce.roflcraft.rpg.character.stats.AttributeIndex;

/**
 * Interface for triggering character attribute based
 * changes in a series of registered user interface 
 * components
 * @author Lorrtath
 *
 */
public interface IAttributeBasedComponent 
{
	/**
	 * Registers a new listener
	 * @param listener The listener to register
	 */
	public void registerListener(IAttributeControlledComponent listener);
	
	/**
	 * Removes a registered listener
	 * @param listener The listener to remove
	 */
	public void unregisterListener(IAttributeControlledComponent listener);
	
	/**
	 * Sets the attribute
	 * @param index The attribute index to use
	 */
	public void setAttribute(AttributeIndex index);
	
	/**
	 * Gets the attribute associated with this component
	 * @return The attribute of this component
	 */
	AttributeIndex getAttribute();
	
	/**
	 * Trigger for broadcasting an atribute to all listeners
	 */
	public void broadcastAttribute();
}
