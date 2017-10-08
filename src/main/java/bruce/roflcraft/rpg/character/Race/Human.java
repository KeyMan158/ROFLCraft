package bruce.roflcraft.rpg.character.Race;

//import com.sun.xml.internal.bind.v2.TODO;

import bruce.roflcraft.rpg.character.stats.AttributeCollection;
import bruce.roflcraft.rpg.character.stats.SkillCollection;

public class Human  implements IRace
{
	private static final String NAME = "Human";
	private static AttributeCollection m_userAttributes;
	private static SkillCollection m_userSkills;
	private static final RaceIndex RACE_TYPE = RaceIndex.RACE_HUMAN;
	
	public Human()
	{
		m_userAttributes = getDefaultStartingAttributes();
		m_userSkills = getDefaultStartingSkills();
	}
	
	public void loadStartingValues(AttributeCollection attributes, SkillCollection skills)
	{
		m_userAttributes = attributes;
		m_userSkills = skills;
	}
	
	@Override
	public AttributeCollection getAttributes() 
	{
		return m_userAttributes;
	}

	@Override
	public AttributeCollection getDefaultStartingAttributes()
	{
		AttributeCollection attributes = new AttributeCollection();
		for (int i = 0; i < attributes.count(); i++)
		{
			attributes.setStatValue(i, 5); // Humans have 5 in all stats .. jack of trades...
		}
		return attributes;
	}
	
	@Override
	public SkillCollection getStartingSkills() 
	{
		return m_userSkills;
	}
	
	@Override
	public SkillCollection getDefaultStartingSkills() 
	{
		SkillCollection skills = new SkillCollection();
		for (int i = 0; i < skills.count(); i++)
		{
			skills.setStatValue(i, 5);
		}
		return skills;
	}
	
	@Override
	public String getName() 
	{
		return NAME;
	}

	@Override
	public RaceIndex getRaceType() 
	{
		return RaceIndex.RACE_HUMAN;
	}
}
