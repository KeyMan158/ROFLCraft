package bruce.roflcraft.init;

import org.lwjgl.input.Keyboard;

import akka.dispatch.SystemMessageQueue;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import scala.tools.nsc.doc.model.Public;

public class KeyBindings 
{
	//Key Binding  (should be private?)
	public static KeyBinding CharacterSheet;
	
	public KeyBindings()
	{
		CharacterSheet = new KeyBinding("Open Character Sheet", Keyboard.KEY_C, "ROFLCraft");
		ClientRegistry.registerKeyBinding(CharacterSheet);
	}
}
