package bruce.roflcraft.rpg.character.stats;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

/**InPrimar
 * Base abstract class for all skills
 * All implementing child classes should use a static 
 * subscribing event as they are registered in a static list
 * this will prevent high numbers of repeated calls
 * @author Lorrtath
 */
public abstract class Skill extends RPGStat
{
	private AttributeIndex m_primaryAttribute;
	private SkillType m_skillType;
	private int m_rollTableIndex;
	private static final String NBT_PRIMARY_TAG = "PrimaryAttr";
	
	public Skill(String name, int value, AttributeIndex attr, SkillType skillType) 
	{
		super(name, value);
		m_primaryAttribute = attr;
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
		return nbtData;
	}
	
	public void SkillFromNBT(NBTTagCompound nbtData)
	{
		super.statFromNBTData(nbtData);
		m_primaryAttribute = AttributeIndex.values()[nbtData.getInteger(NBT_PRIMARY_TAG)];
	}
	
	/**
	 * Provides a default threshold effect description for a given index 
	 * @param index The index of the threshold
	 * @return The default description of the threshold
	 */
	public abstract String getThresholdDescription(int index);	
	
	/**
	 * Get the description of this skill
	 * @return The skill description
	 */
	public abstract String getSkillDescription();
	
	/**
	 * Gets the index of the skill. If this is one of the core skills then this will be
	 * the ordinal value from that the skill index uses. This should also correspond 
	 * with the order in which the skills are registered!
	 * @return The index number of the skill
	 */
	public abstract int getIndexOfSkill();
	
	/**
	 * Tells the skill which roll table to use. This is used
	 * to prevent re-iteration of all tables for each roll
	 * @param index The index of the roll table
	 */
	public void assignRollTable(int index)
	{
		m_rollTableIndex = index;
	}
}
