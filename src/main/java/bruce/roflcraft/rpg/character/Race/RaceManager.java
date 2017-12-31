package bruce.roflcraft.rpg.character.Race;

/**
 * This class is used for configurating and managing racial
 * defaults. This includes ensuring that the racial folders and config
 * files are included
 * TODO .. Revise this after testing and full race implementation
 * @author Lorrtath
 *
 */
public class RaceManager 
{
	private IRace[] m_races;
	
	public RaceManager()
	{
		m_races = new IRace[1];
		m_races[0] = new Human();
	}
	
	/**
	 * Initialises the race manager
	 */
	public void initRaces()
	{
		for (int i = 0; i < m_races.length; i++)
		{
			RaceFileLoader.LoadRaceFile(m_races[i]);
		}
	}
	
	/**
	 * Gets the race at the given index
	 * @param raceIndex The index for the race
	 * @return The race at the given index
	 */
	public IRace getRace(RaceIndex raceIndex)
	{
		return m_races[0];
	}
}
