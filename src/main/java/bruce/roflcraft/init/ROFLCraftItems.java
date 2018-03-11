package bruce.roflcraft.init;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.http.conn.routing.RouteInfo.TunnelType;

import bruce.roflcraft.Item.ItemCharacterJournal;
import bruce.roflcraft.main.Reference;
import bruce.roflcraft.main.RoflCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.Console;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ROFLCraftItems 
{
	private static final Item test_item = new Item();
	private static final ItemCharacterJournal m_characterJournal = new ItemCharacterJournal();
	
	public static void init()
	{

	}
	
	public static void registerModel(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static void registerRenders()
	{
		//registerRender(test_item);
	}
	
	public static void registerRender(Item item)
	{
		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5),"inventory"));
	}
	
	//=======================
	//Register Items
	//=======================
	@Deprecated
	public static Item registerItem(Item item, String name)
	{
		return registerItem(item, name, null);
	}
	
	@Deprecated
	public static Item registerItem(Item item, String name, CreativeTabs tab)
	{
		return item;
	}
	
	/**
	 * Register the test item object
	 * @param event
	 */
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(m_characterJournal);
	}
	
	@SubscribeEvent
	public static void registerItemModels(ModelRegistryEvent event)
	{
		OBJLoader.INSTANCE.addDomain(Reference.MODID);
		registerModel(m_characterJournal);
	}
}
