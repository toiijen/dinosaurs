
package net.mcreator.dinosaurworld.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import net.mcreator.dinosaurworld.itemgroup.LeDinoModItemGroup;
import net.mcreator.dinosaurworld.DinosaurworldElements;

import java.util.List;

@DinosaurworldElements.ModElement.Tag
public class BasicGunPartsItem extends DinosaurworldElements.ModElement {
	@ObjectHolder("dinosaurworld:basicgunparts")
	public static final Item block = null;
	public BasicGunPartsItem(DinosaurworldElements instance) {
		super(instance, 68);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(LeDinoModItemGroup.tab).maxStackSize(64));
			setRegistryName("basicgunparts");
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

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("Cheapest sh*t you can get"));
			list.add(new StringTextComponent("Barely works"));
		}
	}
}
