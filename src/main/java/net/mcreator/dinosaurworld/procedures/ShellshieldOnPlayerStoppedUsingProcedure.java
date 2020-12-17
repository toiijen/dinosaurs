package net.mcreator.dinosaurworld.procedures;

@DinosaurworldElements.ModElement.Tag
public class ShellshieldOnPlayerStoppedUsingProcedure extends DinosaurworldElements.ModElement {

	public ShellshieldOnPlayerStoppedUsingProcedure(DinosaurworldElements instance) {
		super(instance, 28);

	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ShellshieldOnPlayerStoppedUsing!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).setGameType(GameType.SURVIVAL);

	}

}
