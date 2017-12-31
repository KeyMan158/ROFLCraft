package bruce.roflcraft.handlers;

import akka.io.Tcp.Message;
import bruce.roflcraft.main.Reference;
import bruce.roflcraft.network.Message.RPGCharacterSyncMessage;
import bruce.roflcraft.network.Message.RequestRPGCharacterSyncMessage;
import bruce.roflcraft.network.Message.SkillPointPurchaseMessage;
import bruce.roflcraft.network.Message.SkillPointSpentMessage;
import bruce.roflcraft.network.MessageHandler.RPGCharacterSyncMessageHandler;
import bruce.roflcraft.network.MessageHandler.RequestRPGCharacterSyncMessageHandler;
import bruce.roflcraft.network.MessageHandler.SkillPointPurchaseMessageHandler;
import bruce.roflcraft.network.MessageHandler.SkillPointReciptMessasgeHandler;
import bruce.roflcraft.network.MessageHandler.SkillPointSpentMessageHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Class to handle the management of packet dispatching and registration
 * @author Lorrtath
 *
 */
public class RoflCraftPacketHandler
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
	private static byte m_id = 0;
	
	/**
	 * Registers all of the possible packet message systems.
	 * Any new systems should be defined in this method!
	 */
	public static void registerMessages()
	{
		registerMessage(SkillPointReciptMessasgeHandler.class, SkillPointPurchaseMessage.class, Side.CLIENT);
		registerMessage(RPGCharacterSyncMessageHandler.class, RPGCharacterSyncMessage.class, Side.CLIENT);
		registerServerMessages();
	}
	
	/**
	 * Registers the packets relevant only to the server.
	 */
	public static void registerServerMessages()
	{
		registerMessage(SkillPointPurchaseMessageHandler.class, SkillPointPurchaseMessage.class, Side.SERVER);
		registerMessage(RequestRPGCharacterSyncMessageHandler.class, RequestRPGCharacterSyncMessage.class , Side.SERVER);
		registerMessage(SkillPointSpentMessageHandler.class, SkillPointSpentMessage.class, Side.SERVER);
	}
	
	/**
	 * Registers a message packet & handler system
	 * @param handlerClass The handler class
	 * @param messageClass The packet class
	 * @param side The receiving side of the packet
	 */
	public static void registerMessage(Class handlerClass, Class messageClass, Side side)

	{
		INSTANCE.registerMessage(handlerClass, messageClass, m_id++, side);
	}
	
	/**
	 * Sends a packet to the server
	 * @param message The packet to send
	 */
	public static void sendToServer(IMessage message)
	{
		INSTANCE.sendToServer(message);
	}
	
	/**
	 * Sends the packet to all clients connected to the server
	 * @param message The packet to send
	 */
	public static void sendToAll(IMessage message)
	{
		INSTANCE.sendToAll(message);
	}
	
	/**
	 * Sends the packet to all clients in a specific dimension / world
	 * @param message The packet to send
	 * @param dimensionId The id of the dimension
	 */
	public static void sendToDimension(IMessage message, int dimensionId)
	{
		INSTANCE.sendToDimension(message, dimensionId);
	}
	
	/**
	 * Sends a message to all clients in a specific area 
	 * @param message The message to send
	 * @param targetPoint The area definition
	 */
	public static void sendToRadius(IMessage message, NetworkRegistry.TargetPoint targetPoint)
	{
		INSTANCE.sendToAllAround(message, targetPoint);
	}
	
	/**
	 * Sends a message to all clients in a specific area around a player
	 * @param message The packet to send
	 * @param player The player the are is centred around
	 * @param range The Range away from the player
	 */
	public static void sendToRadius(IMessage message,EntityPlayerMP player, float range)
	{
		NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(player.dimension, player.posX, player.posY, player.posZ, range);
		sendToRadius(message, point);
	}
	
	/**
	 * Sends a packet to a specific players client
	 * @param message The packet to send
	 * @param player The player who's client shall receive the packet
	 */
	public static void sendToPlayer(IMessage message, EntityPlayerMP player)
	{
		INSTANCE.sendTo(message, player);
	}
	
	
}
