
package net.mcreator.dinosaurworld.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.player.PlayerEntity;

import net.mcreator.dinosaurworld.procedures.BrewedCycadBitsPotionStartedappliedProcedure;
import net.mcreator.dinosaurworld.itemgroup.LeDinoModItemGroup;
import net.mcreator.dinosaurworld.DinosaurworldElements;

@DinosaurworldElements.ModElement.Tag
public class BerserkJuiceItem extends DinosaurworldElements.ModElement {
	@ObjectHolder("dinosaurworld:berserkjuice")
	public static final Item block = null;
	public BerserkJuiceItem(DinosaurworldElements instance) {
		super(instance, 62);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(LeDinoModItemGroup.tab).maxStackSize(64)
					.food((new Food.Builder()).hunger(4).saturation(0.3f).setAlwaysEdible().meat().build()));
			setRegistryName("berserkjuice");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.DRINK;
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
			ItemStack itemstack = ar.getResult();
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				BrewedCycadBitsPotionStartedappliedProcedure.executeProcedure($_dependencies);
			}
			return ar;
		}
	}
}
