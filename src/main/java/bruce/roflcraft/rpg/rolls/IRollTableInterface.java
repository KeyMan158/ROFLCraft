package bruce.roflcraft.rpg.rolls;

/**
 * This interface provides a common methodology for interacting
 * with roll tables of different types
 * @author Lorrtath
 *
 */
public interface IRollTableInterface 
{
	/**
	 * Initialise the roll table
	 * @param tableName The name of the table
	 */
	public void init(String tableName);
	
	/**
	 * Set the default values for the roll table
	 */
	public void setDefaults();
	
	/**
	 * Gets a count of the possible roll thresholds
	 * @return The number of roll thresholds
	 */
	public int thresholdCount();
	
	/**
	 * Gets the value of a threshold
	 * @param index The index of the threshold
	 * @return THe value of the threshold
	 */
	public int getThresholdValue(int index);
	
	/**
	 * Set the value of a threshold
	 * @param index The index of the threshold
	 * @param value The new value of the threshold
	 */
	public void setThresholdValue(int index, int value);
	
	/**
	 * Get the description of a roll threshold
	 * @param index The index of the threshold
	 * @return The description of the threshold
	 */
	public String getThresholdDesc(int index);
	
	/**
	 * Sets the description of the the threshold at the given index
	 * @param index Th index of the threshold
	 * @param description The description of the threshold
	 */
	public void setThresholdDesc(int index, String description);
	
	/**
	 * Gets the name of the table
	 * @return The name of the table
	 */
	public String getTableName();
		
	/**
	 * Check to determine the roll threshold met by a roll value
	 * @param roll The roll to check against
	 * @return The index for the threshold met
	 */
	public int determineRollThreshold(int roll);
}
