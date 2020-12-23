package net.mcreator.dinosaurworld.procedures;

import net.minecraft.world.World;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.dinosaurworld.DinosaurworldElements;

import java.util.Random;

@DinosaurworldElements.ModElement.Tag
public class SurvivalArrowstaffRightClickedInAirProcedure extends DinosaurworldElements.ModElement {
	public SurvivalArrowstaffRightClickedInAirProcedure(DinosaurworldElements instance) {
		super(instance, 55);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SurvivalArrowstaffRightClickedInAir!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure SurvivalArrowstaffRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SurvivalArrowstaffRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		World world = (World) dependencies.get("world");
		if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.ARROW, (int) (1))) : false)) {
			if (!world.isRemote && entity instanceof LivingEntity) {
				ArrowEntity entityToSpawn = new ArrowEntity(world, (LivingEntity) entity);
				entityToSpawn.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, ((float) 10) * 2.0F, 0);
				entityToSpawn.setDamage(((float) 5) * 2.0F);
				entityToSpawn.setKnockbackStrength((int) 5);
				world.addEntity(entityToSpawn);
			}
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).inventory.clearMatchingItems(p -> new ItemStack(Items.ARROW, (int) (1)).getItem() == p.getItem(), (int) 1);
			if (itemstack.attemptDamageItem((int) 1, new Random(), null)) {
				itemstack.shrink(1);
				itemstack.setDamage(0);
			}
		}
	}
}
