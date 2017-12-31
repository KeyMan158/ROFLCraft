package bruce.roflcraft.rpg.rolls;

import java.util.ArrayList;
import java.util.List;

import bruce.roflcraft.handlers.SkillHandler;

/**
 * This class manages the various roll tables used for the skills
 * and should be initialised before the SkillHandler!
 * @author Lorrtath
 *
 */
public class SkillRollTableManager 
{
	private static List<SkillRollTable> m_skills;
	
	/**
	 * Initialises the manager
	 */
	public static void init()
	{
		m_skills = new ArrayList<SkillRollTable>();
	}
	
	public static void registerRollTable(SkillRollTable table)
	{
		load(table);
		m_skills.add(table);
	}
	
	/**
	 * Loads a specific table from a file
	 * @param table The blank table to load
	 */
	public static void load(SkillRollTable table)
	{
		RollTableFileLoader.LoadTableFile(table);
	}
	
	/**
	 * Identifies which table has the name requested
	 * @param tableName The requested name of the table
	 * @return The index of the table, will return -1 if not found
	 */
	public static int getIndexOfTable(String tableName)
	{
		for(int i = 0; i < m_skills.size(); i++)
		{
			if(m_skills.get(i).getTableName() == tableName)
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Gets the index for the highest met threshold
	 * @param index The index of the table to check
	 * @param roll The roll value to check
	 * @return The index of the lowest threshold met, returns -1 if failed
	 */
	public static int getTableThreshold(int index, int roll)
	{
		return m_skills.get(index).determineRollThreshold(roll);
	}
}
