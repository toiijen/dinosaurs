package net.mcreator.dinosaurworld.procedures;

import net.minecraft.world.GameType;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.dinosaurworld.item.ShellshieldItem;
import net.mcreator.dinosaurworld.DinosaurworldElements;

@DinosaurworldElements.ModElement.Tag
public class ShellshieldToolInInventoryTickProcedure extends DinosaurworldElements.ModElement {
	public ShellshieldToolInInventoryTickProcedure(DinosaurworldElements instance) {
		super(instance, 33);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ShellshieldToolInInventoryTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((!(new ItemStack(ShellshieldItem.block, (int) (1))
				.getItem() == ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).setGameType(GameType.SURVIVAL);
		}
	}
}
