package bruce.roflcraft.rpg.character.stats;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

/**
 * Class contains info on stat modification 
 */
public class StatModifier 
{
	private static final String NBT_NAME_TAG = "Name";
	private static final String NBT_VALUE_TAG = "Value";
	private static final String NBT_TIME_TAG = "Time";
	
	private String m_name;
	private int m_value;
	private float m_timeRemaining;
	
	/**
	 * @param value
	 * @param duration
	 */
	public StatModifier(String name, int value,float duration)
	{
		m_name = name;
		m_value = value;
		m_timeRemaining = duration;
	}
	
	/**
	 * Create a modifier from NBT Data
	 * @param nbtData The NBT data
	 */
	public StatModifier(NBTTagCompound nbtData)
	{
		modifierFromNBT(nbtData);
	}
	
	/**
	 * Gets the name of the modifier
	 * @return
	 */
	public String GetName()
	{
		return m_name;
	}
	
	/**
	 *  Gets the value of the modifier
	 * @return modifier value
	 */
	public int getValue()
	{
		return m_value;
	}
	
	/**
	 * Gets the remaining amount of time the modifier is 
	 * applicable
	 * @return Remaining ticks
	 */
	public float getRemainingTime()
	{
		return m_timeRemaining;
	}
	
	/**
	 * Removes a portion of the remaining duration from the modifier
	 * @param duration - The duration to remove
	 * @return True if there is no remaining duration
	 */
	public boolean reduceDuration(float duration)
	{
		m_timeRemaining -= duration;
		if (m_timeRemaining <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public NBTBase modifierToNBT()
	{
		NBTTagCompound nbtData = new NBTTagCompound();
		nbtData.setString(NBT_NAME_TAG, m_name);
		nbtData.setInteger(NBT_VALUE_TAG, m_value);
		nbtData.setFloat(NBT_TIME_TAG, m_timeRemaining);
		return nbtData;
	}
	
	/**
	 * Sets the modifier from NBT data
	 * @param nbtData The NBT data
	 */
	public void modifierFromNBT(NBTTagCompound nbtData)
	{
		m_name = nbtData.getString(NBT_NAME_TAG);
		m_value = nbtData.getInteger(NBT_VALUE_TAG);
		m_timeRemaining = nbtData.getFloat(NBT_TIME_TAG);
	}
}
