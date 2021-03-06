package bruce.roflcraft.rpg.character.stats;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class AttributeCollection implements IRPGStatCollection
{
	private Attribute[] m_attributes;
	private final int ATTRIBUTE_COUNT = 3;
	private static final String[] NAMES = 
	{
		"BODY", 
		"MIND", 
		"SOUL"
	};

	public AttributeCollection()
	{
		m_attributes = new Attribute[ATTRIBUTE_COUNT];
		for(int i = 0; i < ATTRIBUTE_COUNT; i++)
		{
			m_attributes[i] = new Attribute(NAMES[i], 1);//, getAttributeType(AttributeIndex.values()[i]));
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

	@Override
	public int getStatModifiersValue(int index) 
	{
		if (index >= 0 && index < ATTRIBUTE_COUNT)
		{
			return m_attributes[index].getModifiersValue();
		}
		return 0;
	}

	@Override
	public int getStatModifiedValue(int index) 
	{
		if (index >= 0 && index < ATTRIBUTE_COUNT)
		{
			return m_attributes[index].getModifiedValue();
		}
		return 0;
	}
}
