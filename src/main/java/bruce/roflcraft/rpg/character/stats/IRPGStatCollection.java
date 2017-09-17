package bruce.roflcraft.rpg.character.stats;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;

public interface IRPGStatCollection 
{
	/**
	 * Gets the value of a RPGStat
	 * @param index The index of the RPGStat
	 * @return The value of the RPGStat
	 */
	public int getStatValue(int index);
	
	/**
	 * Sets the value of the RPG stat.
	 * @param index The index of the stat value to set
	 * @param value The new value of the RPG stat
	 */
	public void setStatValue(int index, int value);
	
	/**
	 * Gets the name of the RPGStat
	 * @param index The index of the RPGStat
	 * @return The name of the RPGStat
	 */
	public String getStatName(int index);
	
	/**
	 * Searches of the index of an RPGStat bases on the name
	 * @param Name The known name of the RPGStat
	 * @return The index value of the RPGStat. Returns -1 if nothing is founn
	 */
	public int getStatIndex(String Name);
	
	/**
	 * Applies a modifier to the RPGStat
	 * @param index The index of the RPGStat to modify
	 * @param modifier The modifier to apply
	 */
	public void applyModifier(int index, StatModifier modifier);
	
	/**
	 * Gets a count of the RPGStats within the collection
	 * @return The number of RPGStats
	 */
	public int count();
	
	/**
	 * Gets the RPGStat collection as NBTData
	 * @return
	 */
	public NBTBase collectionToNBT();
	
	/**
	 * Adds to the collection based on NBTData
	 * @param nbtData
	 */
	public void collectionFromNB(NBTTagList nbtData);
}
