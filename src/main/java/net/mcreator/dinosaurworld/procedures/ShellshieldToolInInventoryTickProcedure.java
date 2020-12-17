package net.mcreator.dinosaurworld.procedures;

@DinosaurworldElements.ModElement.Tag
public class ShellshieldToolInInventoryTickProcedure extends DinosaurworldElements.ModElement {

	public ShellshieldToolInInventoryTickProcedure(DinosaurworldElements instance) {
		super(instance, 33);

	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies){
		if(dependencies.get("entity")==null){
			System.err.println("Failed to load dependency entity for procedure ShellshieldToolInInventoryTick!");
			return;
		}

            Entity entity =(Entity)dependencies.get("entity" );

		if (((!)&&)) {if(entity instanceof PlayerEntity)
	((PlayerEntity)entity).setGameType(GameType.SURVIVAL);}

	}

}
