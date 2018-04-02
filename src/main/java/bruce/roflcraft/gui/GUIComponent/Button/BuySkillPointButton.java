package bruce.roflcraft.gui.GUIComponent.Button;

import bruce.roflcraft.main.Reference;
import bruce.roflcraft.rpg.character.RPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Button for buying skill points
 * @author Lorrtath
 *
 */
public class BuySkillPointButton  extends GUIButtonComponent
{
	private static final int HEIGHT = 15;
	private static final int WIDTH = 15;
	// TODO Replace this placeholder with the correct asset & animations etc..
	private static final String BUTTON_TEXTURE_NAME = "PlusButton.png";
	private static final ResourceLocation BUTTON_RESOURCE = new ResourceLocation(Reference.MODID + ":textures/gui/" + BUTTON_TEXTURE_NAME);
	
	@Override
	protected void onButtonPressed(int mouseX, int mouseY)
	{
		super.onButtonPressed(mouseX, mouseY);
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(player.experienceLevel > 0)
		{
			RPGCharacterData charaterData = (RPGCharacterData)player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
			// TODO Implement this in ROFLCraft User Settings object
			charaterData.PurchaseSkillPoint(1);
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
		return HEIGHT;
	}

	@Override
	public int getHeight() 
	{
		return WIDTH;
	}
}
