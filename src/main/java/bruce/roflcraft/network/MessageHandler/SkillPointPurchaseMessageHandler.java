package bruce.roflcraft.network.MessageHandler;

import bruce.roflcraft.handlers.RoflCraftPacketHandler;
import bruce.roflcraft.network.Message.SkillPointPurchaseMessage;
import bruce.roflcraft.network.Message.SkillPointReciptMessage;
import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import bruce.roflcraft.rpg.character.stats.SkillPointTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Packet message handler for Skill Point purchasing.
 * @author Lorrtath
 */
public class SkillPointPurchaseMessageHandler implements IMessageHandler<SkillPointPurchaseMessage, IMessage>
{
	public SkillPointPurchaseMessageHandler()
	{
		
	}
	
	@Override
	public IMessage onMessage(SkillPointPurchaseMessage message, MessageContext ctx) 
	{
		EntityPlayerMP serverPlayer = ctx.getServerHandler().playerEntity;
		IRPGCharacterData characterData = serverPlayer.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		serverPlayer.removeExperienceLevel(message.getXPLevels());
		int xpToSpend = serverPlayer.xpBarCap();
		characterData.ContributeToSkillPoint(xpToSpend);
		SkillPointTracker tracker = characterData.getSkillPointTracker();
		//RoflCraftPacketHandler.sendToPlayer(new SkillPointReciptMessage(tracker.getLevel(), tracker.getAvailablePoints(), tracker.getStoredXP()), serverPlayer);
		return null;
	}

}
