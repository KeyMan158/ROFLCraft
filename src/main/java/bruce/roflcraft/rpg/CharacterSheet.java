package bruce.roflcraft.rpg;

public class CharacterSheet 
{
	private String name;
	private Skill skills[];
	private Attribute attributes[];
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String characterName)
	{
		name = characterName;
	}
}
