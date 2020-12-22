package net.mcreator.dinosaurworld.procedures;

import net.minecraft.world.GameType;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.dinosaurworld.DinosaurworldElements;

@DinosaurworldElements.ModElement.Tag
public class ShellshieldToolInHandTickProcedure extends DinosaurworldElements.ModElement {
	public ShellshieldToolInHandTickProcedure(DinosaurworldElements instance) {
		super(instance, 48);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ShellshieldToolInHandTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.isSneaking())) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).setGameType(GameType.CREATIVE);
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.disableDamage = (entity.isSneaking());
				((PlayerEntity) entity).sendPlayerAbilities();
			}
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.allowFlying = (false);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.allowEdit = (false);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, (int) 60, (int) 1, (false), (false)));
		} else {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).setGameType(GameType.SURVIVAL);
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.allowEdit = (true);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
		}
	}
}
