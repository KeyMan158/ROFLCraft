package bruce.roflcraft.client.gui.component.utl;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureVillagePieces.Hall;

/**
 * This class acts as a rendered layer in a GUI component 
 * and contains storage for a resource location in use 
 * for a GUI texture and * its transform properties.
 * @author Lorrtath
 *
 */
public class GUITextureLayer 
{
	/** 
	 * Resource location for the texture used 
	 */
	public ResourceLocation TextureResource;
	
	/**
	 * The vertical alignment of the texture to its datum
	 */
	public VerticalAlignment VAlignment;
	
	/**
	 * The horizontal alignment of the texture to its datum
	 */
	public HorizontalAlignment HAlignment;
	
	/**
	 * The U Position to use when drawing the texture in the texture resource
	 */
	public int U;
	
	/**
	 * The V Position to use when drawing the texture in the texture resource
	 */
	public int V;
	
	/**
	 * The width of the texture
	 */
	public int Width;
	
	/**
	 * The height of the texture
	 */
	public int Height;
	
	/**
	 * The X pivot position 
	 */
	public int PivotX;
	
	/**
	 * The Y pivot position
	 */
	public int PivotY;
	
	public GUITextureLayer()
	{
		VAlignment = VerticalAlignment.Top;
		HAlignment = HorizontalAlignment.Left;
		U = 0;
		V = 0;
		Width = 32;
		Height = 32;
		PivotX = 0;
		PivotY = 0;
	}
	
	/**
	 * Gets the top position of the texture
	 * @return The top position
	 */
	public int getTop()
	{
		switch (VAlignment)
		{
		default:
			return 0;
		case Top:
			return 0 + PivotY;
		case Middle:
			return (Height / -2) + PivotY;
		case Bottom:
			return (Height * -1) + PivotY;
		}
	}
	
	/**
	 * Gets the Left position of the texture
	 * @return The left position of the texture
	 */
	public int getLeft()
	{
		switch (HAlignment)
		{
		case Center:
			return (Width / -2) + PivotX;
		case Left:
			return 0 + PivotX;
		case Right:
			return (Width * -1) + PivotX;
		default:
			return 0;
		}
	}
}
