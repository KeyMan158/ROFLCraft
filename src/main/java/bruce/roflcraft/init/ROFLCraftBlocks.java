package bruce.roflcraft.init;

import bruce.roflcraft.blocks.TestBlock;
import bruce.roflcraft.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ROFLCraftBlocks 
{
	//this is an example test block
	public static TestBlock test_block;
	
	public static void init()
	{
		test_block = new TestBlock();
		test_block.setUnlocalizedName("test_block");
		test_block.setCreativeTab(CreativeTabs.tabTools);
	}
	
	public static void registerRenders()
	{
		registerRender(test_block);
	}
	
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5),"inventory"));
	}
}
