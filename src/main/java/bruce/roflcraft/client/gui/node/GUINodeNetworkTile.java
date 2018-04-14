package bruce.roflcraft.client.gui.node;

import bruce.roflcraft.client.gui.component.GUIComponentBase;
import bruce.roflcraft.client.gui.component.utl.GUITextureLayer;
import bruce.roflcraft.client.gui.component.utl.HorizontalAlignment;
import bruce.roflcraft.client.gui.component.utl.VerticalAlignment;
import bruce.roflcraft.main.Reference;
import net.minecraft.util.ResourceLocation;

/**
 * This class acts as the tiles used as the network lines 
 * for a node network
 * 
 * This is just an initial version and shall be overhauled
 * with the full node system
 * @author Lorrtath
 *
 */
public class GUINodeNetworkTile extends GUIComponentBase
{
	private static final int TILE_SIZE = 32;
	private static final String TEXTURE_RESOURCE = "character_grid.png"; 
	private static final int DEFAULT_U = 0;
	private static final int DEFAULT_V = 128;
	
	public GUINodeNetworkTile(GUINodeSide inputSide, GUINodeSide outputSide)
	{
		GUITextureLayer layer = new GUITextureLayer();
		layer.TextureResource = new ResourceLocation(Reference.MODID , "textures/gui/" + TEXTURE_RESOURCE);
		layer.U = DEFAULT_U + inputSide.ordinal() * TILE_SIZE;
		layer.V = DEFAULT_V + outputSide.ordinal() * TILE_SIZE;
		layer.HAlignment = HorizontalAlignment.Center;
		layer.VAlignment = VerticalAlignment.Middle;
		AddResource(layer);
	}
}
