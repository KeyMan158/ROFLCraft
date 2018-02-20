package bruce.roflcraft.rpg.character.stats.skills.effects;

import java.util.List;

import bruce.roflcraft.rpg.rolls.DiceRoller;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

// TODO This class...
public class SkillEffectLivingDrops 
{
	private static final float NORMAL_MULTIPLIER = 1f;
	private static final int BONUS_MULTIPLIER = 2;
	private static final int RICH_MULTIPLIER = 3;
	
	/**
	 * Applies a skill effect to a living entity loot drop event based on a roll table threshold met
	 * @param rollThreshold The roll threshold met
	 * @param event The event that has triggered the skill
	 */
	public static void processLivingDrops(int rollThreshold, net.minecraftforge.event.entity.living.LivingDropsEvent event)
	{
		switch (rollThreshold) 
		{
		case 1:
			applyEffect_NormalLoot(event);
			break;
		case 2:
			applyEffect_BonusLoot(event);
			break;
		case 3:
			applyEffect_RichLoot(event);
			break;
		case 4:
			applyEffect_EpicLoot(event);
			break;
		default:
			applyEffect_FailedRoll(event);
			break;
		}
	}
	
	/**
	 * Apply the roll failed effect to the loot drop event
	 * @param event The event the effect is applied to
	 */
	private static void applyEffect_FailedRoll(net.minecraftforge.event.entity.living.LivingDropsEvent event)
	{
		boolean coinTossResult = DiceRoller.flipCoin();
		if(!coinTossResult & event.isCancelable())
		{
			event.setCanceled(true);
		}
	}
	
	/**
	 * Apply the reduced harvest effect to the loot drop event
	 * @param event The event the effect is applied to
	 */
	private static void applyEffect_NormalLoot(net.minecraftforge.event.entity.living.LivingDropsEvent event)
	{
		// Do Nothing, drop items as normal
	}
	
	/**
	 * Apply the normal harvest effect to the loot drop event
	 * @param event The event the effect is applied to
	 */
	private static void applyEffect_BonusLoot(net.minecraftforge.event.entity.living.LivingDropsEvent event)
	{
		List<EntityItem> items = event.getDrops();
		for (int i = items.size() - 1; i >= 0; i--)
		{
			boolean coinToss =  DiceRoller.flipCoin();
			if(coinToss)
			{
				addNewItemDrops(items, i, 1);
			}
		}
	}
	
	/**
	 * Apply the bonus harvest effect to the loot drop event
	 * @param event The event the effect is applied to
	 */
	private static void applyEffect_RichLoot(net.minecraftforge.event.entity.living.LivingDropsEvent event)
	{
		List<EntityItem> items = event.getDrops();
		for (int i = items.size() - 1; i >= 0; i--)
		{
			boolean coinToss =  DiceRoller.flipCoin();
			if(coinToss)
			{
				addNewItemDrops(items, i, 2);
			}
			else
			{
				addNewItemDrops(items, i, 1);
			}
		}
	}
	
	/**
	 * Apply the rich harvest effect to the loot drop event
	 * @param event The event the effect is applied to
	 */
	private static void applyEffect_EpicLoot(net.minecraftforge.event.entity.living.LivingDropsEvent event)
	{
		List<EntityItem> items = event.getDrops();
		for (int i = items.size() - 1; i >= 0; i--)
		{
			boolean coinToss =  DiceRoller.flipCoin();
			if(coinToss)
			{
				addNewItemDrops(items, i, 3);
			}
			else
			{
				addNewItemDrops(items, i, 2);
			}
		}
	}
	
	/**
	 * add additonal drops for an item to a list of dropps
	 * @param items The list of item drops
	 * @param index The index of the item to add additions of
	 * @param qty The number of additons
	 */
	private static void addNewItemDrops(List<EntityItem> items, int index, int qty)
	{
		if(index < 0 || index > items.size())
		{
			return;
		}
		for(int i = 0; i < qty; i++)
		{
			try 
			{
				EntityItem item;
				item = items.get(index).getClass().newInstance();
				items.add(item);
			} 
			catch (InstantiationException | IllegalAccessException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
