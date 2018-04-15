package bruce.roflcraft.client.gui.component.button;

/**
 * Listener interface for GUIButtonComponent events
 * @author Lorrtath
 *
 */
public interface IGUIButtonComponentListener 
{
	public void onMouseEnter(GUIButtonComponent button, int mouseX, int mouseY);

	public void onMouseLeave(GUIButtonComponent button, int mouseX, int mouseY);
	
	public void onMouseMoveOver(GUIButtonComponent button, int mouseX, int mouseY);
	
	public void onButtonPressed(GUIButtonComponent button, int mouseX, int mouseY);
	
	public void onButtonReleased(GUIButtonComponent button, int mouseX, int mouseY);
}
