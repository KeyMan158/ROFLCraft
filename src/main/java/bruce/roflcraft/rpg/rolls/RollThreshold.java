package bruce.roflcraft.rpg.rolls;

/**
 * The role threshold object is used by an RPG roll table to 
 * determine the effect of a skill.
 * @author Lorrtath
 *
 */
public class RollThreshold 
{
	private int m_thresholdValue;
	private String m_thresholdDesc;
	
	public RollThreshold(int value, String description)
	{
		m_thresholdValue = value;
		m_thresholdDesc = description;
	}
	
	/**
	 * Sets the threshold value
	 * @param value The value of the threshold
	 */
	void setValue(int value)
	{
		m_thresholdValue = value;
	}
	
	/**
	 * Gets the threshold value
	 * @param value The value of the threshold
	 * @return The value of the threshold
	 */
	int getValue()
	{
		return m_thresholdValue;
	}
	
	/**
	 * Set the description of the threshold
	 * @param description The description of the threshold
	 */
	void setDesc(String description)
	{
		m_thresholdDesc = description;
	}
	
	/**
	 * Get the description of the threshold
	 * @return The description of the threshold
	 */
	String getDesc()
	{
		return m_thresholdDesc;
	}
}
