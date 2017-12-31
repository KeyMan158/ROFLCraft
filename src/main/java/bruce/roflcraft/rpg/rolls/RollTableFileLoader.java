package bruce.roflcraft.rpg.rolls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bruce.roflcraft.rpg.character.stats.AttributeCollection;
import bruce.roflcraft.rpg.character.stats.SkillCollection;
import jline.internal.Log;
import scala.Predef.StringFormat;

/**
 * A collection of static methods for loading and saving roll table
 * data in an editable format
 * TODO Put the common methods (ie fileExists etc) in a common static class
 * @author Lorrtath
 *
 */
public class RollTableFileLoader 
{
	private static final Logger LOG = LogManager.getLogger();
	private static final String TABLE_FILE_HEADER = "Starting roll table properties for the %s roll table";
	private static final String ROLL_TABLE_FOLDER = "RollTables";
	private static final String TABLE_SAVE_PATH = "\\%s.txt";
	private static final String PROPERTY_VALUE_KEY = "THRESHOLD %s VALUE";
	private static final String PROPERTY_DESC_KEY = "THRESHOLD %s DESC";
	private static final String ERROR_SAVE_FAILED = "Failed to save %s roll table properties";
	private static final String ERROR_LOAD_FAILED = "Failed to load %s roll table properties";
	private static final String ERROR_READ_FAILED = "Could not read the property %s for the table {1}";
	
	/**
	 * Checks if a file in the given path exists
	 * @param filepath The filepath to check
	 * @return True if the filepath exists
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
	 * Create a roll table file
	 * @param rollTable The roll table to create a save file for
	 */
	public static void createTableFile(IRollTableInterface rollTable)
	{
		File file = null;
		FileOutputStream fileOutputStream = null;
		Properties tableProperties = createPropertiesObject(rollTable);
		try
		{
			file = getPropertiesFile(rollTable);
			file.getParentFile().mkdirs();
			fileOutputStream = new FileOutputStream(file);
			tableProperties.store(fileOutputStream, String.format(TABLE_FILE_HEADER, rollTable.getTableName()));
		}       
		catch (Exception e)
        {
			LOG.warn((String)(String.format(ERROR_SAVE_FAILED, rollTable.getTableName())), (Throwable)e);
        }
        finally
        {
            IOUtils.closeQuietly((OutputStream)fileOutputStream);
        }
	}
	
	/**
	 * Load a roll table from a file
	 * @param rollTable The roll table object to populate
	 */
	public static void LoadTableFile(IRollTableInterface rollTable)
	{
		if(fileExists(getFilePath(rollTable)))
		{
		
			FileInputStream fileInputStream = null;
			Properties tableProperties = new Properties();
			try
			{
				fileInputStream = new FileInputStream(getPropertiesFile(rollTable));
				tableProperties.load(fileInputStream);
				loadFromPropertiesObject(tableProperties, rollTable);
			}
			catch (Exception e)
			{
				Log.warn(String.format(ERROR_LOAD_FAILED, rollTable.getTableName()));
				Log.warn(e.getMessage());
				createTableFile(rollTable);
			}
		}
		else
		{
			createTableFile(rollTable);
		}
	}
	
	/**
	 * Gets the file path for a roll table
	 * @param rollTable The roll table to get the file path for
	 * @return The file path
	 */
	public static String getFilePath(IRollTableInterface rollTable)
	{
		return new File(ROLL_TABLE_FOLDER).getAbsolutePath() +  String.format(TABLE_SAVE_PATH, rollTable.getTableName());
	}
	
	/**
	 * Creates a properties object for the roll table
	 * @param rollTable The roll table object to create properties from
	 * @return The roll table properties object
	 */
	public static Properties createPropertiesObject(IRollTableInterface rollTable)
	{
		Properties tableProperties = new Properties();
		for (int i = 0; i < rollTable.thresholdCount(); i++)
		{
			tableProperties.setProperty(String.format(PROPERTY_VALUE_KEY, i), Integer.toString(rollTable.getThresholdValue(i)));
			tableProperties.setProperty(String.format(PROPERTY_DESC_KEY, i), rollTable.getThresholdDesc(i));
		}
		return tableProperties;
	}
	
	/**
	 * Loads the roll thresholds from a properties object.
	 * @param tableProperties The table properties that will be loaded from
	 * @param rollTable The roll table object to set data from
	 */
	public static void loadFromPropertiesObject(Properties tableProperties, IRollTableInterface rollTable)
	{
		for(int i = 0; i < rollTable.thresholdCount(); i++)
		{
			try
			{
				rollTable.setThresholdValue(i, Integer.parseInt(tableProperties.getProperty(String.format(PROPERTY_VALUE_KEY, i))));
				rollTable.setThresholdDesc(i, tableProperties.getProperty(String.format(PROPERTY_DESC_KEY, i)));
			}
			catch(Exception e)
			{
				LOG.warn(String.format(ERROR_READ_FAILED, i , rollTable.getTableName()));
				LOG.warn(e.getMessage());
			}
		}
	}
	
	/**
	 * Creates the file object for the roll table
	 * @param rollTable The roll table that the file object shall be set to
	 * @return The new file object
	 */
	private static File getPropertiesFile(IRollTableInterface rollTable)
	{
		File file = new File(getFilePath(rollTable));
		return file;
	}
}
