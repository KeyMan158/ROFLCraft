package bruce.roflcraft.rpg.character.stats.skills.effects;

import java.util.List;

import bruce.roflcraft.rpg.rolls.DiceRoller;
import net.minecraft.item.ItemStack;

/**
 * This class contains the static methods for handling what happens
 * to a block when it is harvested (such as the forestry and mining 
 * skill).
 * @author Lorrtath
 */
public class SkillEffectBlockHarvest 
{
	private static final float NORMAL_MULTIPLIER = 1f;
	private static final int BONUS_MULTIPLIER = 2;
	private static final int RICH_MULTIPLIER = 3;
	
	/**
	 * Applies a skill effect to a harvesting event based on a roll table threshold met
	 * @param rollThreshold The roll threshold met
	 * @param event The event that has triggered the skill
	 */
	public static void processBlockharvest(int rollThreshold, net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
	{
		switch (rollThreshold) 
		{
		case 1:
			applyEffect_ReducedHarvest(event);
			break;
		case 2:
			applyEffect_NormalHarvest(event);
			break;
		case 3:
			applyEffect_BonusHarvest(event);
			break;
		case 4:
			applyEffect_RichHarvest(event);
			break;
		default:
			applyEffect_FailedRoll(event);
			break;
		}
	}
	
	/**
	 * Apply the roll failed effect to the harvest event
	 * @param event The event the effect is applied to
	 */
	private static void applyEffect_FailedRoll(net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
	{
		event.setDropChance(0.5f);
	}
	
	/**
	 * Apply the reduced harvest effect to the harvest event
	 * @param event The event the effect is applied to
	 */
	private static void applyEffect_ReducedHarvest(net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
	{
		event.setDropChance(0.75f);
	}
	
	/**
	 * Apply the normal harvest effect to the harvest event
	 * @param event The event the effect is applied to
	 */
	private static void applyEffect_NormalHarvest(net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
	{
		event.setDropChance(NORMAL_MULTIPLIER);
	}
	
	/**
	 * Apply the bonus harvest effect to the harvest event
	 * @param event The event the effect is applied to
	 */
	private static void applyEffect_BonusHarvest(net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
	{
		List<ItemStack> items = event.getDrops();
		for(int i = 0; i < items.size(); i++)
		{
			if(DiceRoller.flipCoin())
			{
				items.get(i).setCount(items.get(i).getCount() * BONUS_MULTIPLIER);
			}
		}
	}
	
	/**
	 * Apply the rich harvest effect to the harvest event
	 * @param event The event the effect is applied to
	 */
	private static void applyEffect_RichHarvest(net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
	{
		List<ItemStack> items = event.getDrops();
		for(int i = 0; i < items.size(); i++)
		{
			if(DiceRoller.flipCoin())
			{
				items.get(i).setCount(items.get(i).getCount() * RICH_MULTIPLIER);
			}
			else
			{
				items.get(i).setCount(items.get(i).getCount() * BONUS_MULTIPLIER);
			}
		}
	}
}
