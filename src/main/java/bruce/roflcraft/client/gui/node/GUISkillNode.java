package bruce.roflcraft.client.gui.node;

import java.util.ArrayList;
import java.util.List;

import bruce.roflcraft.client.gui.component.utl.GUITextureLayer;
import bruce.roflcraft.client.gui.component.utl.HorizontalAlignment;
import bruce.roflcraft.client.gui.component.utl.MouseCollisionDetectorCircle;
import bruce.roflcraft.client.gui.component.utl.VerticalAlignment;
import bruce.roflcraft.main.Reference;
import bruce.roflcraft.rpg.character.RPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import bruce.roflcraft.rpg.character.stats.Skill;
import bruce.roflcraft.rpg.character.stats.SkillIndex;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GUISkillNode extends GUINodeComponent
{
	private int skillIndex;
	private static final String TEXTURE_RESOURCE = "character_grid.png"; 
	private static final int DEFAULT_U = 0;
	private static final int DEFAULT_V = 64;
	
	public GUISkillNode(int skill)
	{
		super();
		this.skillIndex = skill;
		((MouseCollisionDetectorCircle)getCollisionDetector()).setHitRadius(11);
		GUITextureLayer layer = new GUITextureLayer();
		layer.TextureResource = new ResourceLocation(Reference.MODID , "textures/gui/" + TEXTURE_RESOURCE);
		layer.U = DEFAULT_U;
		layer.V = DEFAULT_V;
		layer.HAlignment = HorizontalAlignment.Center;
		layer.VAlignment = VerticalAlignment.Middle;
		AddResource(layer);
	}
	
	@Override
	protected void onButtonPressed(int mouseX, int mouseY)
	{
		super.onButtonPressed(mouseX, mouseY);
		EntityPlayer player = Minecraft.getMinecraft().player;
		RPGCharacterData charaterData = (RPGCharacterData)player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		if (charaterData.getSkillPointTracker().getAvailablePoints() > 0)
		{
			/** 
			 * There needs to be an override of this method based on the int value so that  
			 * non-core skills can be used
			 * */
			charaterData.spendSkillPoint(1, SkillIndex.values()[skillIndex]);
		}
	}
	
	@Override
	public List<String> getToolTipText()
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		RPGCharacterData charaterData = (RPGCharacterData)player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		List<String> text = new ArrayList<String>();
		text.add(charaterData.getSkills().getStatName(skillIndex));
		text.add("Level: " + charaterData.getSkills().getStatValue(skillIndex));
		return text;
	}
}
