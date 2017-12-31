package bruce.roflcraft.rpg.character.Race;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.InterningXmlVisitor;

import bruce.roflcraft.rpg.character.stats.AttributeCollection;
import bruce.roflcraft.rpg.character.stats.SkillCollection;
import bruce.roflcraft.rpg.character.stats.SkillIndex;
import jline.internal.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

/**
 * Class dedicated to loading & creating class setting files
 * @author Lorrtath
 */
public class RaceFileLoader 
{	
	private static final Logger LOG = LogManager.getLogger();
	private static final String RACIAL_SAVE_PATH = "\\%s.txt";
	private static final String RACIAL_FILE_HEADER = "Starting racial properties for the %s race";
	private static final String ERROR_SAVE_FAILED = "Failed to save %s racial properties";
	private static final String ERROR_LOAD_FAILED = "Failed to load %s racial properties";
	private static final String ERROR_READ_FAILED = "Could not read the property %s for the race {1}";
	
	/**
	 * Check to see if a file exists
	 * @param filepath
	 * @return True if the file exists
	 */
	public static boolean fileExists(String filepath)
	{
		File settingsFile = new File(filepath);
		if(settingsFile.exists())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Saves a race properties on the common file system
	 * @param race The race object that will be serialised
	 */
	public static void createRaceFile(IRace race)
	{
		String filepath = getFilePath(race);
		File file = null;
		FileOutputStream fileOutputStream = null;
		Properties raceProperties = createPropertiesObject(race);
		try
		{
			file = new File(filepath);
			file.getParentFile().mkdirs();
			fileOutputStream = new FileOutputStream(getRacePropertiesFile(race));
			raceProperties.store(fileOutputStream, String.format(RACIAL_FILE_HEADER, race.getName()));
		}       
		catch (Exception e)
        {
			LOG.warn((String)(String.format(ERROR_SAVE_FAILED, race.getName())), (Throwable)e);
        }
        finally
        {
            IOUtils.closeQuietly((OutputStream)fileOutputStream);
        }
	}
	
	/**
	 * De-serialises the race object and populates it with the user
	 * defined stating values
	 * @param race The race to load
	 */
	public static void LoadRaceFile(IRace race)
	{
		if(fileExists(getFilePath(race)))
		{
		
			FileInputStream fileInputStream = null;
			Properties raceProperties = new Properties();
			try
			{
				fileInputStream = new FileInputStream(getRacePropertiesFile(race));
				raceProperties.load(fileInputStream);
				loadFromPropertiesObject(raceProperties, race);
			}
			catch (Exception e)
			{
				Log.warn(String.format(ERROR_LOAD_FAILED, race.getName()));
				Log.warn(e.getMessage());
				createRaceFile(race);
			}
		}
		else
		{
			createRaceFile(race);
		}
	}
	
	/**
	 * Gets the file path for the racial settings file
	 * @param race The racial settings object
	 * @return The file path for the settings
	 */
	public static String getFilePath(IRace race)
	{
		return new File("Races/").getAbsolutePath() +  String.format(RACIAL_SAVE_PATH, race.getName());
	}
	
	/**
	 * Generates a properties object from default starting values
	 * @param race The race object to base the properties on
	 * @return The newly created properties object
	 */
	private static Properties createPropertiesObject(IRace race)
	{
		Properties racePropeties = new Properties();
		AttributeCollection defaultAttributes = race.getDefaultStartingAttributes();
		for (int i = 0; i < defaultAttributes.count(); i++)
		{
			racePropeties.setProperty(defaultAttributes.getStatName(i), Integer.toString(defaultAttributes.getStatValue(i)));
		}
		SkillCollection defaultSkills = race.getDefaultStartingSkills();
		for (int i = 0; i < defaultSkills.count(); i++)
		{
			racePropeties.setProperty(defaultSkills.getStatName(i), Integer.toString(defaultSkills.getStatValue(i)));
		}
		return racePropeties;
	}
	
	/**
	 * Sets the race stating values based on the properties object
	 * @param properties The loaded properties file to load from
	 * @param race The race who's starting values will be set
	 */
	private static void loadFromPropertiesObject(Properties properties, IRace race)
	{
		AttributeCollection attributes = race.getAttributes();
		for (int i = 0; i < attributes.count(); i++)
		{
			if(properties.containsKey(attributes.getStatName(i)))
			{
				try
				{
					attributes.setStatValue(i, Integer.parseInt(properties.getProperty(attributes.getStatName(i))));
				}
				catch (Exception e)
				{
					LOG.warn(String.format(ERROR_READ_FAILED, attributes.getStatName(i), race.getName()));
					LOG.warn(e.getMessage());
				}
			}
		}
		SkillCollection skills = race.getStartingSkills();
		for (int i = 0; i < skills.count(); i++)
		{
			if(properties.containsKey(skills.getStatName(i)))
			{
				try
				{
					skills.setStatValue(i, Integer.parseInt(properties.getProperty(skills.getStatName(i))));
				}
				catch (Exception e)
				{
					LOG.warn(String.format(ERROR_READ_FAILED, skills.getStatName(i), race.getName()));
					LOG.warn(e.getMessage());
				}
			}
		}
		race.loadStartingValues(attributes, skills);
	}
	
	/**
	 * Creates a file object
	 * @param race The race 
	 * @return The properties file object
	 */
	private static File getRacePropertiesFile(IRace race)
	{
		File file = new File(getFilePath(race));
		return file;
	}
}
