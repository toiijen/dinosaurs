package net.mcreator.dinosaurworld.procedures;

@DinosaurworldElements.ModElement.Tag
public class AroowstaffRightClickedInAirProcedure extends DinosaurworldElements.ModElement {

	public AroowstaffRightClickedInAirProcedure(DinosaurworldElements instance) {
		super(instance, 44);

	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure AroowstaffRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure AroowstaffRightClickedInAir!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");

		if (!world.isRemote && entity instanceof LivingEntity) {
			ArrowEntity entityToSpawn = new ArrowEntity(world, (LivingEntity) entity);
			entityToSpawn.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, ((float) 100) * 2.0F, 0);
			entityToSpawn.setDamage(((float) 100) * 2.0F);
			entityToSpawn.setKnockbackStrength((int) 5);
			world.addEntity(entityToSpawn);
		}

	}

}
