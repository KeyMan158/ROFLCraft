package bruce.roflcraft.network.MessageHandler;

import bruce.roflcraft.network.Message.SkillPointSpentMessage;
import bruce.roflcraft.rpg.character.RPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class SkillPointSpentMessageHandler implements IMessageHandler<SkillPointSpentMessage, IMessage>
{	
	@Override
	public IMessage onMessage(SkillPointSpentMessage message, MessageContext ctx) 
	{
		EntityPlayer player;
		if(ctx.side == Side.SERVER)
		{
			player = ctx.getServerHandler().playerEntity;
			RPGCharacterData charData = (RPGCharacterData)player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
			charData.getSkills().addToStat(message.getPoints(), message.getIndex());
		}
		return null;
	}

}
