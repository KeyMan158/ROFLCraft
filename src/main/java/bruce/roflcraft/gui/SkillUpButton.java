package bruce.roflcraft.gui;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import scala.reflect.internal.Trees.Super;

//=============================================
//This class is just for testing & DEV purposes
//=============================================

public class SkillUpButton extends GuiButton
{
	//Constants:
	private final static int XP_DRAW_RATE = 1; //The XP drawn per cycle while the button is held down
	
	//Variables
	private boolean initilized;
	private EntityPlayer player;

	public SkillUpButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) 
	{
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		// TODO Auto-generated constructor stub
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
		if(player.experienceTotal > 0)
		{
			player.addExperience(-1 * XP_DRAW_RATE);
			if(player.experience < 0)
			{
				int overShoot = (int)( -1 * player.experience * player.xpBarCap());
				player.removeExperienceLevel(1);
				player.experience = ((float)player.xpBarCap() - overShoot) / (float)player.xpBarCap();
			}
		}
	}
}
