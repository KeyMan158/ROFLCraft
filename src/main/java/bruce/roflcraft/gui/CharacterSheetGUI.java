package bruce.roflcraft.gui;

import bruce.roflcraft.gui.GUIComponent.GUIComponentManager;
import bruce.roflcraft.gui.GUIComponent.Button.ArrowButton;
import bruce.roflcraft.gui.GUIComponent.Button.ArrowButtonDirection;
import bruce.roflcraft.main.Reference;
import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import bruce.roflcraft.settings.Skills;
import gnu.trove.impl.hash.THashIterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import scala.reflect.internal.Trees.This;

@Deprecated
public class CharacterSheetGUI extends GuiScreen
{	
	//Constants:
	private final String TEXTURE_NAME = "CharacterSheet.png";
	private final int BOOK_TOP = 6;
	private final int BOOK_LEFT = 23;
	private final int BOOK_WIDTH = 226;
	private final int BOOK_HEIGHT = 172;
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
	
	private TestAnimation testAnimation;
	private GUIComponentManager testManager;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		testAnimation.onRenderTick();
		//m_player.getCapability(RPGCharacterProvider.CHAR_CAP, null).
		//this.mc.getTextureManager().bindTexture(testAnimation.getResourceLocation());
		//this.drawTexturedModalRect(0, 0, testAnimation.getLeft() ,testAnimation.getTop(), testAnimation.getWidth(), testAnimation.getHeight());
		
		testManager.drawComponent(this.mc, mouseX, mouseY, partialTicks);
		
		//Draw GUI content to the screen (called every tick)
		//this.drawDefaultBackground();//Set the background to the default value
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/" + TEXTURE_NAME));
		this.drawTexturedModalRect(this.width/2 - BOOK_WIDTH/2, this.height/2 - BOOK_HEIGHT/2, BOOK_LEFT, BOOK_TOP, BOOK_WIDTH, BOOK_HEIGHT);
		
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
		
		//Testing the Book mark Render
		this.drawTexturedModalRect(this.width/2 - BOOK_WIDTH/2 + 22,
				this.height/2 - BOOK_HEIGHT/2 - 13,
				120,
				194,
				20,
				20);
		
		//Draw features with the font renderer
		for (int i = 0; i < 5; i++) 
		{
			fontRenderer.drawString("Skill " + i, 
					this.width/2 - BOOK_WIDTH/2 + SKILLS_LEFT + SKILL_TEXT_LEFT, 
					this.height/2 - BOOK_HEIGHT/2 + SKILLS_TOP + (i * SKILL_FRAME_HEIGHT) + SKILL_TEXT_TOP,
					0);
			fontRenderer.drawString("" + i, 
					this.width/2 - BOOK_WIDTH/2 + SKILLS_LEFT + 3, 
					this.height/2 - BOOK_HEIGHT/2 + SKILLS_TOP + (i * SKILL_FRAME_HEIGHT) + 3,
					0);
		}
		IRPGCharacterData charData = Minecraft.getMinecraft().player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		fontRenderer.drawString(charData.getSkillPointTracker().getStoredXP() + " / " + charData.getSkillPointTracker().xpToNext(),0, 0, 0);	
		fontRenderer.drawString("Level : " + charData.getSkillPointTracker().getLevel(),0, 12, 0);	
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void updateScreen()
	{
		
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	@Override
	public void initGui()
	{
		testManager = new GUIComponentManager();
		ArrowButton button = new ArrowButton();
		testManager.register(button);
		testManager.init(0 , 32);
		testAnimation = new TestAnimation();
		//set GUI features
		//messageTest = new SkillUpButton(0, this.width/2, this.height / 2, 20, 20, "+");
		//messageTest.init(Minecraft.getMinecraft().thePlayer);
		//this.buttonList.add(messageTest);
	}
}
