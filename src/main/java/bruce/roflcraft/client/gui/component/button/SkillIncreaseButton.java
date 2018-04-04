package bruce.roflcraft.client.gui.component.button;

import bruce.roflcraft.main.Reference;
import bruce.roflcraft.rpg.character.RPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import bruce.roflcraft.rpg.character.stats.SkillIndex;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SkillIncreaseButton extends GUIButtonComponent
{
	private static final int HEIGHT = 15;
	private static final int WIDTH = 15;
	// TODO Replace this placeholder with the correct asset & animations etc..
	private static final String BUTTON_TEXTURE_NAME = "PlusButton.png";
	private static final ResourceLocation BUTTON_RESOURCE = new ResourceLocation(Reference.MODID + ":textures/gui/" + BUTTON_TEXTURE_NAME);
	private SkillIndex m_index;
	
	public void setSkillIndex(SkillIndex index)
	{
		m_index = index;
	}
	
	@Override
	protected void onButtonPressed(int mouseX, int mouseY)
	{
		super.onButtonPressed(mouseX, mouseY);
		EntityPlayer player = Minecraft.getMinecraft().player;
		RPGCharacterData charaterData = (RPGCharacterData)player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		if (charaterData.getSkillPointTracker().getAvailablePoints() > 0)
		{
			// TODO Change 1 to a user setting
			charaterData.spendSkillPoint(1, m_index);
		}
	}
	
	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY, float deltaSeconds)
	{
		super.drawComponent(mc, mouseX, mouseY, deltaSeconds);
		mc.getTextureManager().bindTexture(BUTTON_RESOURCE);
		drawTexturedModalRect(getActualLeft(), getActualTop(), 1, 1, WIDTH, HEIGHT);
	}
	
	@Override
	public int getWidth() 
	{
		return WIDTH;
	}

	@Override
	public int getHeight() 
	{
		return HEIGHT;
	}

}
