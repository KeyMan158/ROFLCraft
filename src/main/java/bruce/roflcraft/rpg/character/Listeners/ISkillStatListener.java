package bruce.roflcraft.rpg.character.Listeners;

import bruce.roflcraft.rpg.character.stats.Skill;

/**
 * Interface for listening to skills
 * @author Lorrtath
 */
public interface ISkillStatListener
{
	/**
	 * Event receiver for when a skill changes
	 * @param skillChanged The skill that has changed 
	 * @param ammout The amount the skill has changed by
	 */
	void OnSkillValueChanged(Skill skillChanged, int amount);
}
