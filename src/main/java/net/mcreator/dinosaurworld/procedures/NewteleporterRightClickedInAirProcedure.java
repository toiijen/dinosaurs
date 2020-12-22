package net.mcreator.dinosaurworld.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.network.play.server.SPlaySoundEventPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.dinosaurworld.world.dimension.MesozoicDimension;
import net.mcreator.dinosaurworld.world.dimension.JurassicworldDimension;
import net.mcreator.dinosaurworld.DinosaurworldElements;

@DinosaurworldElements.ModElement.Tag
public class NewteleporterRightClickedInAirProcedure extends DinosaurworldElements.ModElement {
	public NewteleporterRightClickedInAirProcedure(DinosaurworldElements instance) {
		super(instance, 34);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure NewteleporterRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure NewteleporterRightClickedInAir!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure NewteleporterRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure NewteleporterRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		if (((entity.dimension.getId()) == (0))) {
			if (!entity.world.isRemote && entity instanceof ServerPlayerEntity) {
				DimensionType destinationType = JurassicworldDimension.type;
				ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, (ServerPlayerEntity) entity, true, "field_184851_cj");
				ServerWorld nextWorld = entity.getServer().getWorld(destinationType);
				((ServerPlayerEntity) entity).connection.sendPacket(new SChangeGameStatePacket(4, 0));
				((ServerPlayerEntity) entity).teleport(nextWorld, nextWorld.getSpawnPoint().getX(), nextWorld.getSpawnPoint().getY() + 1,
						nextWorld.getSpawnPoint().getZ(), entity.rotationYaw, entity.rotationPitch);
				((ServerPlayerEntity) entity).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) entity).abilities));
				for (EffectInstance effectinstance : ((ServerPlayerEntity) entity).getActivePotionEffects()) {
					((ServerPlayerEntity) entity).connection.sendPacket(new SPlayEntityEffectPacket(entity.getEntityId(), effectinstance));
				}
				((ServerPlayerEntity) entity).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
			}
		} else if (((entity.dimension.getId()) == (-1))) {
			if (!entity.world.isRemote && entity instanceof ServerPlayerEntity) {
				DimensionType destinationType = MesozoicDimension.type;
				ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, (ServerPlayerEntity) entity, true, "field_184851_cj");
				ServerWorld nextWorld = entity.getServer().getWorld(destinationType);
				((ServerPlayerEntity) entity).connection.sendPacket(new SChangeGameStatePacket(4, 0));
				((ServerPlayerEntity) entity).teleport(nextWorld, nextWorld.getSpawnPoint().getX(), nextWorld.getSpawnPoint().getY() + 1,
						nextWorld.getSpawnPoint().getZ(), entity.rotationYaw, entity.rotationPitch);
				((ServerPlayerEntity) entity).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) entity).abilities));
				for (EffectInstance effectinstance : ((ServerPlayerEntity) entity).getActivePotionEffects()) {
					((ServerPlayerEntity) entity).connection.sendPacket(new SPlayEntityEffectPacket(entity.getEntityId(), effectinstance));
				}
				((ServerPlayerEntity) entity).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
			}
		} else if (((entity.dimension.getId()) == (1))) {
			if (!entity.world.isRemote && entity instanceof ServerPlayerEntity) {
				DimensionType destinationType = DimensionType.THE_NETHER;
				ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, (ServerPlayerEntity) entity, true, "field_184851_cj");
				ServerWorld nextWorld = entity.getServer().getWorld(destinationType);
				((ServerPlayerEntity) entity).connection.sendPacket(new SChangeGameStatePacket(4, 0));
				((ServerPlayerEntity) entity).teleport(nextWorld, nextWorld.getSpawnPoint().getX(), nextWorld.getSpawnPoint().getY() + 1,
						nextWorld.getSpawnPoint().getZ(), entity.rotationYaw, entity.rotationPitch);
				((ServerPlayerEntity) entity).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) entity).abilities));
				for (EffectInstance effectinstance : ((ServerPlayerEntity) entity).getActivePotionEffects()) {
					((ServerPlayerEntity) entity).connection.sendPacket(new SPlayEntityEffectPacket(entity.getEntityId(), effectinstance));
				}
				((ServerPlayerEntity) entity).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
			}
			entity.setPositionAndUpdate(x, (y + 200), z);
			{
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().sendMessage(new StringTextComponent("I told you"));
			}
		} else if (((entity.dimension.getId()) == (MesozoicDimension.type.getId()))) {
			if (!entity.world.isRemote && entity instanceof ServerPlayerEntity) {
				DimensionType destinationType = JurassicworldDimension.type;
				ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, (ServerPlayerEntity) entity, true, "field_184851_cj");
				ServerWorld nextWorld = entity.getServer().getWorld(destinationType);
				((ServerPlayerEntity) entity).connection.sendPacket(new SChangeGameStatePacket(4, 0));
				((ServerPlayerEntity) entity).teleport(nextWorld, nextWorld.getSpawnPoint().getX(), nextWorld.getSpawnPoint().getY() + 1,
						nextWorld.getSpawnPoint().getZ(), entity.rotationYaw, entity.rotationPitch);
				((ServerPlayerEntity) entity).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) entity).abilities));
				for (EffectInstance effectinstance : ((ServerPlayerEntity) entity).getActivePotionEffects()) {
					((ServerPlayerEntity) entity).connection.sendPacket(new SPlayEntityEffectPacket(entity.getEntityId(), effectinstance));
				}
				((ServerPlayerEntity) entity).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
			}
		} else if (((entity.dimension.getId()) == (JurassicworldDimension.type.getId()))) {
			if (!entity.world.isRemote && entity instanceof ServerPlayerEntity) {
				DimensionType destinationType = MesozoicDimension.type;
				ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, (ServerPlayerEntity) entity, true, "field_184851_cj");
				ServerWorld nextWorld = entity.getServer().getWorld(destinationType);
				((ServerPlayerEntity) entity).connection.sendPacket(new SChangeGameStatePacket(4, 0));
				((ServerPlayerEntity) entity).teleport(nextWorld, nextWorld.getSpawnPoint().getX(), nextWorld.getSpawnPoint().getY() + 1,
						nextWorld.getSpawnPoint().getZ(), entity.rotationYaw, entity.rotationPitch);
				((ServerPlayerEntity) entity).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) entity).abilities));
				for (EffectInstance effectinstance : ((ServerPlayerEntity) entity).getActivePotionEffects()) {
					((ServerPlayerEntity) entity).connection.sendPacket(new SPlayEntityEffectPacket(entity.getEntityId(), effectinstance));
				}
				((ServerPlayerEntity) entity).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
			}
		}
	}
}
