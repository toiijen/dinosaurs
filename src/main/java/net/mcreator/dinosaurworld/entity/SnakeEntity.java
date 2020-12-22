
package net.mcreator.dinosaurworld.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.dinosaurworld.DinosaurworldElements;

@DinosaurworldElements.ModElement.Tag
public class SnakeEntity extends DinosaurworldElements.ModElement {
	public static EntityType entity = null;
	public SnakeEntity(DinosaurworldElements instance) {
		super(instance, 25);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1.5f, 0.0625f)).build("snake")
						.setRegistryName("snake");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -16738048, -13382656, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("snake"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("jungle")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("jungle_hills")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("jungle_edge")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("bamboo_jungle")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("bamboo_jungle_hills")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 10, 3, 30));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AnimalEntity::func_223315_a);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelsnake_model(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("dinosaurworld:textures/snake.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 5;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(3, new SwimGoal(this));
			this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, (float) 0.8));
			this.targetSelector.addGoal(5, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.targetSelector.addGoal(6, new NearestAttackableTargetGoal(this, AnimalEntity.class, false, true));
			this.goalSelector.addGoal(7, new MeleeAttackGoal(this, 1.2, true));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}
	}

	public static class Modelsnake_model extends EntityModel<Entity> {
		private final RendererModel tail1;
		private final RendererModel tail2;
		private final RendererModel tail3;
		private final RendererModel tail4;
		private final RendererModel tail5;
		private final RendererModel tail6;
		private final RendererModel tail7;
		private final RendererModel tail8;
		private final RendererModel tail9;
		private final RendererModel tail10;
		private final RendererModel bb_main;
		public Modelsnake_model() {
			textureWidth = 32;
			textureHeight = 32;
			tail1 = new RendererModel(this);
			tail1.setRotationPoint(0.0F, 23.75F, -5.0F);
			tail1.cubeList.add(new ModelBox(tail1, 0, 12, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));
			tail2 = new RendererModel(this);
			tail2.setRotationPoint(0.0F, 0.0F, 3.0F);
			tail1.addChild(tail2);
			tail2.cubeList.add(new ModelBox(tail2, 10, 4, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));
			tail3 = new RendererModel(this);
			tail3.setRotationPoint(0.0F, 0.0F, 3.0F);
			tail2.addChild(tail3);
			tail3.cubeList.add(new ModelBox(tail3, 10, 0, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));
			tail4 = new RendererModel(this);
			tail4.setRotationPoint(0.0F, 0.0F, 3.0F);
			tail3.addChild(tail4);
			tail4.cubeList.add(new ModelBox(tail4, 10, 10, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));
			tail5 = new RendererModel(this);
			tail5.setRotationPoint(0.0F, 0.0F, 3.0F);
			tail4.addChild(tail5);
			tail5.cubeList.add(new ModelBox(tail5, 5, 9, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));
			tail6 = new RendererModel(this);
			tail6.setRotationPoint(0.0F, 0.0F, 3.0F);
			tail5.addChild(tail6);
			tail6.cubeList.add(new ModelBox(tail6, 0, 8, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));
			tail7 = new RendererModel(this);
			tail7.setRotationPoint(0.0F, 0.0F, 3.0F);
			tail6.addChild(tail7);
			tail7.cubeList.add(new ModelBox(tail7, 5, 5, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));
			tail8 = new RendererModel(this);
			tail8.setRotationPoint(0.0F, -0.25F, 3.0F);
			tail7.addChild(tail8);
			tail8.cubeList.add(new ModelBox(tail8, 5, 1, -0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F, false));
			tail9 = new RendererModel(this);
			tail9.setRotationPoint(0.0F, 0.0F, 3.0F);
			tail8.addChild(tail9);
			tail9.cubeList.add(new ModelBox(tail9, 0, 4, -0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F, false));
			tail10 = new RendererModel(this);
			tail10.setRotationPoint(0.0F, 0.0F, 3.0F);
			tail9.addChild(tail10);
			tail10.cubeList.add(new ModelBox(tail10, 0, 0, -0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F, false));
			bb_main = new RendererModel(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.cubeList.add(new ModelBox(bb_main, 5, 13, -0.5F, -1.0F, -8.0F, 1, 1, 3, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			tail1.render(f5);
			bb_main.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.tail1.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
			this.tail2.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tail3.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
			this.bb_main.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.bb_main.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.tail4.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tail5.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
			this.tail10.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tail6.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tail7.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
			this.tail8.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tail9.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}
