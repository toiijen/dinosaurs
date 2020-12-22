
package net.mcreator.dinosaurworld.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.dinosaurworld.item.AncientTeleporterItem;
import net.mcreator.dinosaurworld.DinosaurworldElements;

@DinosaurworldElements.ModElement.Tag
public class LeDinoModItemGroup extends DinosaurworldElements.ModElement {
	public LeDinoModItemGroup(DinosaurworldElements instance) {
		super(instance, 60);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabledinomod") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(AncientTeleporterItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
