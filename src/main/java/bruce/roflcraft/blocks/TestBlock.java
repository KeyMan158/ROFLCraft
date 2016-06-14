package bruce.roflcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TestBlock extends Block
{
	public TestBlock() 
	{
		super(Material.glass);
		setRegistryName("test_block");
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
		setLightLevel(1); //0 = no light, 1 = full
		setHardness(4); //Obsidian - 50, cobble = 10
		setHarvestLevel("pickaxe", 1); //stone pickaxe
	}
}
