package bruce.roflcraft.rpg.character.stats.skills;

import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import bruce.roflcraft.rpg.character.stats.AttributeIndex;
import bruce.roflcraft.rpg.character.stats.Skill;
import bruce.roflcraft.rpg.character.stats.SkillIndex;
import bruce.roflcraft.rpg.character.stats.SkillType;
import bruce.roflcraft.rpg.character.stats.skills.effects.SkillEffectLivingDrops;
import bruce.roflcraft.rpg.rolls.SkillRollTable;
import bruce.roflcraft.rpg.rolls.SkillRollTableManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SkillHunting extends Skill
{
	private static final SkillIndex SKILL_INDEX = SkillIndex.SKIL_HUNTING;
	private static final AttributeIndex SKILL_ATTRIBUTE = AttributeIndex.AT_SOUL;
	private static final SkillType SKILL_TYPE = SkillType.SKILL_TYPE_GATHERING;
	private static final String SKILL_NAME = "Hunting";
	private static final String SKILL_DESCR = "Affects items dropped by a creature on death";
	private static final String THRESHOLD_0_DESCR = "50% chance that a block is droped";
	private static final String THRESHOLD_1_DESCR = "75% chance that a block is dropped";
	private static final String THRESHOLD_2_DESCR = "100% chance that a block is dropped";
	private static final String THRESHOLD_3_DESCR = "100% chance that a block is dropped, 50% chance that 1 extra block is dropped";
	private static final String THRESHOLD_4_DESCR = "100% chance that 2 blocks are dropped, 50% chance that a second block is dropped";

	
	
	public SkillHunting()
	{
		super(SKILL_NAME,0, SKILL_ATTRIBUTE, SKILL_TYPE);
	}
	
	public SkillHunting(NBTTagCompound nbtData) 
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
	
	@ SubscribeEvent
	public static void onLivingEntityHunted(net.minecraftforge.event.entity.living.LivingDropsEvent event)
	{
		DamageSource damageSource = event.getSource();
		if(damageSource == null)
		{
			return;
		}
		EntityPlayer player = null;
		Entity tureDamageSource = damageSource.getTrueSource();
		if(tureDamageSource == null)
		{
			return;
		}
		if(EntityPlayer.class.isAssignableFrom(tureDamageSource.getClass()))
		{
			player = (EntityPlayer)tureDamageSource;
		}
		
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
		SkillEffectLivingDrops.processLivingDrops(thresholdMet, event);
	}
}
