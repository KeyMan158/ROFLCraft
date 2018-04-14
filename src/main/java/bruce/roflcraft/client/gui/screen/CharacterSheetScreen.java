package bruce.roflcraft.client.gui.screen;

import java.util.List;

import bruce.roflcraft.client.gui.component.GUIComponentManager;
import bruce.roflcraft.client.gui.component.GUIComponentScreen;
import bruce.roflcraft.client.gui.component.button.BuySkillPointButton;
import bruce.roflcraft.client.gui.component.character.AtributeDialComponent;
import bruce.roflcraft.client.gui.component.character.AttributeFrame;
import bruce.roflcraft.client.gui.component.utl.GUITextureLayer;
import bruce.roflcraft.client.gui.component.utl.HorizontalAlignment;
import bruce.roflcraft.client.gui.component.utl.VerticalAlignment;
import bruce.roflcraft.client.gui.node.GUINodeComponent;
import bruce.roflcraft.client.gui.node.GUINodeNetworkTile;
import bruce.roflcraft.client.gui.node.GUINodeSide;
import bruce.roflcraft.client.gui.node.GUISkillNode;
import bruce.roflcraft.main.Reference;
import bruce.roflcraft.rpg.character.stats.AttributeIndex;
import bruce.roflcraft.rpg.character.stats.SkillIndex;
import net.minecraft.util.ResourceLocation;

/**
 * This class is the root for the charater screen
 * @author Lorrtath
 *
 */
public class CharacterSheetScreen extends GUIComponentScreen
{
	private GUIComponentManager m_mainComponentLayer;
	private GUIComponentManager m_nodeNetworkTilesLayer;
	
	
	public CharacterSheetScreen()
	{
		super();
				
		m_mainComponentLayer = new GUIComponentManager();
		m_nodeNetworkTilesLayer = new GUIComponentManager();
		
		AtributeDialComponent dial = new AtributeDialComponent();
		m_mainComponentLayer.register(dial);
		
		BuySkillPointButton skillPointButton = new BuySkillPointButton();
		m_mainComponentLayer.register(skillPointButton);
		
		AttributeFrame bodyFrame = new AttributeFrame(AttributeIndex.AT_BODY);
		bodyFrame.setTop(64);
		bodyFrame.setLeft(0);
		bodyFrame.registerListener(dial);
		m_mainComponentLayer.register(bodyFrame);
		
		AttributeFrame soulFrame = new AttributeFrame(AttributeIndex.AT_SOUL);
		soulFrame.setTop(-32);
		soulFrame.setLeft(55);
		soulFrame.registerListener(dial);
		m_mainComponentLayer.register(soulFrame);
		
		AttributeFrame mindFrame = new AttributeFrame(AttributeIndex.AT_MIND);
		mindFrame.setTop(-32);
		mindFrame.setLeft(-55);
		mindFrame.registerListener(dial);
		m_mainComponentLayer.register(mindFrame);
		
		/** ------ Initial implementation of node component system ------ */
		
		// Body Tiles
		GUINodeNetworkTile tile_1 = new GUINodeNetworkTile(GUINodeSide.NODE_TOP, GUINodeSide.NODE_BOTTOM);
		tile_1.setUseCenter(true);
		tile_1.setTop(bodyFrame.getTop() + 16);
		m_nodeNetworkTilesLayer.register(tile_1);
		GUINodeNetworkTile tile_2 = new GUINodeNetworkTile(GUINodeSide.NODE_TOP, GUINodeSide.NODE_LEFT);
		tile_2.setUseCenter(true);
		tile_2.setTop(tile_1.getTop() + 32);
		m_nodeNetworkTilesLayer.register(tile_2);
		GUINodeNetworkTile tile_3 = new GUINodeNetworkTile(GUINodeSide.NODE_TOP, GUINodeSide.NODE_RIGHT);
		tile_3.setUseCenter(true);
		tile_3.setTop(tile_1.getTop() + 32);
		m_nodeNetworkTilesLayer.register(tile_3);
		
		GUISkillNode node_mining = new GUISkillNode(SkillIndex.SKILL_MINING.ordinal());
		node_mining.setUseCenter(true);
		node_mining.setLeft(tile_3.getLeft() + 16);
		node_mining.setTop(tile_3.getTop());
		m_mainComponentLayer.register(node_mining);
		
		GUISkillNode node_forestry = new GUISkillNode(SkillIndex.SKILL_FORESTRY.ordinal());
		node_forestry.setUseCenter(true);
		node_forestry.setLeft(tile_3.getLeft() - 16);
		node_forestry.setTop(tile_3.getTop());
		m_mainComponentLayer.register(node_forestry);
		
		// MIND TILES
		GUINodeNetworkTile tile_4 = new GUINodeNetworkTile(GUINodeSide.NODE_BOTTOM, GUINodeSide.NODE_TOP);
		tile_4.setUseCenter(true);
		tile_4.setTop(mindFrame.getTop() - 16);
		tile_4.setLeft(mindFrame.getLeft());
		m_nodeNetworkTilesLayer.register(tile_4);
		GUINodeNetworkTile tile_5 = new GUINodeNetworkTile(GUINodeSide.NODE_BOTTOM, GUINodeSide.NODE_LEFT);
		tile_5.setUseCenter(true);
		tile_5.setTop(tile_4.getTop() - 32);
		tile_5.setLeft(tile_4.getLeft());
		m_nodeNetworkTilesLayer.register(tile_5);
		
		GUISkillNode node_farming = new GUISkillNode(SkillIndex.SKILL_FARMING.ordinal());
		node_farming.setUseCenter(true);
		node_farming.setLeft(tile_5.getLeft() - 16);
		node_farming.setTop(tile_5.getTop());
		m_mainComponentLayer.register(node_farming);
		
		// Soul TILES
		GUINodeNetworkTile tile_6 = new GUINodeNetworkTile(GUINodeSide.NODE_BOTTOM, GUINodeSide.NODE_TOP);
		tile_6.setUseCenter(true);
		tile_6.setTop(soulFrame.getTop() - 16);
		tile_6.setLeft(soulFrame.getLeft());
		m_nodeNetworkTilesLayer.register(tile_6);
		GUINodeNetworkTile tile_7 = new GUINodeNetworkTile(GUINodeSide.NODE_BOTTOM, GUINodeSide.NODE_RIGHT);
		tile_7.setUseCenter(true);
		tile_7.setTop(tile_6.getTop() - 32);
		tile_7.setLeft(tile_6.getLeft());
		m_nodeNetworkTilesLayer.register(tile_7);
		
		GUISkillNode node_hunting = new GUISkillNode(SkillIndex.SKIL_HUNTING.ordinal());
		node_hunting.setUseCenter(true);
		node_hunting.setLeft(tile_7.getLeft() + 16);
		node_hunting.setTop(tile_7.getTop());
		m_mainComponentLayer.register(node_hunting);		
	}
	
	@Override
	public void initGui()
	{
		registerComponent(m_nodeNetworkTilesLayer);
		registerComponent(m_mainComponentLayer);
		super.initGui();
	}
}
