package bruce.roflcraft.network.Message;

import bruce.roflcraft.rpg.currency.CurrencyHolderType;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Network message for performing currency operations
 * @author Lorrtath
 *
 */
public class CurrencyOpperationMessage_Add implements IMessage
{	
	public CurrencyHolderType holderType;
	
	public ResourceLocation ownerUUID;
	
	int ammount;
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(holderType.ordinal());
		
	}
}
