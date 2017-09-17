package bruce.roflcraft.rpg.character;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * This class provides a data storage mechanism for the RPGCharacterData
 * object
 * @author Lorrtath
 */
public class RPGCharacterDataStorage implements IStorage<IRPGCharacterData>
{
	@Override
	public NBTBase writeNBT(Capability<IRPGCharacterData> capability, IRPGCharacterData instance, EnumFacing side) 
	{
		return instance.rpgCharacterToNBT();
	}

	@Override
	public void readNBT(Capability<IRPGCharacterData> capability, IRPGCharacterData instance, EnumFacing side,
			NBTBase nbt) 
	{
		instance.rpgCharacterFromNBT((NBTTagCompound)nbt);
	}
}
