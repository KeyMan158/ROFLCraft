package bruce.roflcraft.rpg.character.stats;

import com.sun.glass.ui.Window.Level;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;

/**
 * Object for handling the Creation & tracking of 
 * skill points (e.g the characters overall level)
 * @author Lorrtath
 */
public class SkillPointTracker 
{
	private static final String NBT_LEVEL_TAG = "Level";
	private static final String NBT_AVAILABLE_TAG = "Available";
	private static final String NBT_XP_TAG = "xp";
	private static final int NBT_SKILL_TRAKER_ID = 0;
	
	/**
	 * The total number of skill points obtained
	 */
	private int m_skillPointLevel;
	
	/**
	 * The total number of skill points left to spend
	 */
	private int m_skillPointsAvailable;
	
	/** The sum total XP points currently stored towards the next level */
	private int m_xpStored;
	
	public SkillPointTracker()
	{
		m_skillPointLevel = 0;
		m_skillPointsAvailable = 5;
		m_xpStored = 0;
	}
	
	/**
	 * Function for controlling the state of the skill point tracker
	 * @param level The level to have the tracker set at
	 * @param pointsAvailable The new number of points available
	 * @param xp The XP stored
	 */
	public void setSkillPointTracker(int level, int pointsAvailable, int xp)
	{
		m_skillPointLevel = level;
		m_skillPointsAvailable = pointsAvailable;
		m_xpStored = xp;
	}
	
	/**
	 * Subtracts an amount from the amount of points to the available points
	 * @param ammount The amount to subtract
	 */
	public void removePointToSpend(int ammount)
	{
		if(ammount > 0)
		{
			m_skillPointsAvailable -= ammount;
		}
	}
	
	/**
	 * Counts an amount of player XP towards skill points
	 * @param xpAmmount
	 * @return Levels gained
	 */
	public int AddXP(int xpAmmount)
	{
		m_xpStored += xpAmmount;
		int levelsGained = 0;
		while (m_xpStored >= xpToNext())
		{
			m_xpStored -= xpToNext();
			m_skillPointLevel++;
			m_skillPointsAvailable++;
			levelsGained++;
		}
		return levelsGained;
	}
	
	/**
	 * Gets the total XP needed to level up
	 * @return
	 */
	public int xpToNext()
	{
		int xpTotal = 0;
		for (int i = 0; i <= m_skillPointLevel; i++) //playerLevelsToNextSkillPoint(m_skillPointLevel); i++)
		{
			xpTotal += xpInBarToNextLevel(i);
		}
		return xpTotal;
	}
	
	public static int playerLevelsToNextSkillPoint(int level)
	{
		return (int)(1 + Math.floor(0.05 * Math.pow(level, 2)));
	}	
	
	/**
	 * Gets the skill point tracker as NBTData
	 * @return the NBT Data
	 */
	public NBTBase pointTrackerToNBT()
	{
		NBTTagList nbtDataWrapper = new NBTTagList();
		NBTTagCompound nbtData = new NBTTagCompound();
		nbtData.setInteger(NBT_LEVEL_TAG, m_skillPointLevel);
		nbtData.setInteger(NBT_AVAILABLE_TAG, m_skillPointsAvailable);
		nbtData.setInteger(NBT_XP_TAG, m_xpStored);
		nbtDataWrapper.appendTag(nbtData);
		return nbtDataWrapper;
	}
	
	/**
	 * Sets the tracker from the NBT data
	 * @param nbtData The NBT data to set from
	 */
	public void pointTrackerFromNBT(NBTTagList nbtDataWrapper)
	{
		NBTTagCompound nbtData = (NBTTagCompound)nbtDataWrapper.get(NBT_SKILL_TRAKER_ID);
		m_skillPointLevel = nbtData.getInteger(NBT_LEVEL_TAG);
		m_skillPointsAvailable =  nbtData.getInteger(NBT_AVAILABLE_TAG);
		m_xpStored = nbtData.getInteger(NBT_XP_TAG);
	}
	
	/**
	 * Gets the skill point trackers level
	 * @return The level of this skill point tracker
	 */
	public int getLevel()
	{
		return m_skillPointLevel;
	}
	
	/**
	 * Gets the number of available skill points
	 * @return The number of available skill points
	 */
	public int getAvailablePoints()
	{
		return m_skillPointsAvailable;
	}
	
	/**
	 * Gets the stored XP towards the next level
	 * @return The xp stored towards the next level
	 */
	public int getStoredXP()
	{
		return m_xpStored;
	}
		
	/**
	 * Calculates the amount of XP a bar at a certain 
	 * level holds.
	 * @param level The level for a players XP
	 * @return The XP points a player XP bar can hold at a given level
	 */
	private static int xpInBarToNextLevel(int level)
	{
		if (level >= 30)
		{
			return 112 + (level - 30) * 9;
		}
		else if(level >= 15)
		{
			return 37 + (level - 15) * 5;
		}
		else
		{
			return 7 + level * 2;
		}
	}
}