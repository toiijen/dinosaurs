package net.mcreator.dinosaurworld.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.dinosaurworld.DinosaurworldElements;

@DinosaurworldElements.ModElement.Tag
public class ElektrickyplotEntityCollidesInTheBlockProcedure extends DinosaurworldElements.ModElement {
	public ElektrickyplotEntityCollidesInTheBlockProcedure(DinosaurworldElements instance) {
		super(instance, 11);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ElektrickyplotEntityCollidesInTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, (float) 1);
		entity.setMotion(-1, -1, -1);
	}
}
