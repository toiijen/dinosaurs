
package net.mcreator.dinosaurworld.item;

@DinosaurworldElements.ModElement.Tag
public class ShellItem extends DinosaurworldElements.ModElement {

	@ObjectHolder("dinosaurworld:shell")
	public static final Item block = null;

	public ShellItem(DinosaurworldElements instance) {
		super(instance, 29);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MATERIALS).maxStackSize(64));
			setRegistryName("shell");
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
