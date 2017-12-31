package bruce.roflcraft.rpg.rolls;

/**
 * Roll table object used for skill roll checks
 * By default, a skill has 5 roll thresholds, but this is just a general 
 * rule used for the default skills. additional threshold levels should be 
 * created in an extending class
 * @author Lorrtath
 *
 */
public class SkillRollTable implements IRollTableInterface 
{
	private static final int THRESHOLD_COUNT = 5;
	private static final int DEFAULT_STARTING_VALUE = 1;
	private static final int DEFAULT_VALUE_INCREMENT = 15;
	
	private RollThreshold[] m_thresholds;
	private String m_tableName;
	
	public SkillRollTable() 
	{
		m_thresholds = new RollThreshold[THRESHOLD_COUNT];
	}

	
	@Override
	public void init(String tableName) 
	{
		m_tableName = tableName;
		setDefaults();
	}

	@Override
	public void setDefaults() 
	{
		// TODO Make specific descriptions based on a skill class
		int value = DEFAULT_STARTING_VALUE;
		for(int i = 0; i < thresholdCount(); i++)
		{
			m_thresholds[i] = new RollThreshold(value, "Skill Threshold " + i);
			value += DEFAULT_VALUE_INCREMENT;
		}
	}

	@Override
	public int thresholdCount() 
	{
		return THRESHOLD_COUNT;
	}

	@Override
	public int getThresholdValue(int index) 
	{
		return m_thresholds[index].getValue();
	}

	@Override
	public void setThresholdValue(int index, int value) 
	{
		m_thresholds[index].setValue(value);
	}

	@Override
	public String getThresholdDesc(int index) 
	{
		return m_thresholds[index].getDesc();
	}

	@Override
	public void setThresholdDesc(int index, String description) 
	{
		m_thresholds[index].setDesc(description);
	}

	@Override
	public String getTableName() 
	{
		return m_tableName;
	}

	@Override
	public int determineRollThreshold(int roll) 
	{
		int threshold = -1;
		for (int i = 0; i < thresholdCount(); i++)
		{
			if(m_thresholds[i].getValue() > roll)
			{
				break;
			}
			threshold++;
		}
		return threshold;
	}

}
