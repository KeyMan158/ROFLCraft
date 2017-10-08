package bruce.roflcraft.network.MessageHandler;

import bruce.roflcraft.network.Message.SkillPointPurchaseMessage;
import bruce.roflcraft.network.Message.SkillPointReciptMessage;
import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Handler for the recipt message when a skill point is purchased
 * @author Lorrtath
 *
 */
public class SkillPointReciptMessasgeHandler implements IMessageHandler<SkillPointReciptMessage, IMessage>
{

	@Override
	public IMessage onMessage(SkillPointReciptMessage message, MessageContext ctx) 
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		IRPGCharacterData characterData = player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		characterData.getSkillPointTracker().setSkillPointTracker(message.getLevel(), message.getAvailable(), message.getXP());
		return null;
	}

}
