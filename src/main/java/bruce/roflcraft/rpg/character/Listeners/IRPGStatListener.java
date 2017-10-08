package bruce.roflcraft.rpg.character.Listeners;

import bruce.roflcraft.rpg.character.stats.RPGStat;
import bruce.roflcraft.rpg.character.stats.StatModifier;

/**
 * Listener interface for RPGStat related events
 * This is currently not used
 * @author Lorrtath
 */
public interface IRPGStatListener 
{
	/**
	 * Event called when an RPGStat has its value changed
	 * @param statChanged The RPGStat that has had its value changed
	 * @param changedBy The amount the stat has changed by
	 */
	public void onStatValueChanged(RPGStat statChanged, int changedBy);
	
	/**
	 * Event called when a RPGStat is modified
	 * @param statModified The RPGStat modified
	 * @param modifier The modifier applied
	 */
	public void onStatValueModified(RPGStat statModified, StatModifier modifier);
	
	/**
	 * Event called when a RPGStat loses a modifier
	 * @param statModified The RPGStat to lose a modifier
	 * @param modifierCleared The modifier lost
	 */
	public void onStatModifierCleared(RPGStat statModified, StatModifier modifierCleared);
}
