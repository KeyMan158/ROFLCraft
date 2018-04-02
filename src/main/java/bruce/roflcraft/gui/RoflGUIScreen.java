package bruce.roflcraft.gui;

import org.lwjgl.opengl.GL11;

import bruce.roflcraft.main.Reference;
import bruce.roflcraft.settings.GUIIDs;
import net.minecraft.client.Minecraft;

//========================================
//File Name: RoflGUIScreen.java
//Author: Lorrtath
//Purpose: Test / play with how GUIs work
//========================================

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

@Deprecated
public class RoflGUIScreen extends GuiScreen
{
	//public static final int GUIID = GUIIDs.CharacterSheet.ordinal();
	
	//Test GUI buttons (should be private...)
	//public GuiButton a;
	//public GuiButton b;
	public SkillUpButton test;
	
	private final String TEXTURE_NAME = "CharacterSheet.png";
	private final int BOOK_TOP = 1;
	private final int BOOK_LEFT = 20;
	private final int BOOK_WIDTH = 228;
	private final int BOOK_HEIGHT = 180;
	

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		//This method draws GUI content to the screen
		
		this.drawDefaultBackground();//Set the background to the default value
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/" + TEXTURE_NAME));//Use this to load in a 256x256 texture
		//This draws a rectangle using the loaded texture.(left, top, texture U min, texture V Min, texture width, texture height)
		this.drawTexturedModalRect(this.width/2 - BOOK_WIDTH/2, this.height/2 - BOOK_HEIGHT/2, BOOK_LEFT, BOOK_TOP, BOOK_WIDTH, BOOK_HEIGHT);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		//This override function sets weather the GUI pauses the game when opened
		return false;
	}
	
	@Override
	public void initGui()
	{
		//set GUI features here
		
		//default size is 200x20. With default texture resources, 20x20 should be the smallest 
		//there is also a constructor overload with width & height

		test = new SkillUpButton(0, this.width/2, this.height / 2, 20, 20, "+");
		test.init(Minecraft.getMinecraft().player);
		//a = new GuiButton(0, this.width/2 - 100, this.height / 2 - 24, "This is button 'A'");
		//b = new GuiButton(1, this.width/2 - 100, this.height / 2 + 4, "This is not button 'A'");
		
		//Buttons are added in the initialisation 
		//this.buttonList.add(a);
		//this.buttonList.add(b);
		this.buttonList.add(test);
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		/*//REMOVED 
		//Would be better to inherit buttons and give a enum for a switch statement? 
		if (button == this.a)
		{
			//Send cmds to server, modify character sheet, etc...
			this.mc.displayGuiScreen(null);
			if (this.mc.currentScreen == null)
			{
				this.mc.setIngameFocus();
			}
		}
		
		if (button == this.b)
		{
			//Send cmds to server, modify character sheet, etc...
			this.mc.displayGuiScreen(null);
			if (this.mc.currentScreen == null)
			{
				this.mc.setIngameFocus();
			}
		}
		*/
	}
}
