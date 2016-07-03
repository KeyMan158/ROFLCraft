package bruce.roflcraft.handlers;

import bruce.roflcraft.gui.CharacterSheetGUI;
import bruce.roflcraft.settings.GUIIDs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUIIDs.CharacterSheet.ordinal())
		{
			return new CharacterSheetGUI();
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUIIDs.CharacterSheet.ordinal())
		{
			return new CharacterSheetGUI();
		}
		return null;
	}

}
