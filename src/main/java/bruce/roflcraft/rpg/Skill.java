package bruce.roflcraft.rpg;

public class Skill 
{
	private String name;
	private int value;
	
	public Skill(String sName, int sValue)
	{
		name = sName;
		value = sValue;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int skillValue)
	{
		value = skillValue;
	}
}
