package bruce.roflcraft.gui.character;

import bruce.roflcraft.gui.IGUIComponent;
import bruce.roflcraft.main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Object for handling the level frame
 * @author Lorrtath
 */
@SideOnly(Side.CLIENT)
public class LevelFrame extends Gui implements IGUIComponent, IXPVisual
{
	private static final ResourceLocation RESOURCE = new ResourceLocation(Reference.MODID + Reference.PATH_TEXTURES_GUI + "LevelFrame.png");
	private static final int FRAME_LEFT = 0;
	private static final int FRAME_TOP = 0;
	private static final int FRAME_HEIGHT = 44;
	private static final int FRAME_WIDTH = 62;
	private static final int XP_HEIGHT = 5;
	private static final int XP_WIDTH_FULL = 61;
	private int m_left;
	private int m_top;
	private int m_xpWidth;
	private boolean m_visability;
	
	
	public LevelFrame(int top, int left, float xp)
	{
		m_top = top;
		m_left = left;
		m_xpWidth = (int)(XP_WIDTH_FULL * xp);
		m_visability = true;
	}

	@Override
	public void init() 
	{

	}

	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY) 
	{
		mc.getTextureManager().bindTexture(RESOURCE);
		drawTexturedModalRect(m_left, m_top, FRAME_LEFT, FRAME_TOP, FRAME_WIDTH, FRAME_HEIGHT);
	}

	@Override
	public int getTop() 
	{
		return m_top;
	}

	@Override
	public int getLeft() 
	{
		return m_left;
	}

	@Override
	public int getWidth() 
	{
		return FRAME_WIDTH;
	}

	@Override
	public int getHeight() 
	{
		return FRAME_HEIGHT;
	}

	@Override
	public boolean getVisibility() 
	{
		return m_visability;
	}

	@Override
	public void setVisibility(boolean visability) 
	{
		m_visability = visability;
	}

	@Override
	public void SetXPBar(float xp) 
	{
		m_xpWidth = (int)(XP_WIDTH_FULL * xp);
	}
}
