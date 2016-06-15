package bruce.roflcraft.gui;

import org.lwjgl.opengl.GL11;

import bruce.roflcraft.main.Reference;
import bruce.roflcraft.settings.GUIIDs;

//========================================
//File Name: RoflGUIScreen.java
//Author: Lorrtath
//Purpose: Test / play with how GUIs work
//========================================

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class RoflGUIScreen extends GuiScreen
{
	//default width = 480
	//default height = 255
	
	//public static final int GUIID = GUIIDs.CharacterSheet.ordinal();
	
	//Test GUI buttons (should be private...)
	public GuiButton a;
	public GuiButton b;
	
	private final String TEXTURENAME = "CharacterSheet.png";
	

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		//This method draws GUI content to the screen
		
		this.drawDefaultBackground();//Set the background to the default value
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/" + TEXTURENAME));//Use this to load in a 256x256 texture
		//This draws a rectangle using the loaded texture.(left, top, texture U min, texture V Min, texture U Maz, texture V Max)
		this.drawTexturedModalRect(0, 0, 0, 0,100, 200);
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

		a = new GuiButton(0, this.width/2 - 100, this.height / 2 - 24, "This is button 'A'");
		b = new GuiButton(1, this.width/2 - 100, this.height / 2 + 4, "This is not button 'A'");
		
		//Buttons are added in the initialisation 
		this.buttonList.add(a);
		this.buttonList.add(b);
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
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
	}
}
