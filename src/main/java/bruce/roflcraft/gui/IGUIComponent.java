package bruce.roflcraft.gui;

import net.minecraft.client.Minecraft;

/**
 * Interface for modulating GUI components. These 
 * should be registered in a GUIComponentManager object
 * @author Lorrtath
 */
public interface IGUIComponent 
{
	/**
	 * Initialise the component
	 */
	public void init();
	
	/**
	 * Draws the component	
	 */
	public void drawComponent(Minecraft mc, int mouseX, int mouseY);
	
	/**
	 * Gets the top value
	 * @return The top of the component
	 */
	public int getTop();
	
	/**
	 * Gets the left value
	 * @return The left of the component
	 */
	public int getLeft();
	
	/**
	 * Gets the width value
	 * @return The width of the component
	 */
	public int getWidth();
	
	/**
	 * Gets the height component
	 * @return The height of the component
	 */
	public int getHeight();
	
	/**
	 * Gets the visibility of the component
	 * @return The visibility of the component
	 */
	public boolean getVisibility();
	
	/**
	 * Sets the visibility of the component
	 * @param visability The visibility of the component
	 */
	public void setVisibility(boolean visability);
}
