package bruce.roflcraft.rpg.character;

import bruce.roflcraft.handlers.RoflCraftPacketHandler;
import bruce.roflcraft.network.Message.SkillPointPurchaseMessage;
import bruce.roflcraft.network.Message.SkillPointSpentMessage;
import bruce.roflcraft.rpg.character.Listeners.ISkillStatListener;
import bruce.roflcraft.rpg.character.Race.IRace;
import bruce.roflcraft.rpg.character.stats.AttributeCollection;
import bruce.roflcraft.rpg.character.stats.Skill;
import bruce.roflcraft.rpg.character.stats.SkillCollection;
import bruce.roflcraft.rpg.character.stats.SkillIndex;
import bruce.roflcraft.rpg.character.stats.SkillPointTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Default implementation of IRPGCharacterData.
 * This Class is the digital "Character Sheet" that is the backbone of
 * the RoflCraft RPG System
 * @author Lorrtath
 */
public class RPGCharacterData implements IRPGCharacterData
{
	private static final String NBT_ATTRI_ID = "Attributes";
	private static final String NBT_SKILL_POINTS_ID = "Skill Tracker";
	private static final String NBT_SKILL_STATS_ID = "Skills";
	
	private AttributeCollection m_attributes;
	private SkillPointTracker m_skillPoints;
	private SkillCollection m_skills;
	private IRace m_race;
	
	public RPGCharacterData()
	{
		m_attributes = new AttributeCollection();
		m_skillPoints = new SkillPointTracker();
		m_skills = new SkillCollection();
		m_skills.setListener(this);
	}
	
	@Override
	public AttributeCollection getAttributes() 
	{
		return m_attributes;
	}

	@Override
	public SkillPointTracker getSkillPointTracker() 
	{
		return m_skillPoints;
	}
	@Override
	public SkillCollection getSkills()
	{
		return m_skills;
	}
	
	@Override
	public void Duplicate(IRPGCharacterData existingData) 
	{
		m_attributes = existingData.getAttributes();
		m_skillPoints = existingData.getSkillPointTracker();
		m_skills = existingData.getSkills();
		m_skills.setListener(this);
	}
	
	@Override
	public void PurchaseSkillPoint(int levels)
	{
		Minecraft.getMinecraft().player.addExperienceLevel(-1 * levels); //removeExperienceLevel(levels);
		ContributeToSkillPoint(Minecraft.getMinecraft().player.xpBarCap());
		RoflCraftPacketHandler.sendToServer(new SkillPointPurchaseMessage(levels));
	}
	
	@Override
	public void spendSkillPoint(int amount, SkillIndex index)
	{
		RoflCraftPacketHandler.sendToServer(new SkillPointSpentMessage(amount, index));
		m_skills.addToStat(amount, index);
	}

	
	public void ContributeToSkillPoint(int xp)
	{
		m_skillPoints.AddXP(xp);
	}
	
	@Override
	public NBTBase rpgCharacterToNBT() 
	{
		NBTTagCompound nbtData = new NBTTagCompound();
		nbtData.setTag(NBT_ATTRI_ID, m_attributes.collectionToNBT());
		nbtData.setTag(NBT_SKILL_POINTS_ID, m_skillPoints.pointTrackerToNBT());
		nbtData.setTag(NBT_SKILL_STATS_ID, m_skills.collectionToNBT());
		// nbtData.appendTag(m_attributes.collectionToNBT());
		// nbtData.appendTag(m_skillPoints.pointTrackerToNBT());
		// nbtData.appendTag(m_skills.collectionToNBT());
		return nbtData;
	}

	@Override
	public void rpgCharacterFromNBT(NBTTagCompound nbtData) 
	{
		m_attributes.collectionFromNB((NBTTagList)nbtData.getTag(NBT_ATTRI_ID));
		m_skillPoints.pointTrackerFromNBT((NBTTagList)nbtData.getTag(NBT_SKILL_POINTS_ID));
		m_skills.collectionFromNB((NBTTagList)nbtData.getTag(NBT_SKILL_STATS_ID));
		// m_attributes.collectionFromNB((NBTTagList)nbtData.get(NBT_ATTRI_ID));
		// m_skillPoints.pointTrackerFromNBT((NBTTagList)nbtData.get(NBT_SKILL_POINTS_ID));
		// m_skills.collectionFromNB((NBTTagList)nbtData.get(NBT_SKILL_STATS_ID));
		// TODO Race NBT
	}

	/**
	 * --- SkillStatListener event handling: ---
	 */
	
	@Override
	public void OnSkillValueChanged(Skill skillChanged, int amount) 
	{
		m_skillPoints.removePointToSpend(amount);
		m_attributes.progressAttribute(skillChanged.getPrimaryAttribute());
	}
	
}
