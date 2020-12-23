
package net.mcreator.dinosaurworld.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.dinosaurworld.itemgroup.LeDinoModItemGroup;
import net.mcreator.dinosaurworld.DinosaurworldElements;

@DinosaurworldElements.ModElement.Tag
public class FossilitemItem extends DinosaurworldElements.ModElement {
	@ObjectHolder("dinosaurworld:fossilitem")
	public static final Item block = null;
	public FossilitemItem(DinosaurworldElements instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(LeDinoModItemGroup.tab).maxStackSize(64));
			setRegistryName("fossilitem");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
