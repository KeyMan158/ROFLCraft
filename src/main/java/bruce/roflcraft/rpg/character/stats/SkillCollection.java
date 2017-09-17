package bruce.roflcraft.rpg.character.stats;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SkillCollection implements IRPGStatCollection
{
	private List<Skill> m_skills;
	// This bit is a mess ...
	private static String[] NAMES = 
		{
			"Nature",
			"Mining",
			"Digging",
			"Construction",
			"Mele Damage",
			"Mele Speed",
			"Blocking",
			"Archery",
			"Carpentry",
			"Smithing",
			"Masonary",
			"Survival",
			"Schollarship"
		};
	private static AttributeIndex[] PRIMARYS = 
		{
			AttributeIndex.AT_WIS, 
			AttributeIndex.AT_STR,
			AttributeIndex.AT_CON,
			AttributeIndex.AT_INT,
			AttributeIndex.AT_STR,
			AttributeIndex.AT_AGI,
			AttributeIndex.AT_CON,
			AttributeIndex.AT_AGI,
			AttributeIndex.AT_AGI,
			AttributeIndex.AT_STR,
			AttributeIndex.AT_CON,
			AttributeIndex.AT_WIS,
			AttributeIndex.AT_INT
		};
	private static AttributeIndex[] SECONDARIES = 
		{
			AttributeIndex.AT_INT,
			AttributeIndex.AT_CON,
			AttributeIndex.AT_STR,
			AttributeIndex.AT_CON,
			AttributeIndex.AT_CHA,
			AttributeIndex.AT_CON,
			AttributeIndex.AT_INT,
			AttributeIndex.AT_WIS,
			AttributeIndex.AT_CHA,
			AttributeIndex.AT_CHA,
			AttributeIndex.AT_CHA,
			AttributeIndex.AT_INT,
			AttributeIndex.AT_WIS
		};
	private static SkillType[] TYPES = 
		{
			SkillType.SKILL_TYPE_GATHERING,
			SkillType.SKILL_TYPE_GATHERING,
			SkillType.SKILL_TYPE_GATHERING,
			SkillType.SKILL_TYPE_GATHERING,
			SkillType.SKILL_TYPE_COMBAT,
			SkillType.SKILL_TYPE_COMBAT,
			SkillType.SKILL_TYPE_COMBAT,
			SkillType.SKILL_TYPE_COMBAT,
			SkillType.SKILL_TYPE_CRAFTING,
			SkillType.SKILL_TYPE_CRAFTING,
			SkillType.SKILL_TYPE_CRAFTING,
			SkillType.SKILL_TYPE_CRAFTING,
			SkillType.SKILL_TYPE_CRAFTING
		};
	

	public SkillCollection()
	{
		m_skills = new ArrayList<Skill>();
		for (int i = 0; i < NAMES.length; i++)
		{
			m_skills.add(new Skill(NAMES[i],0, PRIMARYS[i], SECONDARIES[i], TYPES[i]));
		}
	}
	
	/**
	 * Adds a skill to the collection
	 * @param skill The skill to add
	 * @return False if a skill with this name already exists
	 */
	public boolean AddSkill(Skill skill)
	{
		if(getStatIndex(skill.getName()) < 0)
		{
			m_skills.add(skill);
			return true;
		}
		else
		{
			// A skill with this name already exists...
			return false;
		}
	}
	
	/**
	 * Gets the value of the skill
	 * @param index The index of the skill
	 * @return The value of the skill. -1 if the skill is not found
	 */
	@Override
	public int getStatValue(int index) 
	{
		if (index >= 0 && index < m_skills.size())
		{
			return m_skills.get(index).getValue();
		}
		else
		{
			return -1;
		}
	}
	
	@Override
	public void setStatValue(int index, int value)
	{
		if (index >= 0 && index < m_skills.size())
		{
			m_skills.get(index).addToValue(value - m_skills.get(index).getValue());
		}
	}

	/**
	 * Gets the name of a skill
	 * @param index The index of the skill
	 * @return The name of the skill. Will return "" if the skill dose not exist
	 */
	@Override
	public String getStatName(int index) 
	{
		if (index >= 0 && index < m_skills.size())
		{
			return m_skills.get(index).getName();
		}
		else
		{
			return "";
		}
	}

	/**
	 * Gets the index of the skill
	 * @param Name The name of the skill
	 * @return The index of the skill. Will return -1 if not found
	 */
	@Override
	public int getStatIndex(String Name) 
	{
		int index = -1;
		for (int i = 0; i < m_skills.size(); i++)
		{
			if(m_skills.get(i).getName() == Name)
			{
				index = i;
				return index;
			}
		}
		return index;
	}

	/**
	 * Applies a modifier to the skill
	 * @param index The index of the skill to modify
	 * @param modifier The modifier to apply
	 */
	@Override
	public void applyModifier(int index, StatModifier modifier) 
	{
		if (index >= 0 && index < m_skills.size())
		{
			m_skills.get(index).addModifier(modifier);
		}
	}

	/**
	 * Gets the count of skills
	 * @return The number of skills stored
	 */
	@Override
	public int count() 
	{
		return m_skills.size();
	}

	/**
	 * Returns the collection in the NBT format
	 * @return The collection in NBT format
	 */
	@Override
	public NBTBase collectionToNBT() 
	{
		NBTTagList nbtData = new NBTTagList();
		for (int i = 0; i < count(); i++)
		{
			nbtData.appendTag(m_skills.get(i).SkillToNBT());
		}
		return nbtData;
	}

	/**
	 * Sets the collection from NBT Format
	 * @param nbtData The NBT data
	 */
	@Override
	public void collectionFromNB(NBTTagList nbtData) 
	{
		for (int i = 0; i < count(); i++)
		{
			m_skills.get(i).SkillFromNBT((NBTTagCompound)nbtData.get(i));
			//setStatValue(i, ((NBTTagCompound)nbtData.get(i)).getInteger(RPGStat.NBT_VALUE_TAG));
		}
	}

}
