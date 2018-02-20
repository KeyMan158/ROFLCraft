package bruce.roflcraft.rpg.character.stats.skills;

import java.util.List;

import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import bruce.roflcraft.rpg.character.stats.AttributeIndex;
import bruce.roflcraft.rpg.character.stats.Skill;
import bruce.roflcraft.rpg.character.stats.SkillIndex;
import bruce.roflcraft.rpg.character.stats.SkillType;
import bruce.roflcraft.rpg.character.stats.skills.effects.SkillEffectBlockHarvest;
import bruce.roflcraft.rpg.rolls.SkillRollTable;
import bruce.roflcraft.rpg.rolls.SkillRollTableManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This class represents the farming skill.
 * This skill affets the use of spades, hoes and harvesting of plant blocks 
 * As this class uses forge event handling it should be registered
 * with the skill handler during common initialisation!  
 * @author Lorrtath
 */
public class SkillFarming extends Skill
{
	private static final SkillIndex SKILL_INDEX = SkillIndex.SKILL_FARMING;
	private static final AttributeIndex SKILL_ATTRIBUTE = AttributeIndex.AT_MIND;
	private static final SkillType SKILL_TYPE = SkillType.SKILL_TYPE_GATHERING;
	private static final String SKILL_NAME = "Farming";
	private static final String SKILL_DESCR = "Affects the use of spades, hoes and harvesting of plant blocks";
	private static final String THRESHOLD_0_DESCR = "50% chance that a block is droped";
	private static final String THRESHOLD_1_DESCR = "75% chance that a block is dropped";
	private static final String THRESHOLD_2_DESCR = "100% chance that a block is dropped";
	private static final String THRESHOLD_3_DESCR = "100% chance that a block is dropped, 50% chance that 1 extra block is dropped";
	private static final String THRESHOLD_4_DESCR = "100% chance that 2 blocks are dropped, 50% chance that a second block is dropped";
	
	public SkillFarming()
	{
		super(SKILL_NAME,0, SKILL_ATTRIBUTE, SKILL_TYPE);
	}
	
	public SkillFarming(NBTTagCompound nbtData) 
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
		}	}

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
		if (!eventAppliesToSkill(event))
		{
			return;
		}
		IRPGCharacterData data = player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		int roll = data.rollSkill(SKILL_INDEX.ordinal());
		int thresholdMet = SkillRollTableManager.getTableThreshold(SKILL_INDEX.ordinal(), roll);
		SkillEffectBlockHarvest.processBlockharvest(thresholdMet, event);
	}
	
	/**
	 * Checks to see if a spade or hoe was used, or if any of the items are plantable
	 * @param event
	 * @return
	 */
	private static boolean eventAppliesToSkill(net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
	{
		ItemStack heldItem = event.getHarvester().getHeldItem(EnumHand.MAIN_HAND);
		if(heldItem.getItem().getClass() == ItemSpade.class || heldItem.getItem().getClass() == ItemHoe.class)
		{
			return true;
		}
		else
		{
			List<ItemStack> items = event.getDrops();
			for (int i = 0; i < items.size(); i++)
			{
				if(items.get(i).getItem() instanceof IPlantable)
				{
					return true;
				}
			}
		}
		return false;
	}
}
