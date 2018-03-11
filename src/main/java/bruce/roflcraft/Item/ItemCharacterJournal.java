package bruce.roflcraft.Item;

import bruce.roflcraft.main.Reference;
import bruce.roflcraft.main.RoflCraft;
import bruce.roflcraft.settings.GUIIDs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Item class for the character journal that opens up the character sheet
 * @author Lorrtath
 *
 */
public class ItemCharacterJournal extends Item
{
	private static final String DEFAULT_NAME  = "character_journal";
	// ToDo On item used
	
	public ItemCharacterJournal()
	{
		super();
		setUnlocalizedName(DEFAULT_NAME);
		setCreativeTab(CreativeTabs.TOOLS);
		setRegistryName(new ResourceLocation(Reference.MODID + ":" + DEFAULT_NAME));
	}
	
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	playerIn.openGui(RoflCraft.instance, GUIIDs.CharacterSheet.ordinal(), (World)playerIn.getEntityWorld(), (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
    	return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
