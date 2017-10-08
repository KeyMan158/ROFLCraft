package bruce.roflcraft.rpg.character.stats;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import scala.tools.nsc.settings.Final;

public class Attribute extends RPGStat
{	
	//private static final String NBT_STAT_TAG = "RPG Stat";
	private static final String NBT_ATT_TYPE_TAG = "Attribute Type";
	private static final String NBT_ATT_PROG_TAG = "Attribute Progress";
	
	private AttributeType m_attributeType;
	private int m_progressToNext;
	private final int PROGRESS_MAX = 3; // Temporary place holder
	
	public Attribute(String name, int value, AttributeType type) 
	{
		super(name, value);
		m_attributeType = type;
		m_progressToNext = 0;
	}
	
	/**
	 * Gets the attribute type
	 * @return
	 */
	public AttributeType getAttributeType()
	{
		return m_attributeType;
	}
	
	/**
	 * Gets the progress to the next attribute
	 * @return The progression to the next attribute
	 */
	public int getProgression()
	{
		return m_progressToNext;
	}
	
	/**
	 * Adds a progression point to the attribute
	 */
	public void addToProgression()
	{
		m_progressToNext++;
		if (m_progressToNext > PROGRESS_MAX)
		{
			addToValue(1);
			m_progressToNext = 0;
		}
	}
	
	@Override
	public NBTBase statToNBTData()
	{
		NBTTagCompound nbtData = (NBTTagCompound)super.statToNBTData();
		nbtData.setInteger(NBT_ATT_TYPE_TAG, m_attributeType.ordinal());
		nbtData.setInteger(NBT_ATT_PROG_TAG, m_progressToNext);
		return nbtData;
	}
	
	@Override
	public void statFromNBTData(NBTTagCompound nbtData)
	{
		super.statFromNBTData(nbtData);
		m_attributeType = AttributeType.values()[(nbtData.getInteger(NBT_ATT_TYPE_TAG))];
		m_progressToNext = nbtData.getInteger(NBT_ATT_PROG_TAG);
	}
}
