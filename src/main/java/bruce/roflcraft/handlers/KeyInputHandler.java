package bruce.roflcraft.handlers;

import org.lwjgl.input.Mouse;

import bruce.roflcraft.gui.RoflGUIScreen;
import bruce.roflcraft.init.KeyBindings;
import bruce.roflcraft.main.RoflCraft;
import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import bruce.roflcraft.settings.GUIIDs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
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
			System.out.println("Opening Character Sheet");
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			player.openGui(RoflCraft.instance, GUIIDs.CharacterSheet.ordinal(), (World)player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
		}
	}
}
