package bruce.roflcraft.rpg.character.stats;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants.NBT;

public class RPGStat 
{
	public static final String NBT_VALUE_TAG = "Value";
	public static final String NBT_NAME_TAG = "Name";
	private static final String NBT_MODIFIERS_TAG = "Modifiers";
	
	private List<StatModifier> m_modifiers;
	private String m_name;
	private int m_value;
	
	/**
	 * Constructor
	 * @param name
	 * @param value
	 */
	public RPGStat(String name, int value)
	{
		m_name = name;
		m_value = value;
		m_modifiers = new ArrayList<StatModifier>();
	}
	
	/**
	 * applies a modifier to the RPGStat
	 * @param modifier : The modifier to apply
	 */
	public void addModifier(StatModifier modifier)
	{
		m_modifiers.add(modifier);
	}
	
	/**
	 * Gets the vale of the RPGStat
	 * @return
	 */
	public int getValue()
	{
		return m_value;
	}

	/**
	 * Gets the current modifiers value
	 * @return The combined value of all modifiers
	 */
	public int getModifiersValue()
	{
		int value = 0;
		for (int i = 0; i < m_modifiers.size(); i++)
		{
			value += m_modifiers.get(i).getValue();
		}
		return value;
	}
	
	/**
	 * Gets the fully modified RPGStat value
	 * @return The stat value with all modifiers applied
	 */
	public int getModifiedValue()
	{
		return getValue() + getModifiersValue();
	}
	
	/**
	 * Gets the name of the RPGStat
	 * @return
	 */
	public String getName()
	{
		return m_name;
	}
	
	/**
	 * Changes the value of the RPGStat
	 * @param value : The value of the modification
	 */
	public void addToValue(int value)
	{
		m_value += value;
	}
	
	/**
	 * Casts this object as an RPGStat object
	 * @return This object as an RPGStat object 
	 */
	public RPGStat toRPGStat()
	{
		return this;
	}
	
	/**
	 * Method to be called every player tick
	 * @param duration The duration since the last tick
	 */
	public void onPlayerTick(int duration)
	{
		for (int i = m_modifiers.size() - 1 ; i >= 0 ; i--)
		{
			boolean result = m_modifiers.get(i).reduceDuration(duration);
			if (result)
			{
				m_modifiers.remove(i);
			}
		}
	}
	
	/**
	 * ----------- THIS IS NOT USED -----------!
	 * Gets the number of XP levels to buy a skill point
	 * @return
	 */
	//public int pointsToNextLevel()
	//{
	//	return toLevel(m_value);
	//}
	
	/**
	 * Gets the number of XP levels to buy a skill point
	 * @param currentLevel The level to check against
	 * @return
	 */
	public static int toLevel(int currentLevel)
	{
		if (currentLevel < 1)
		{
			return 1;
		}
		return (int) (1 + Math.floor(0.05 * Math.pow(currentLevel, 2)));
	}
	
	/**
	 * Converts the RPGStat to NBTData and returns it
	 * @return
	 */
	public NBTBase statToNBTData()
	{
		NBTTagCompound statData = new NBTTagCompound();
		statData.setString(NBT_NAME_TAG, m_name);
		statData.setInteger(NBT_VALUE_TAG, m_value);
		NBTTagList modifiersData = new NBTTagList();
		for (int i = 0; i < m_modifiers.size(); i++)
		{
			modifiersData.appendTag(m_modifiers.get(i).modifierToNBT());
		}
		statData.setTag(NBT_MODIFIERS_TAG, modifiersData);
		return statData;
	}
	
	/**
	 * Sets the RPGStat from NBTData
	 * @param nbtData
	 */
	public void statFromNBTData(NBTTagCompound nbtData)
	{
		m_name = nbtData.getString(NBT_NAME_TAG);
		m_value = nbtData.getInteger(NBT_VALUE_TAG);
		NBTTagList modifierData = nbtData.getTagList(NBT_MODIFIERS_TAG,NBT.TAG_COMPOUND);
		for (int i = 0; i < modifierData.tagCount(); i++)
		{
			m_modifiers.add(new StatModifier(modifierData.getCompoundTagAt(i)));
		}
	}
}
