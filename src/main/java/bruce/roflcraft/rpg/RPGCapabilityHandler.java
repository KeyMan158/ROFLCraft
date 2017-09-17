package bruce.roflcraft.rpg;

import com.sun.media.jfxmedia.events.PlayerEvent;

import bruce.roflcraft.handlers.RoflCraftPacketHandler;
import bruce.roflcraft.main.Reference;
import bruce.roflcraft.main.RoflCraft;
import bruce.roflcraft.network.Message.RequestRPGCharacterSyncMessage;
import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This object provides allows the attachment of RPG capabilities to
 * a player entity
 * @author Lorrtath
 */
public class RPGCapabilityHandler 
{
	public static final ResourceLocation CHARACTER_CAPABILITY = new ResourceLocation(Reference.MODID, "Character");
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent.Entity event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			event.addCapability(CHARACTER_CAPABILITY, new RPGCharacterProvider());
			RoflCraftPacketHandler.sendToServer(new RequestRPGCharacterSyncMessage());
		}
	}
}
