package bruce.roflcraft.gui;

import bruce.roflcraft.main.Reference;
import bruce.roflcraft.settings.Skills;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import scala.reflect.internal.Trees.This;

public class CharacterSheetGUI extends GuiScreen
{
	//Constants:
	private final String TEXTURE_NAME = "CharacterSheet.png";
	private final int BOOK_TOP = 1;
	private final int BOOK_LEFT = 20;
	private final int BOOK_WIDTH = 228;
	private final int BOOK_HEIGHT = 180;
	private final int SKILL_FRAME_TOP = 223;
	private final int SKILL_FRAME_LEFT = 3;
	private final int SKILL_FRAME_HEIGHT = 22;
	private final int SKILL_FRAME_WIDTH = 84;
	private final int SKILLS_TOP = 11;
	private final int SKILLS_LEFT = 120;
	private final int SKILL_TEXT_TOP = 5;
	private final int SKILL_TEXT_LEFT = 24;
	
	//Skill Controls:
	public GuiLabel testLable;
	public GuiSlider testBar;
	public GuiButton testButton;
	
	public GuiLabel[] skillLables;
	public GuiSlider[] slillBars;
	public GuiButton[] skillButtons;
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		//Draw GUI content to the screen 
		this.drawDefaultBackground();//Set the background to the default value
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/" + TEXTURE_NAME));
		this.drawTexturedModalRect(this.width/2 - BOOK_WIDTH/2, this.height/2 - BOOK_HEIGHT/2, BOOK_LEFT, BOOK_TOP, BOOK_WIDTH, BOOK_HEIGHT);
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		//Draw other content from the texture
		for (int i = 0; i < 5; i++) 
		{
			//Draw out the skill frames
			this.drawTexturedModalRect(this.width/2 - BOOK_WIDTH/2 + SKILLS_LEFT,
					this.height/2 - BOOK_HEIGHT/2 + SKILLS_TOP + (i * SKILL_FRAME_HEIGHT),
					SKILL_FRAME_LEFT,
					SKILL_FRAME_TOP,
					SKILL_FRAME_WIDTH,
					SKILL_FRAME_HEIGHT);
		}
		//Draw features with the front renderer
		for (int i = 0; i < 5; i++) 
		{
			this.mc.fontRendererObj.drawString("Skill " + i, 
					this.width/2 - BOOK_WIDTH/2 + SKILLS_LEFT + SKILL_TEXT_LEFT, 
					this.height/2 - BOOK_HEIGHT/2 + SKILLS_TOP + (i * SKILL_FRAME_HEIGHT) + SKILL_TEXT_TOP,
					0);
			this.mc.fontRendererObj.drawString("" + i, 
					this.width/2 - BOOK_WIDTH/2 + SKILLS_LEFT + 3, 
					this.height/2 - BOOK_HEIGHT/2 + SKILLS_TOP + (i * SKILL_FRAME_HEIGHT) + 3,
					0);
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	@Override
	public void initGui()
	{
		//set GUI features
	}
	
	
}
