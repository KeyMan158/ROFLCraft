package bruce.roflcraft.rpg;

public class Attribute 
{
	private String name;
	private int value;
	
	public Attribute(String aName, int aValue)
	{
		name = aName;
		value = aValue;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int attributeValue)
	{
		value = attributeValue;
	}
}
