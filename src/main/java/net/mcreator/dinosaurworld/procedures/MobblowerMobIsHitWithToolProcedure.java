package net.mcreator.dinosaurworld.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.dinosaurworld.DinosaurworldElements;

@DinosaurworldElements.ModElement.Tag
public class MobblowerMobIsHitWithToolProcedure extends DinosaurworldElements.ModElement {
	public MobblowerMobIsHitWithToolProcedure(DinosaurworldElements instance) {
		super(instance, 45);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MobblowerMobIsHitWithTool!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotion(-30, -20, -30);
	}
}
