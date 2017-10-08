package bruce.roflcraft.rpg.character.stats;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class AttributeCollection implements IRPGStatCollection
{
	private Attribute[] m_attributes;
	private final int ATTRIBUTE_COUNT = 8;
	private static final String[] NAMES = 
	{
		"STR", 
		"AGI", 
		"CON", 
		"INT", 
		"WIS", 
		"CHA", 
		"LUK", 
		"MGK"
	};

	public AttributeCollection()
	{
		m_attributes = new Attribute[ATTRIBUTE_COUNT];
		for(int i = 0; i < ATTRIBUTE_COUNT; i++)
		{
			m_attributes[i] = new Attribute(NAMES[i], 1, getAttributeType(AttributeIndex.values()[i]));
		}
	}
	
	/**
	 * Gets the attribute type
	 * @param attribute
	 * @return
	 */
	public static AttributeType getAttributeType(AttributeIndex attribute)
	{
		switch (attribute) 
		{
		case AT_STR:
			return AttributeType.BODY;
		case AT_AGI:
			return AttributeType.BODY;
		case AT_CON:
			return AttributeType.BODY;
		case AT_INT:
			return AttributeType.MIND;
		case AT_WIS:
			return AttributeType.MIND;
		case AT_CHA:
			return AttributeType.MIND;
		case AT_LUK:
			return AttributeType.SOUL;
		case AT_MGK:
			return AttributeType.SOUL;
		default:
			return null;
		}
	}

	/**
	 * Progresses an attribute by am amount
	 * @param index The attribute to progress
	 */
	public void progressAttribute(AttributeIndex index)
	{
		m_attributes[index.ordinal()].addToProgression();
	}
	
	public int getAttributeProgress(AttributeIndex index)
	{
		return m_attributes[index.ordinal()].getProgression();
	}
	
	@Override
	public int getStatValue(int index) 
	{
		return m_attributes[index].getValue();
	}
	
	@Override
	public void setStatValue(int index, int value)
	{
		if (index >= 0 && index < ATTRIBUTE_COUNT)
		{
			m_attributes[index].addToValue(value - m_attributes[index].getValue());
		}
	}

	@Override
	public String getStatName(int index) 
	{
		return NAMES[index];
	}
	
	@Override
	public int getStatIndex(String Name) 
	{
		int index = -1;
		for (int i = 0; i < ATTRIBUTE_COUNT; i++)
		{
			if(NAMES[i] == Name)
			{
				index = i;
				return index;
			}
		}
		return index;
	}

	@Override
	public void applyModifier(int index, StatModifier modifier) 
	{
		m_attributes[index].addModifier(modifier);
	}

	@Override
	public int count() 
	{
		return ATTRIBUTE_COUNT;
	}

	@Override
	public NBTBase collectionToNBT() 
	{
		NBTTagList nbtData = new NBTTagList();
		for (int i = 0; i < count(); i++)
		{
			nbtData.appendTag(m_attributes[i].statToNBTData());
		}
		return nbtData;
	}

	@Override
	public void collectionFromNB(NBTTagList nbtData) 
	{
		for (int i = 0; i < count(); i++)
		{
			m_attributes[i].statFromNBTData((NBTTagCompound)nbtData.get(i));
		}
	}
}
