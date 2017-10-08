package bruce.roflcraft.rpg.character;

import bruce.roflcraft.rpg.character.Listeners.ISkillStatListener;
import bruce.roflcraft.rpg.character.stats.AttributeCollection;
import bruce.roflcraft.rpg.character.stats.SkillCollection;
import bruce.roflcraft.rpg.character.stats.SkillIndex;
import bruce.roflcraft.rpg.character.stats.SkillPointTracker;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * RPG Character data capability interface
 * @author Lorrtath
 */
public interface IRPGCharacterData extends ISkillStatListener
{
	/**
	 * Gets a collection of the players attributes
	 * @return
	 */
	public AttributeCollection getAttributes();
		
	/**
	 * Gets the skill point tracker object for this player
	 * @return
	 */
	public SkillPointTracker getSkillPointTracker();
	
	/**
	 * Gets the skills collection
	 * @return
	 */
	public SkillCollection getSkills();
	
	/**
	 * Duplicates a RPGCharacterData object to this one
	 * @param existingData
	 */
	public void Duplicate(IRPGCharacterData existingData);
	
	/**
	 * Adds an amount of player XP towards the next available skill point
	 * @param xp
	 */
	public void PurchaseSkillPoint(int xp);
	
	/**
	 * spends an amount of skill points on a skill
	 * @param ammout The amount of skill points to spend
	 * @param index The skill index that this applies to
	 */
	public void spendSkillPoint(int ammout, SkillIndex index);
	
	/**
	 * Adds an amount of player XP towards the next available skill point. This is 
	 * intended for server side calls only and wont send any packets
	 * @param xp
	 */
	public void ContributeToSkillPoint(int xp);
	
	/**
	 * Gets the character data as NBT data
	 * @return The CharacterData object as NBT data
	 */
	public NBTBase rpgCharacterToNBT();
	
	/**
	 * Sets the character from NBT Data
	 * @param nbtData The NBT data to set this character from
	 */
	public void rpgCharacterFromNBT(NBTTagCompound nbtData);
}
