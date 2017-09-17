package bruce.roflcraft.rpg.character;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;

public class RPGCharacterProvider implements ICapabilitySerializable<NBTBase>
{
	@CapabilityInject(IRPGCharacterData.class)
	public static final Capability<IRPGCharacterData> CHAR_CAP = null;
	
	private IRPGCharacterData instance = CHAR_CAP.getDefaultInstance();
	
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		return capability == CHAR_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		return capability == CHAR_CAP ? CHAR_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() 
	{
		return CHAR_CAP.getStorage().writeNBT(CHAR_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) 
	{
		CHAR_CAP.getStorage().readNBT(CHAR_CAP, this.instance, null, nbt);
	}
	
}
