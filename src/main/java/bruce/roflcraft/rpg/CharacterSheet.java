package bruce.roflcraft.rpg;

import java.util.ArrayList;

import bruce.roflcraft.settings.Attributes;
import bruce.roflcraft.settings.Skills;

@Deprecated
public class CharacterSheet 
{
	private String name;
	private Attribute[] attributesArray;
	private Skill[] skillsArray;
	
	public CharacterSheet()
	{
		//initialise attributes and skills 
		attributesArray = new Attribute[Attributes.values().length];
		skillsArray = new Skill[Skills.values().length];
		
		for (Attributes att : Attributes.values())
		{
			int i = 0;
			attributesArray[i] = new Attribute(att.name(),0);
			i++;
		}
		
		for (Skills att : Skills.values())
		{
			int i = 0;
			skillsArray[i] = new Skill(att.name(),0);
			i++;
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String characterName)
	{
		name = characterName;
	}
	
	public void addXpAttribute(int ID, int XP)
	{
		//placeholder to add XP for Attribute 
	}
	
	public void minusXpAttribute(int ID, int XP)
	{
		//placeholder to minus XP for Attribute
	}
	
	public void addXpSkill(int ID, int XP)
	{
		//placeholder to add XP for Skill 
	}
	
	public void minusXpSkill(int ID, int XP)
	{
		//placeholder to minus XP for Skill
	}
}
