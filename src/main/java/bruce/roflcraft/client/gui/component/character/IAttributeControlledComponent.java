package bruce.roflcraft.client.gui.component.character;

import bruce.roflcraft.rpg.character.stats.AttributeIndex;

/**
 * This interface allows for listening for GUI
 * changes that are dependent on an attribute
 * @author Lorrtath
 *
 */
public interface IAttributeControlledComponent 
{
	/**
	 * Tells the implementing class that a triggering object
	 * wants it to change behaviour based on a character attribute
	 * @param index The attribute index
	 * @param sender The instigating component
	 */
	public void changeAttribute(AttributeIndex index, IAttributeBasedComponent sender);
}
