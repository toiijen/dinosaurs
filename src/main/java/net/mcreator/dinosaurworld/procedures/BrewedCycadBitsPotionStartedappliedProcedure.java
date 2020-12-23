package net.mcreator.dinosaurworld.procedures;

@DinosaurworldElements.ModElement.Tag
public class BrewedCycadBitsPotionStartedappliedProcedure extends DinosaurworldElements.ModElement {

	public BrewedCycadBitsPotionStartedappliedProcedure(DinosaurworldElements instance) {
		super(instance, 57);

	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BrewedCycadBitsPotionStartedapplied!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.NAUSEA, (int) 30, (int) 15));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 6200, (int) 5));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, (int) 6200, (int) 3));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).setHealth((float) (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) + 2));

	}

}
