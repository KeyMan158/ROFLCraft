package bruce.roflcraft.gui;

import org.lwjgl.input.Mouse;

import bruce.roflcraft.main.RoflCraft;
import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import scala.reflect.internal.Trees.Super;

//=============================================
//This class is just for testing & DEV purposes
//=============================================
@Deprecated
public class SkillUpButton extends GuiButton
{
	//Constants:
	private final static int XP_DRAW_RATE = 1; //The XP Levels drawn per cycle while the button is held down
	
	//Variables
	private boolean initilized;
	private EntityPlayer player;

	public SkillUpButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) 
	{
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		initilized = false;
	}
	
	public void init(EntityPlayer player)
	{
		//change this to the characterSheet or pass in a delegate
		this.player = player;
		initilized = true;
	}
	
	//@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
	{
		boolean result = Mouse.isButtonDown(0);
		if (result)
		{
			drainXP();
		}
		return result;
	}
	
	//These methods could be placed into the character sheet?
	private void drainXP()
	{
		if(player.experienceLevel > XP_DRAW_RATE)
		{
			IRPGCharacterData characterData = player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
			if (characterData != null)
			{
				characterData.PurchaseSkillPoint(XP_DRAW_RATE);
			}
		}
	}
}
