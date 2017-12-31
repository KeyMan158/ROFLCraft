package bruce.roflcraft.rpg.character.stats.skills;

import java.util.List;

import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import bruce.roflcraft.rpg.character.stats.AttributeIndex;
import bruce.roflcraft.rpg.character.stats.Skill;
import bruce.roflcraft.rpg.character.stats.SkillIndex;
import bruce.roflcraft.rpg.character.stats.SkillType;
import bruce.roflcraft.rpg.rolls.DiceRoller;
import bruce.roflcraft.rpg.rolls.SkillRollTable;
import bruce.roflcraft.rpg.rolls.SkillRollTableManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.Console;

/**
 * Class for the Forestry skill
 * As this class uses forge event handling it should be registered
 * with the skill handler during common initialisation!  
 * @author Lorrtath
 *
 */
public class SkillForestry extends Skill
{
	private static final SkillIndex SKILL_INDEX = SkillIndex.SKILL_FORESTRY;
	private static final AttributeIndex SKILL_ATTRIBUTE = AttributeIndex.AT_BODY;
	private static final SkillType SKILL_TYPE = SkillType.SKILL_TYPE_GATHERING;
	private static final String SKILL_NAME = "Forestry";
	private static final String SKILL_DESCR = "Affects use of an axe to gather a block";
	private static final String THRESHOLD_0_DESCR = "50% chance that a block is droped";
	private static final String THRESHOLD_1_DESCR = "75% chance that a block is dropped";
	private static final String THRESHOLD_2_DESCR = "100% chance that a block is dropped";
	private static final String THRESHOLD_3_DESCR = "100% chance that a block is dropped, 50% chance that 1 extra block is dropped";
	private static final String THRESHOLD_4_DESCR = "100% chance that 2 blocks are dropped, 50% chance that a second block is dropped";

	public SkillForestry()
	{
		super(SKILL_NAME,0, SKILL_ATTRIBUTE, SKILL_TYPE);
	}

	public SkillForestry(NBTTagCompound nbtData) 
	{
		super(nbtData);
	}
	
	/**
	 * Assigns threshold descriptions to a specific table
	 * @param table The table to assign values to
	 */
	public static void assignThresholdDescriptions(SkillRollTable table)
	{
		table.setThresholdDesc(0, THRESHOLD_0_DESCR);
		table.setThresholdDesc(1, THRESHOLD_1_DESCR);
		table.setThresholdDesc(2, THRESHOLD_2_DESCR);
		table.setThresholdDesc(3, THRESHOLD_3_DESCR);
		table.setThresholdDesc(4, THRESHOLD_4_DESCR);
	}

	@Override
	public String getThresholdDescription(int index) 
	{
		switch (index) 
		{
		case 0:
			return THRESHOLD_0_DESCR;
		case 1:
			return THRESHOLD_1_DESCR;
		case 2:
			return THRESHOLD_2_DESCR;
		case 3:
			return THRESHOLD_3_DESCR;
		case 4:
			return THRESHOLD_4_DESCR;
		default:
			return "";
		}
	}

	@Override
	public String getSkillDescription() 
	{
		return SKILL_DESCR;
	}

	@Override
	public int getIndexOfSkill() 
	{
		return SKILL_INDEX.ordinal();
	}
	
	/**
	 * Event handler for when a block is harvested 
	 * TODO This is a mess, clean up ASAP once tested
	 * @param event
	 */
	@SubscribeEvent
	public static void onBlockHarvested(net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
	{
		EntityPlayer player = event.getHarvester();
		if (player == null)
		{
			return;
		}
		ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
		if (heldItem.getItem().getClass() != ItemAxe.class)
		{
			return;
		}
		IRPGCharacterData data = player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		int roll = data.rollSkill(SKILL_INDEX.ordinal());
		int thresholdMet = SkillRollTableManager.getTableThreshold(SKILL_INDEX.ordinal(), roll);
		List<ItemStack> items = event.getDrops();
		switch (thresholdMet) 
		{
		case 1:
			event.setDropChance(0.75f);
			break;
		case 2:
			event.setDropChance(1f);
			break;
		case 3:
			if(DiceRoller.flipCoin())
			{
				for(int i = 0; i < items.size(); i++)
				{
					items.get(i).setCount(items.get(i).getCount() * 2);
				}
			}
			break;
		case 4:
			if(DiceRoller.flipCoin())
			{
				for(int i = 0; i < items.size(); i++)
				{
					items.get(i).setCount(items.get(i).getCount() * 3);
				}
			}
			break;
		default:
			event.setDropChance(0.5f);
			break;
		}
	}
}
