package bruce.roflcraft.init;

import org.apache.http.conn.routing.RouteInfo.TunnelType;

import bruce.roflcraft.main.Reference;
import bruce.roflcraft.main.RoflCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ROFLCraftItems 
{
	//Item for testing purposes
	public static Item test_item;
	
	public static void init()
	{
		test_item = registerItem(new Item(), "test_item", CreativeTabs.MISC).setUnlocalizedName("test_item").setCreativeTab(CreativeTabs.TOOLS);
	}
	
	
	public static void registerRenders()
	{
		registerRender(test_item);
	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5),"inventory"));
	}
	
	//=======================
	//Register Items
	//=======================
	public static Item registerItem(Item item, String name)
	{
		return registerItem(item, name, null);
	}
	
	public static Item registerItem(Item item, String name, CreativeTabs tab)
	{
		GameRegistry.register(item, new ResourceLocation(Reference.MODID, name));
		return item;
	}
}
