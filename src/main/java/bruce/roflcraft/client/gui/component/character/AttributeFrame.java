package bruce.roflcraft.client.gui.component.character;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import bruce.roflcraft.client.gui.component.GUIComponentBase;
import bruce.roflcraft.client.gui.component.GUIComponentScreen;
import bruce.roflcraft.client.gui.component.IGUIComponent;
import bruce.roflcraft.client.gui.component.utl.GUITextureLayer;
import bruce.roflcraft.client.gui.component.utl.HorizontalAlignment;
import bruce.roflcraft.client.gui.component.utl.MouseCollisionDetectorCircle;
import bruce.roflcraft.client.gui.component.utl.MouseCollisionMode;
import bruce.roflcraft.client.gui.component.utl.VerticalAlignment;
import bruce.roflcraft.main.Reference;
import bruce.roflcraft.rpg.character.RPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import bruce.roflcraft.rpg.character.stats.AttributeCollection;
import bruce.roflcraft.rpg.character.stats.AttributeIndex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiEditArrayEntries.IArrayEntry;

/**
 * This class provides the GUI for an attribute
 * within the character sheet interface
 * @author Lorrtath
 *
 */
public class AttributeFrame extends GUIComponentBase implements IAttributeBasedComponent
{
	private static final String FRAME_TEXTURE_NAME = "character_grid.png";
	private static final String XP_TEXTURE_NAME = "character_sheet_details.png";
	private static final String GUI_TEXTURE_FOLDER = "textures/gui/";

	private static final int FRAME_HEIGHT = 64;
	private static final int FRAME_WIDTH = 64;
	private static final int XP_WIDTH = 32;
	private static final int XP_HEIGHT = 32;
	private static final float ROTATION_INTERVAL = 120f;
	private static final float HIT_RADIUS = 18;
	
	private AttributeIndex m_attribute;
	private AttributeCollection m_attributes;
	private List<IAttributeControlledComponent> m_attributeDependancies;
	private GUITextureLayer m_frameTextureLayer;
	private GUITextureLayer m_xpFrameLayer;
	private GUITextureLayer m_xpBarLayer;
	
	/** ------------- No Longer Used: ---------------- 
	
	private static final ResourceLocation FRAME_RESOURCE_LOCATION = new ResourceLocation(Reference.MODID , "textures/gui/" + FRAME_TEXTURE_NAME);
	private static final ResourceLocation XP_RESOURCE_LOCATION = new ResourceLocation(Reference.MODID , "textures/gui/" + XP_TEXTURE_NAME);
	private static final int FRAME_BACK_TOP = 16;
	private static final int FRAME_BACK_LEFT = 64;
	private static final int FRAME_BACK_HEIGHT = 32;
	private static final int FRAME_BACK_WIDTH = 32;
	private static final int FRAME_BACK_MARGIN = 16;
	private static final int FRAME_BACK_COUNT = 6;
	private static final float FRAME_BACK_TICK_DUR = 3f;*/
	
	public AttributeFrame(AttributeIndex attribute)
	{
		m_attribute = attribute;
		m_attributeDependancies = new ArrayList<IAttributeControlledComponent>();
		setRotation(ROTATION_INTERVAL * attribute.ordinal()); 
		setUseCenter(true);
		setCollisionMode(MouseCollisionMode.MOUSE_COLLISION_CIRCLE);
		MouseCollisionDetectorCircle detector = (MouseCollisionDetectorCircle)getCollisionDetector();
		detector.setHitRadius(XP_WIDTH/2);
		setUseToolTip(true);
		
		RPGCharacterData characterData = (RPGCharacterData)Minecraft.getMinecraft().player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		m_attributes = characterData.getAttributes();
	
		m_frameTextureLayer = new GUITextureLayer();
		m_frameTextureLayer.TextureResource = new ResourceLocation(Reference.MODID , GUI_TEXTURE_FOLDER + FRAME_TEXTURE_NAME);
		m_frameTextureLayer.HAlignment = HorizontalAlignment.Center;
		m_frameTextureLayer.VAlignment = VerticalAlignment.Middle;
		m_frameTextureLayer.Height = FRAME_HEIGHT;
		m_frameTextureLayer.Width = FRAME_WIDTH;
		AddResource(m_frameTextureLayer);
		
		m_xpBarLayer = new GUITextureLayer();
		m_xpBarLayer.TextureResource = new ResourceLocation(Reference.MODID , GUI_TEXTURE_FOLDER + XP_TEXTURE_NAME);
		m_xpBarLayer.VAlignment = VerticalAlignment.Middle;
		m_xpBarLayer.Height = XP_HEIGHT;
		m_xpBarLayer.V = XP_HEIGHT;
		m_xpBarLayer.PivotX = XP_WIDTH / -2;
		AddResource(m_xpBarLayer);
		
		m_xpFrameLayer = new GUITextureLayer();
		m_xpFrameLayer.TextureResource = new ResourceLocation(Reference.MODID , GUI_TEXTURE_FOLDER + XP_TEXTURE_NAME);
		m_xpFrameLayer.HAlignment = HorizontalAlignment.Center;
		m_xpFrameLayer.VAlignment = VerticalAlignment.Middle;
		m_xpFrameLayer.Height = XP_HEIGHT;
		AddResource(m_xpFrameLayer);
	}
	
	@Override
	protected void handle_MouseEnter()
	{
		broadcastAttribute();
	}
	
	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY, float deltaSeconds) 
	{
		m_xpBarLayer.Width = m_attributes.getAttributeProgress(m_attribute) * XP_WIDTH / 3;
		List<String> textLines = new ArrayList<String>();
		textLines.add(m_attributes.getStatName(m_attribute.ordinal()));
		textLines.add("Level: " + m_attributes.getStatValue(m_attribute.ordinal()));
		textLines.add("Progress: " + m_attributes.getAttributeProgress(m_attribute) + "/3");
		setToolTipText(textLines);
		super.drawComponent(mc, mouseX, mouseY, deltaSeconds);
		/*if(checkMouseCollision(mouseX, mouseY))
		{
			broadcastAttribute();
			List<String> textLines = new ArrayList<String>();
			textLines.add(m_attributes.getStatName(m_attribute.ordinal()));
			textLines.add("Level: " + m_attributes.getStatValue(m_attribute.ordinal()));
			textLines.add("Progress: " + m_attributes.getAttributeProgress(m_attribute) + "/3");
			getRoot().drawHoveringText(textLines, mouseX + 8, mouseY);
		}*/
	}
	
	@Override
	public void registerListener(IAttributeControlledComponent listener) 
	{
		m_attributeDependancies.add(listener);
	}

	@Override
	public void unregisterListener(IAttributeControlledComponent listener) 
	{
		m_attributeDependancies.remove(listener);
	}

	@Override
	public void setAttribute(AttributeIndex index) 
	{
		m_attribute = index;
	}

	@Override
	public AttributeIndex getAttribute() 
	{
		return m_attribute;
	}
	
	@Override
	public void broadcastAttribute() 
	{
		for(int i = 0; i < m_attributeDependancies.size(); i++)
		{
			m_attributeDependancies.get(i).changeAttribute(m_attribute, this);
		}
	}
	
	/*private boolean checkMouseCollision(int mouseX, int mouseY)
	{
		double distance = Math.sqrt(0.5 * (Math.pow(mouseX - getActualLeft(), 2) + Math.pow(mouseY - getActualTop(), 2)));
		if(HIT_RADIUS < distance)
		{
			return false;
		}
		return true;
	}*/
}
