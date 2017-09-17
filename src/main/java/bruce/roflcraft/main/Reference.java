package bruce.roflcraft.main;

public class Reference 
{
	public static final String MODID = "rofl";
	public static final String NAME = "ROFLCraft";
	public static final String VERSION = "1.9.0-0.0.0.1";
	
	public static final String PATH_TEXTURES_GUI = ":textures/gui/";
	
	public static final String CLIENTPROXY = "bruce.roflcraft.proxy.ClientProxy";
	public static final String COMMONPROXY = "bruce.roflcraft.proxy.CommonProxy";
	
	/**
	 * Calculates the experience to the level after the one passed in
	 * based upon EntityPlayer::xpBarCap()
	 * @param currentLevel the 
	 */
	public static int playerXPToNextLevel(int currentLevel)
	{
		return currentLevel >= 30 ? 112 + (currentLevel - 30) * 9 : (currentLevel >= 15 ? 37 + (currentLevel - 15) * 5 : 7 + currentLevel * 2);
	}
}
