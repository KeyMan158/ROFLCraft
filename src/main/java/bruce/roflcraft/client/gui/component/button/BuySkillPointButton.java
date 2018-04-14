package bruce.roflcraft.client.gui.component.button;

import java.util.ArrayList;
import java.util.List;

import bruce.roflcraft.client.gui.component.utl.GUITextureLayer;
import bruce.roflcraft.client.gui.component.utl.HorizontalAlignment;
import bruce.roflcraft.client.gui.component.utl.MouseCollisionMode;
import bruce.roflcraft.client.gui.component.utl.VerticalAlignment;
import bruce.roflcraft.main.Reference;
import bruce.roflcraft.rpg.character.RPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Specific extension of the GUI button component for 
 * purchasing a skill point.
 * @author Lorrtath
 *
 */
public class BuySkillPointButton  extends GUIButtonComponent
{
	private static final int HEIGHT = 32;
	private static final int WIDTH = 32;
	private static final int LEFT = 0;
	// TODO Replace this placeholder with the correct asset & animations etc..
	private static final String BUTTON_TEXTURE_NAME = "character_sheet_details.png";
	private static final ResourceLocation BUTTON_RESOURCE = new ResourceLocation(Reference.MODID + ":textures/gui/" + BUTTON_TEXTURE_NAME);
	private RPGCharacterData m_characterData;
	
	public BuySkillPointButton()
	{
		super();
		GUITextureLayer texture = new GUITextureLayer();
		texture.TextureResource =  new ResourceLocation(Reference.MODID + ":textures/gui/" + BUTTON_TEXTURE_NAME);
		texture.HAlignment = HorizontalAlignment.Center;
		texture.VAlignment = VerticalAlignment.Middle;
		texture.U = WIDTH;
		AddResource(texture);
		m_characterData = (RPGCharacterData)Minecraft.getMinecraft().player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		setUseCenter(true);
		setUseToolTip(true);
		setCollisionMode(MouseCollisionMode.MOUSE_COLLISION_CIRCLE);
	}
	
	@Override
	protected void onButtonPressed(int mouseX, int mouseY)
	{
		super.onButtonPressed(mouseX, mouseY);
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(player.experienceLevel > 0)
		{
			m_characterData.PurchaseSkillPoint(1);
		}
	}
	
	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY, float deltaSeconds)
	{
		List<String> text = new ArrayList<String>();
		EntityPlayer player = Minecraft.getMinecraft().player;
		text.add("Level: " + m_characterData.getSkillPointTracker().getLevel());
		text.add("Points Available: " + m_characterData.getSkillPointTracker().getAvailablePoints());
		text.add("XP: " +  m_characterData.getSkillPointTracker().getStoredXP() + " / " + m_characterData.getSkillPointTracker().xpToNext());
		setToolTipText(text);
		super.drawComponent(mc, mouseX, mouseY, deltaSeconds);
	}
}
