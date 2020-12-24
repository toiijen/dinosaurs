
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import net.mcreator.dinosaurworld.itemgroup.LeDinoModItemGroup;
import net.mcreator.dinosaurworld.DinosaurworldElements;

import java.util.Random;
import java.util.EnumSet;

@DinosaurworldElements.ModElement.Tag
public class PterodaktylEntity extends DinosaurworldElements.ModElement {
	public static EntityType entity = null;
	public PterodaktylEntity(DinosaurworldElements instance) {
		super(instance, 51);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("pterodaktyl")
						.setRegistryName("pterodaktyl");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -7185895, -7379386, new Item.Properties().group(LeDinoModItemGroup.tab))
				.setRegistryName("pterodaktyl"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("mountains")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("snowy_mountains")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("desert_hills")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("wooded_hills")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("taiga_hills")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("mountain_edge")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("jungle")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("jungle_hills")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("stone_shore")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("birch_forest_hills")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("snowy_taiga_hills")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("giant_tree_taiga_hills")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("wooded_mountains")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 20, 3, 30));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AnimalEntity::func_223315_a);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelpterodaktyl(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("dinosaurworld:textures/ptero1texture.png");
				}
			};
		});
	}
	public static class CustomEntity extends CreatureEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 2;
			setNoAI(false);
			this.moveController = new FlyingMovementController(this);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.8, 20) {
				@Override
				protected Vec3d getPosition() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.posX + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.posY + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.posZ + ((random.nextFloat() * 2 - 1) * 16);
					return new Vec3d(dir_x, dir_y, dir_z);
				}
			});
			this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(3, new SwimGoal(this));
			this.goalSelector.addGoal(4, new PanicGoal(this, 1.2));
			this.goalSelector.addGoal(5, new Goal() {
				{
					this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
				}
				public boolean shouldExecute() {
					if (CustomEntity.this.getAttackTarget() != null && !CustomEntity.this.getMoveHelper().isUpdating()) {
						return true;
					} else {
						return false;
					}
				}

				@Override
				public boolean shouldContinueExecuting() {
					return CustomEntity.this.getMoveHelper().isUpdating() && CustomEntity.this.getAttackTarget() != null
							&& CustomEntity.this.getAttackTarget().isAlive();
				}

				@Override
				public void startExecuting() {
					LivingEntity livingentity = CustomEntity.this.getAttackTarget();
					Vec3d vec3d = livingentity.getEyePosition(1);
					CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
				}

				@Override
				public void tick() {
					LivingEntity livingentity = CustomEntity.this.getAttackTarget();
					if (CustomEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
						CustomEntity.this.attackEntityAsMob(livingentity);
					} else {
						double d0 = CustomEntity.this.getDistanceSq(livingentity);
						if (d0 < 16) {
							Vec3d vec3d = livingentity.getEyePosition(1);
							CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
						}
					}
				}
			});
			this.targetSelector.addGoal(6, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(7, new FollowMobGoal(this, (float) 1, 10, 5));
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
		public void fall(float l, float d) {
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
			this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
			this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.3);
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		public void livingTick() {
			super.livingTick();
			this.setNoGravity(true);
		}
	}

	public static class Modelpterodaktyl extends EntityModel<Entity> {
		private final RendererModel root;
		private final RendererModel body;
		private final RendererModel neck_rear;
		private final RendererModel neck_mid;
		private final RendererModel neck_end;
		private final RendererModel head;
		private final RendererModel head_r1;
		private final RendererModel head_r2;
		private final RendererModel head_r3;
		private final RendererModel head_r4;
		private final RendererModel head_r5;
		private final RendererModel jaw;
		private final RendererModel right_wing_rear;
		private final RendererModel right_hand;
		private final RendererModel right_ring;
		private final RendererModel right_index;
		private final RendererModel right_middle;
		private final RendererModel right_wing_elbow;
		private final RendererModel right_wing_elbow_r1;
		private final RendererModel right_wing_elbow_r2;
		private final RendererModel left_wing_rear;
		private final RendererModel left_hand;
		private final RendererModel left_index;
		private final RendererModel left_middle;
		private final RendererModel left_ring;
		private final RendererModel left_wing_elbow;
		private final RendererModel left_wing_elbow_r1;
		private final RendererModel left_wing_elbow_r2;
		private final RendererModel midSection;
		private final RendererModel pelvis;
		private final RendererModel left_leg_rear;
		private final RendererModel left_leg_front;
		private final RendererModel left_foot;
		private final RendererModel left_claws;
		private final RendererModel right_leg_rear;
		private final RendererModel right_leg_front;
		private final RendererModel right_foot;
		private final RendererModel right_claws;
		private final RendererModel tail;
		public Modelpterodaktyl() {
			textureWidth = 16;
			textureHeight = 16;
			root = new RendererModel(this);
			root.setRotationPoint(0.0F, 20.75F, 1.5F);
			body = new RendererModel(this);
			body.setRotationPoint(0.0F, 0.0F, 0.0F);
			root.addChild(body);
			body.cubeList.add(new ModelBox(body, 8, 11, -0.8F, -0.75F, -2.5F, 1, 1, 2, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 10, 12, -0.7F, -0.65F, -2.7F, 1, 0, 0, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 10, 12, -0.4F, -0.55F, -2.9F, 0, 0, 0, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 12, -0.6F, 0.35F, -2.4F, 1, 0, 2, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 12, 0.6F, 0.35F, -2.3F, 0, 0, 2, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 12, -0.7F, 0.35F, -2.3F, 0, 0, 2, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 10, 12, -0.9F, -0.65F, -2.4F, 0, 0, 0, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 10, 12, 0.8F, -0.65F, -2.4F, 0, 0, 0, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 10, 12, -0.9F, -0.55F, -1.5F, 0, 0, 0, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 10, 12, 0.8F, -0.55F, -1.5F, 0, 0, 0, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 10, 12, -0.9F, -0.45F, -0.9F, 0, 0, 0, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 10, 12, 0.8F, -0.45F, -0.9F, 0, 0, 0, 0.0F, false));
			neck_rear = new RendererModel(this);
			neck_rear.setRotationPoint(0.0F, -0.35F, -2.9F);
			body.addChild(neck_rear);
			neck_rear.cubeList.add(new ModelBox(neck_rear, 8, 12, -0.4F, -0.2F, -0.6F, 0, 0, 0, 0.0F, false));
			neck_rear.cubeList.add(new ModelBox(neck_rear, 4, 13, -0.3F, 0.4F, -0.6F, 0, 0, 0, 0.0F, false));
			neck_mid = new RendererModel(this);
			neck_mid.setRotationPoint(0.0F, -0.125F, -0.7F);
			neck_rear.addChild(neck_mid);
			neck_mid.cubeList.add(new ModelBox(neck_mid, 9, 13, -0.4F, -0.075F, -0.5F, 0, 0, 0, 0.0F, false));
			neck_mid.cubeList.add(new ModelBox(neck_mid, 4, 13, -0.3F, 0.525F, -0.5F, 0, 0, 0, 0.0F, false));
			neck_end = new RendererModel(this);
			neck_end.setRotationPoint(0.0F, 0.0F, -0.6F);
			neck_mid.addChild(neck_end);
			neck_end.cubeList.add(new ModelBox(neck_end, 8, 14, -0.4F, -0.075F, -0.5F, 0, 0, 0, 0.0F, false));
			neck_end.cubeList.add(new ModelBox(neck_end, 4, 13, -0.3F, 0.525F, -0.5F, 0, 0, 0, 0.0F, false));
			head = new RendererModel(this);
			head.setRotationPoint(0.0F, 0.3952F, -0.429F);
			neck_end.addChild(head);
			head.cubeList.add(new ModelBox(head, 10, 13, -0.3F, -0.2702F, -2.271F, 0, 0, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 10, 13, -0.2F, -0.2702F, -3.571F, 0, 0, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 10, 13, -0.2F, -0.3702F, -2.171F, 0, 0, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 13, 4, -0.4F, -0.5702F, -0.871F, 0, 0, 0, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 13, 4, -0.4F, -0.5702F, -0.871F, 0, 0, 0, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 2, 12, -0.3F, 0.0298F, -0.871F, 0, 0, 0, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 8, 7, 0.41F, -0.3702F, -0.771F, 0, 0, 0, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 8, 7, -0.41F, -0.3702F, -0.771F, 0, 0, 0, 0.0F, true));
			head.cubeList.add(new ModelBox(head, 8, 7, 0.41F, -0.4702F, -0.521F, 0, 0, 0, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 8, 7, -0.41F, -0.4702F, -0.521F, 0, 0, 0, 0.0F, true));
			head.cubeList.add(new ModelBox(head, 8, 7, 0.409F, -0.2702F, -0.521F, 0, 0, 0, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 8, 7, -0.409F, -0.2702F, -0.521F, 0, 0, 0, 0.0F, true));
			head.cubeList.add(new ModelBox(head, 8, 7, 0.409F, -0.3702F, -0.271F, 0, 0, 0, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 8, 7, -0.409F, -0.3702F, -0.271F, 0, 0, 0, 0.0F, true));
			head.cubeList.add(new ModelBox(head, 14, 7, 0.41F, -0.3702F, -0.521F, 0, 0, 0, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 14, 7, -0.41F, -0.3702F, -0.521F, 0, 0, 0, 0.0F, true));
			head_r1 = new RendererModel(this);
			head_r1.setRotationPoint(0.0F, -0.5046F, -1.1886F);
			head.addChild(head_r1);
			setRotationAngle(head_r1, 0.2618F, 0.0F, 0.0F);
			head_r1.cubeList.add(new ModelBox(head_r1, 10, 13, -0.1F, -0.1F, -0.2F, 0, 0, 1, 0.0F, false));
			head_r2 = new RendererModel(this);
			head_r2.setRotationPoint(0.0F, -0.7634F, -0.2227F);
			head.addChild(head_r2);
			setRotationAngle(head_r2, 0.2618F, 0.0F, 0.0F);
			head_r2.cubeList.add(new ModelBox(head_r2, 10, 13, -0.1F, -0.1F, -0.2F, 0, 0, 1, 0.0F, false));
			head_r3 = new RendererModel(this);
			head_r3.setRotationPoint(0.0F, -0.5702F, -0.171F);
			head.addChild(head_r3);
			setRotationAngle(head_r3, 0.2618F, 0.0F, 0.0F);
			head_r3.cubeList.add(new ModelBox(head_r3, 10, 13, -0.1F, -0.1F, -0.4F, 0, 0, 0, 0.0F, false));
			head_r4 = new RendererModel(this);
			head_r4.setRotationPoint(0.0F, -0.3202F, -2.821F);
			head.addChild(head_r4);
			setRotationAngle(head_r4, 0.0436F, 0.0F, 0.0F);
			head_r4.cubeList.add(new ModelBox(head_r4, 10, 13, -0.1F, 0.0F, -0.65F, 0, 0, 1, 0.0F, false));
			head_r5 = new RendererModel(this);
			head_r5.setRotationPoint(0.0F, -0.4202F, -1.421F);
			head.addChild(head_r5);
			setRotationAngle(head_r5, 0.0873F, 0.0F, 0.0F);
			head_r5.cubeList.add(new ModelBox(head_r5, 10, 13, -0.1F, -0.05F, -0.65F, 0, 0, 1, 0.0F, false));
			jaw = new RendererModel(this);
			jaw.setRotationPoint(0.0F, 0.0131F, -0.8376F);
			head.addChild(jaw);
			jaw.cubeList.add(new ModelBox(jaw, 2, 12, -0.2F, -0.0833F, -1.3333F, 0, 0, 1, 0.0F, false));
			jaw.cubeList.add(new ModelBox(jaw, 2, 12, -0.1F, 0.0167F, -1.2333F, 0, 0, 1, 0.0F, false));
			jaw.cubeList.add(new ModelBox(jaw, 2, 12, -0.1F, -0.0833F, -2.6333F, 0, 0, 1, 0.0F, false));
			right_wing_rear = new RendererModel(this);
			right_wing_rear.setRotationPoint(-0.95F, -0.2F, -1.95F);
			body.addChild(right_wing_rear);
			right_wing_rear.cubeList.add(new ModelBox(right_wing_rear, 7, 12, -3.45F, -0.15F, -0.35F, 3, 0, 0, 0.0F, true));
			right_wing_rear.cubeList.add(new ModelBox(right_wing_rear, 10, 13, -3.55F, -0.15F, -0.4F, 0, 0, 0, 0.0F, true));
			right_wing_rear.cubeList.add(new ModelBox(right_wing_rear, 0, 2, -3.45F, -0.05F, -0.1F, 3, 0, 2, 0.0F, true));
			right_hand = new RendererModel(this);
			right_hand.setRotationPoint(-3.4F, 0.0F, -0.45F);
			right_wing_rear.addChild(right_hand);
			right_ring = new RendererModel(this);
			right_ring.setRotationPoint(-0.1F, 0.0F, 0.025F);
			right_hand.addChild(right_ring);
			right_ring.cubeList.add(new ModelBox(right_ring, 11, 6, -0.05F, -0.05F, -0.225F, 0, 0, 0, 0.0F, false));
			right_index = new RendererModel(this);
			right_index.setRotationPoint(0.2F, 0.0F, 0.025F);
			right_hand.addChild(right_index);
			right_index.cubeList.add(new ModelBox(right_index, 11, 6, -0.05F, -0.05F, -0.225F, 0, 0, 0, 0.0F, false));
			right_middle = new RendererModel(this);
			right_middle.setRotationPoint(0.05F, 0.0F, 0.025F);
			right_hand.addChild(right_middle);
			right_middle.cubeList.add(new ModelBox(right_middle, 11, 6, -0.05F, -0.05F, -0.225F, 0, 0, 0, 0.0F, false));
			right_wing_elbow = new RendererModel(this);
			right_wing_elbow.setRotationPoint(-3.55F, -0.05F, -0.225F);
			right_wing_rear.addChild(right_wing_elbow);
			right_wing_elbow_r1 = new RendererModel(this);
			right_wing_elbow_r1.setRotationPoint(0.0934F, 0.05F, 0.2712F);
			right_wing_elbow.addChild(right_wing_elbow_r1);
			setRotationAngle(right_wing_elbow_r1, 0.0F, 0.1745F, 0.0F);
			right_wing_elbow_r1.cubeList.add(new ModelBox(right_wing_elbow_r1, 0, 0, -3.15F, -0.05F, -0.15F, 3, 0, 2, 0.0F, false));
			right_wing_elbow_r2 = new RendererModel(this);
			right_wing_elbow_r2.setRotationPoint(0.05F, 0.15F, 0.025F);
			right_wing_elbow.addChild(right_wing_elbow_r2);
			setRotationAngle(right_wing_elbow_r2, 0.0F, 0.1745F, 0.0F);
			right_wing_elbow_r2.cubeList.add(new ModelBox(right_wing_elbow_r2, 7, 12, -3.15F, -0.25F, -0.15F, 3, 0, 0, 0.0F, true));
			left_wing_rear = new RendererModel(this);
			left_wing_rear.setRotationPoint(0.95F, -0.2F, -1.95F);
			body.addChild(left_wing_rear);
			left_wing_rear.cubeList.add(new ModelBox(left_wing_rear, 8, 12, -0.05F, -0.15F, -0.35F, 3, 0, 0, 0.0F, false));
			left_wing_rear.cubeList.add(new ModelBox(left_wing_rear, 11, 12, 3.15F, -0.15F, -0.4F, 0, 0, 0, 0.0F, false));
			left_wing_rear.cubeList.add(new ModelBox(left_wing_rear, 0, 2, -0.05F, -0.05F, -0.1F, 3, 0, 2, 0.0F, true));
			left_hand = new RendererModel(this);
			left_hand.setRotationPoint(3.35F, 0.05F, -0.35F);
			left_wing_rear.addChild(left_hand);
			left_index = new RendererModel(this);
			left_index.setRotationPoint(-0.15F, -0.05F, -0.075F);
			left_hand.addChild(left_index);
			left_index.cubeList.add(new ModelBox(left_index, 11, 6, -0.05F, -0.05F, -0.225F, 0, 0, 0, 0.0F, false));
			left_middle = new RendererModel(this);
			left_middle.setRotationPoint(0.0F, -0.05F, -0.075F);
			left_hand.addChild(left_middle);
			left_middle.cubeList.add(new ModelBox(left_middle, 11, 6, -0.05F, -0.05F, -0.225F, 0, 0, 0, 0.0F, false));
			left_ring = new RendererModel(this);
			left_ring.setRotationPoint(0.15F, -0.05F, -0.075F);
			left_hand.addChild(left_ring);
			left_ring.cubeList.add(new ModelBox(left_ring, 11, 6, -0.05F, -0.05F, -0.225F, 0, 0, 0, 0.0F, false));
			left_wing_elbow = new RendererModel(this);
			left_wing_elbow.setRotationPoint(3.55F, -0.05F, -0.225F);
			left_wing_rear.addChild(left_wing_elbow);
			left_wing_elbow_r1 = new RendererModel(this);
			left_wing_elbow_r1.setRotationPoint(-0.0934F, 0.05F, 0.2712F);
			left_wing_elbow.addChild(left_wing_elbow_r1);
			setRotationAngle(left_wing_elbow_r1, 0.0F, -0.1745F, 0.0F);
			left_wing_elbow_r1.cubeList.add(new ModelBox(left_wing_elbow_r1, 0, 4, -0.05F, -0.05F, -0.15F, 3, 0, 2, 0.0F, false));
			left_wing_elbow_r2 = new RendererModel(this);
			left_wing_elbow_r2.setRotationPoint(-0.05F, 0.15F, 0.025F);
			left_wing_elbow.addChild(left_wing_elbow_r2);
			setRotationAngle(left_wing_elbow_r2, 0.0F, -0.1745F, 0.0F);
			left_wing_elbow_r2.cubeList.add(new ModelBox(left_wing_elbow_r2, 7, 13, -0.05F, -0.25F, -0.15F, 3, 0, 0, 0.0F, true));
			midSection = new RendererModel(this);
			midSection.setRotationPoint(0.0F, 0.0F, 0.0F);
			body.addChild(midSection);
			midSection.cubeList.add(new ModelBox(midSection, 9, 12, -0.75F, -0.5F, 0.0F, 1, 0, 1, 0.0F, false));
			midSection.cubeList.add(new ModelBox(midSection, 1, 12, -0.55F, 0.4F, 0.0F, 1, 0, 1, 0.0F, false));
			midSection.cubeList.add(new ModelBox(midSection, 1, 12, 0.55F, 0.4F, 0.0F, 0, 0, 1, 0.0F, false));
			midSection.cubeList.add(new ModelBox(midSection, 1, 12, -0.65F, 0.4F, 0.0F, 0, 0, 1, 0.0F, false));
			pelvis = new RendererModel(this);
			pelvis.setRotationPoint(0.0F, -0.0208F, 1.625F);
			midSection.addChild(pelvis);
			pelvis.cubeList.add(new ModelBox(pelvis, 10, 12, -0.55F, -0.3792F, -0.125F, 1, 0, 1, 0.0F, true));
			pelvis.cubeList.add(new ModelBox(pelvis, 3, 12, -0.45F, 0.3708F, -0.125F, 0, 0, 0, 0.0F, false));
			pelvis.cubeList.add(new ModelBox(pelvis, 3, 12, -0.35F, 0.3708F, 0.675F, 0, 0, 0, 0.0F, false));
			left_leg_rear = new RendererModel(this);
			left_leg_rear.setRotationPoint(0.65F, -0.0042F, 0.775F);
			pelvis.addChild(left_leg_rear);
			left_leg_rear.cubeList.add(new ModelBox(left_leg_rear, 7, 12, -0.1F, -0.175F, -0.2F, 0, 0, 1, 0.0F, false));
			left_leg_front = new RendererModel(this);
			left_leg_front.setRotationPoint(0.0F, 0.0F, 1.0F);
			left_leg_rear.addChild(left_leg_front);
			left_leg_front.cubeList.add(new ModelBox(left_leg_front, 7, 12, -0.1F, -0.175F, 0.0F, 0, 0, 0, 0.0F, false));
			left_foot = new RendererModel(this);
			left_foot.setRotationPoint(0.0F, 0.0F, 0.7F);
			left_leg_front.addChild(left_foot);
			left_foot.cubeList.add(new ModelBox(left_foot, 7, 12, -0.2F, -0.175F, 0.0F, 0, 0, 0, 0.0F, false));
			left_foot.cubeList.add(new ModelBox(left_foot, 7, 12, -0.2F, 0.375F, 0.1F, 0, 0, 0, 0.0F, false));
			left_claws = new RendererModel(this);
			left_claws.setRotationPoint(0.0F, 0.55F, 0.2F);
			left_foot.addChild(left_claws);
			left_claws.cubeList.add(new ModelBox(left_claws, 11, 5, -0.2F, -0.025F, -0.1F, 0, 0, 0, 0.0F, false));
			left_claws.cubeList.add(new ModelBox(left_claws, 11, 5, 0.1F, -0.025F, -0.1F, 0, 0, 0, 0.0F, false));
			left_claws.cubeList.add(new ModelBox(left_claws, 11, 5, -0.05F, -0.025F, -0.1F, 0, 0, 0, 0.0F, false));
			right_leg_rear = new RendererModel(this);
			right_leg_rear.setRotationPoint(-0.65F, -0.0042F, 0.775F);
			pelvis.addChild(right_leg_rear);
			right_leg_rear.cubeList.add(new ModelBox(right_leg_rear, 9, 13, -0.1F, -0.175F, -0.2F, 0, 0, 1, 0.0F, false));
			right_leg_front = new RendererModel(this);
			right_leg_front.setRotationPoint(0.0F, 0.0F, 1.0F);
			right_leg_rear.addChild(right_leg_front);
			right_leg_front.cubeList.add(new ModelBox(right_leg_front, 9, 13, -0.1F, -0.175F, 0.0F, 0, 0, 0, 0.0F, false));
			right_foot = new RendererModel(this);
			right_foot.setRotationPoint(0.0F, 0.0F, 0.7F);
			right_leg_front.addChild(right_foot);
			right_foot.cubeList.add(new ModelBox(right_foot, 9, 13, -0.2F, -0.175F, 0.0F, 0, 0, 0, 0.0F, false));
			right_foot.cubeList.add(new ModelBox(right_foot, 9, 13, -0.2F, 0.375F, 0.1F, 0, 0, 0, 0.0F, false));
			right_claws = new RendererModel(this);
			right_claws.setRotationPoint(0.0F, 0.55F, 0.2F);
			right_foot.addChild(right_claws);
			right_claws.cubeList.add(new ModelBox(right_claws, 11, 6, 0.1F, -0.025F, -0.1F, 0, 0, 0, 0.0F, false));
			right_claws.cubeList.add(new ModelBox(right_claws, 11, 6, -0.2F, -0.025F, -0.1F, 0, 0, 0, 0.0F, false));
			right_claws.cubeList.add(new ModelBox(right_claws, 11, 6, -0.05F, -0.025F, -0.1F, 0, 0, 0, 0.0F, false));
			tail = new RendererModel(this);
			tail.setRotationPoint(0.0F, -0.0542F, 1.075F);
			pelvis.addChild(tail);
			tail.cubeList.add(new ModelBox(tail, 11, 13, -0.2F, -0.225F, 0.0F, 0, 0, 0, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			root.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.right_wing_rear.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.neck_rear.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.neck_rear.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.left_wing_rear.rotateAngleZ = MathHelper.cos(f * 0.6662F) * f1;
			this.left_wing_elbow.rotateAngleZ = MathHelper.cos(f * 0.6662F) * f1;
			this.left_foot.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.right_wing_elbow.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.right_foot.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
