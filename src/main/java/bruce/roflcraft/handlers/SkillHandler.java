package bruce.roflcraft.handlers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bruce.roflcraft.rpg.character.stats.Skill;
import bruce.roflcraft.rpg.character.stats.skills.SkillFarming;
import bruce.roflcraft.rpg.character.stats.skills.SkillForestry;
import bruce.roflcraft.rpg.character.stats.skills.SkillHunting;
import bruce.roflcraft.rpg.character.stats.skills.SkillMining;
import bruce.roflcraft.rpg.rolls.SkillRollTable;
import bruce.roflcraft.rpg.rolls.SkillRollTableManager;
import net.minecraftforge.common.MinecraftForge;
import scala.reflect.internal.Trees.This;

/**
 * Handler class for registering and controlling skills
 * Skills are stored and registered statically by their static class object so 
 * use ".class". This prevents over registration and allows the use of factory 
 * methods
 * To expand the available skills list, register custom skills via the registerSkill method!
 * @author Lorrtath
 *
 */
public class SkillHandler 
{
	private static final Logger LOG = LogManager.getLogger();
	private static final String ERROR_CANNOT_REGISTER = "Aborted registration of %s as it cannot be cast to the Skill class!";
	private static final String ERROR_FAILED_SKILL_CREATE = "Failed to create skill no %s, with the following message:";
	private static List<Class<? extends Skill>> m_skills;
	
	/**
	 * Initialises the handler. Ensure that any non core skills are registered after
	 * this has performed its base init, or the skills will be unregistered!
	 */
	public static void init()
	{
		m_skills = new ArrayList<Class<? extends Skill>>();
		
		/** --- Setup Forestry Skill --- */
		registerSkill(SkillForestry.class);
		SkillRollTable forestryTable = new SkillRollTable();
		forestryTable.init(SkillForestry.class.getSimpleName());
		SkillForestry.assignThresholdDescriptions(forestryTable);
		SkillRollTableManager.registerRollTable(forestryTable);
		
		/** --- Setup Mining Skill --- */
		registerSkill(SkillMining.class);
		SkillRollTable miningTable = new SkillRollTable();
		miningTable.init(SkillMining.class.getSimpleName());
		SkillMining.assignThresholdDescriptions(miningTable);
		SkillRollTableManager.registerRollTable(miningTable);
		
		/** --- Setup Farming Skill --- */
		registerSkill(SkillFarming.class);
		SkillRollTable farmingTable = new SkillRollTable();
		farmingTable.init(SkillFarming.class.getSimpleName());
		SkillFarming.assignThresholdDescriptions(farmingTable);
		SkillRollTableManager.registerRollTable(farmingTable);
		
		/** --- Setup Hunting Skill --- */
		registerSkill(SkillHunting.class);
		SkillRollTable huntingTable = new SkillRollTable();
		huntingTable.init(SkillHunting.class.getSimpleName());
		SkillHunting.assignThresholdDescriptions(huntingTable);
		SkillRollTableManager.registerRollTable(huntingTable);
	}
	
	/**
	 * Registers an RPG Skill
	 * @param skill
	 */
	public static void registerSkill(Class<? extends Skill> skill)
	{
		if (Skill.class.isAssignableFrom(skill))
		{
			m_skills.add(skill);
			MinecraftForge.EVENT_BUS.register(skill);
		}
		else
		{
			LOG.debug(String.format(ERROR_CANNOT_REGISTER, skill.getName()));
		}
	}
	
	public static List<Skill> makeSkillList()
	{
		List<Skill> skills = new ArrayList<Skill>();	
		for(int i = 0; i <  m_skills.size(); i++)
		{
			try
			{
				Skill skill = (Skill)m_skills.get(i).newInstance();
				skills.add(skill);
			}
			catch (Exception e)
			{
				LOG.error("Failed to create ");
				LOG.error(String.format(ERROR_FAILED_SKILL_CREATE, e.getMessage()));
			}
		}
		return skills;
	}
	
	public static int getSkillCount()
	{
		return m_skills.size();
	}
	
	public static String getSkillName(int index)
	{
		if(index < 0 || index >= m_skills.size())
		{
			return "";
		}
		return m_skills.get(index).getSimpleName();
	}
}
