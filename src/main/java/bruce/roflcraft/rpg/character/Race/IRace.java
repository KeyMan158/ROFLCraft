package bruce.roflcraft.rpg.character.Race;

import bruce.roflcraft.rpg.character.stats.AttributeCollection;
import bruce.roflcraft.rpg.character.stats.SkillCollection;

public interface IRace 
{	
	/**
	 * Method of loading in the user defined default settings
	 * @param attributes The new starting attributes
	 * @param skills The new stating skills
	 */
	public void loadStartingValues(AttributeCollection attributes, SkillCollection skills);
	
	/**
	 * Gets the race starting attributes
	 * @return The starting attributes
	 */
	public AttributeCollection getAttributes();
	
	/**
	 * Gets the Mods default starting attributes for the race
	 * @return The default attributes
	 */
	public AttributeCollection getDefaultStartingAttributes();
	
	/**
	 * Gets the races starting skills
	 * @return The starting skills
	 */
	public SkillCollection getStartingSkills();
	
	/**
	 * Gets the default starting skills and their values;
	 * @return The default stating skills
	 */
	public SkillCollection getDefaultStartingSkills();
	
	/**
	 * Get the name of the race
	 * @return The races name
	 */
	public String getName();
	
	/**
	 * Gets the type of race
	 * @return The race type
	 */
	public RaceIndex getRaceType();
}
