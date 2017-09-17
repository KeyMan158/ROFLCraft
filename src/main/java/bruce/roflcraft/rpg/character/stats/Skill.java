package bruce.roflcraft.rpg.character.stats;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Base class for all skills
 * @author Lorrtath
 */
public class Skill extends RPGStat
{
	private AttributeIndex m_primaryAttribute;
	private AttributeIndex m_secondaryAttribute;
	private SkillType m_skillType;
	private static final String NBT_PRIMARY_TAG = "PrimaryAttr";
	private static final String NBT_SECONDARY_TAG = "SecondaryAttr";
	
	public Skill(String name, int value, AttributeIndex primaryAttr, AttributeIndex secndAttr, SkillType skillType) 
	{
		super(name, value);
		m_primaryAttribute = primaryAttr;
		m_secondaryAttribute = secndAttr;
		m_skillType = skillType;
	}
	
	public Skill(NBTTagCompound nbtData)
	{
		super("", 0);
		SkillFromNBT(nbtData);
	}
	
	/**
	 * Gets the primary attribute for the skill
	 * @return The primary Attribute
	 */
	public AttributeIndex getPrimaryAttribute()
	{
		return m_primaryAttribute;
	}
	
	/**
	 * Gets the secondary attribute for the skill
	 * @return The secondary Attribute
	 */
	public AttributeIndex getSecondaryAttribute()
	{
		return m_secondaryAttribute;
	}
	
	/**
	 * Gets the skill type
	 * @return The skill type
	 */
	public SkillType getSkillType()
	{
		return m_skillType;
	}
	
	public NBTBase SkillToNBT()
	{
		NBTTagCompound nbtData = (NBTTagCompound)super.statToNBTData();
		nbtData.setInteger(NBT_PRIMARY_TAG, m_primaryAttribute.ordinal());
		nbtData.setInteger(NBT_SECONDARY_TAG, m_secondaryAttribute.ordinal());
		return nbtData;
	}
	
	public void SkillFromNBT(NBTTagCompound nbtData)
	{
		super.statFromNBTData(nbtData);
		m_primaryAttribute = AttributeIndex.values()[nbtData.getInteger(NBT_PRIMARY_TAG)];
		m_secondaryAttribute = AttributeIndex.values()[nbtData.getInteger(NBT_SECONDARY_TAG)];
	}
}
