package bruce.roflcraft.handlers;

import org.lwjgl.input.Mouse;

import bruce.roflcraft.gui.RoflGUIScreen;
import bruce.roflcraft.init.KeyBindings;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputHandler 
{
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		if(KeyBindings.CharacterSheet.isPressed())
		{
			//run the test GUI
			/* This Crashes...
			System.out.println("Opening Character Sheet");
			RoflGUIScreen screen = new RoflGUIScreen();
			screen.initGui();
			screen.drawScreen(Mouse.getX(), Mouse.getY(), 0.5f);
			*/
			
		}
	}
}
