
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.Pose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.dinosaurworld.item.ShellItem;
import net.mcreator.dinosaurworld.block.CycadsaplingBlock;
import net.mcreator.dinosaurworld.DinosaurworldElements;

@DinosaurworldElements.ModElement.Tag
public class AnkylosaurusEntity extends DinosaurworldElements.ModElement {
	public static EntityType entity = null;
	public AnkylosaurusEntity(DinosaurworldElements instance) {
		super(instance, 10);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1.32f, 1f)).build("ankylosaurus")
						.setRegistryName("ankylosaurus");
		elements.entities.add(() -> entity);
		elements.items.add(
				() -> new SpawnEggItem(entity, -16724941, -6711040, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("ankylosaurus"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("dinosaurworld:cycadbiom")))
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
			return new MobRenderer(renderManager, new ModelAnkyloReworkV1(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("dinosaurworld:textures/ankylo.png");
				}
			};
		});
	}
	public static class CustomEntity extends AnimalEntity {
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
			this.goalSelector.addGoal(5, new PanicGoal(this, 1.2));
			this.targetSelector.addGoal(6, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(7, new FollowMobGoal(this, (float) 1, 10, 5));
			this.goalSelector.addGoal(8,
					new TemptGoal(this, 1, Ingredient.fromItems(new ItemStack(CycadsaplingBlock.block, (int) (1)).getItem()), false));
			this.goalSelector.addGoal(9, new EatGrassGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(ShellItem.block, (int) (1)));
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
			if (source.getImmediateSource() instanceof ArrowEntity)
				return false;
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public boolean processInteract(PlayerEntity entity, Hand hand) {
			super.processInteract(entity, hand);
			entity.startRiding(this);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			ItemStack itemstack = entity.getHeldItem(hand);
			return true;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15);
		}

		@Override
		public AgeableEntity createChild(AgeableEntity ageable) {
			return (CustomEntity) entity.create(this.world);
		}

		@Override
		public float getStandingEyeHeight(Pose pose, EntitySize size) {
			return this.isChild() ? size.height : 1.3F;
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			if (stack == null)
				return false;
			if (new ItemStack(CycadsaplingBlock.block, (int) (1)).getItem() == stack.getItem())
				return true;
			return false;
		}

		@Override
		public void travel(Vec3d dir) {
			Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
			if (this.isBeingRidden()) {
				this.rotationYaw = entity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = entity.rotationPitch * 0.5F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
				this.renderYawOffset = entity.rotationYaw;
				this.rotationYawHead = entity.rotationYaw;
				this.stepHeight = 1.0F;
				if (entity instanceof LivingEntity) {
					this.setAIMoveSpeed((float) this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					float forward = ((LivingEntity) entity).moveForward;
					float strafe = ((LivingEntity) entity).moveStrafing;
					super.travel(new Vec3d(strafe, 0, forward));
				}
				this.prevLimbSwingAmount = this.limbSwingAmount;
				double d1 = this.posX - this.prevPosX;
				double d0 = this.posZ - this.prevPosZ;
				float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
				if (f1 > 1.0F)
					f1 = 1.0F;
				this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
				this.limbSwing += this.limbSwingAmount;
				return;
			}
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.travel(dir);
		}
	}

	public static class ModelAnkyloReworkV1 extends EntityModel<Entity> {
		private final RendererModel root;
		private final RendererModel body;
		private final RendererModel body_r1;
		private final RendererModel body_r2;
		private final RendererModel body_r3;
		private final RendererModel body_r4;
		private final RendererModel body_r5;
		private final RendererModel body_r6;
		private final RendererModel left_foot_front;
		private final RendererModel left_foot_end_a;
		private final RendererModel right_foot_front;
		private final RendererModel right_foot_end_a;
		private final RendererModel left_foot_back;
		private final RendererModel left_foot_end_b;
		private final RendererModel right_foot_back;
		private final RendererModel right_foot_end_b;
		private final RendererModel neck_rear;
		private final RendererModel neck_6_r1;
		private final RendererModel neck_5_r1;
		private final RendererModel neck_mid;
		private final RendererModel neck_5_r2;
		private final RendererModel neck_4_r1;
		private final RendererModel head;
		private final RendererModel mouth_up_r1;
		private final RendererModel tail_r1;
		private final RendererModel tail_r2;
		private final RendererModel tail_r3;
		private final RendererModel head_full_r1;
		private final RendererModel head_full_r2;
		private final RendererModel mouth;
		private final RendererModel mouth_r1;
		private final RendererModel tail_r4;
		private final RendererModel tail_rear;
		private final RendererModel tail_r5;
		private final RendererModel tail_r6;
		private final RendererModel tail_mid;
		private final RendererModel tail_r7;
		private final RendererModel tail_r8;
		private final RendererModel tail_front;
		private final RendererModel tail_end;
		public ModelAnkyloReworkV1() {
			textureWidth = 112;
			textureHeight = 112;
			root = new RendererModel(this);
			root.setRotationPoint(0.0F, 22.0F, 0.0F);
			body = new RendererModel(this);
			body.setRotationPoint(0.0F, 0.0F, 0.0F);
			root.addChild(body);
			body.cubeList.add(new ModelBox(body, 0, 0, -7.0F, -8.0F, -10.0F, 14, 5, 19, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 24, -7.0F, -10.0F, -10.0F, 14, 2, 19, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 45, -7.0F, -12.0F, -6.0F, 14, 2, 12, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 47, 45, -7.0F, -11.25F, -10.0F, 14, 1, 4, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 47, 45, -7.0F, -11.25F, 6.0F, 14, 1, 3, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 9, 0, -5.0F, -3.0F, -8.0F, 10, 1, 15, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 62, 62, 5.0F, -3.0F, -8.0F, 1, 1, 15, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 45, 61, -6.0F, -3.0F, -8.0F, 1, 1, 15, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 79, 75, -5.0F, -3.0F, -9.0F, 10, 1, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 47, 41, -5.0F, -3.0F, 7.0F, 10, 1, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 93, 77, -4.0F, -3.0F, 8.0F, 8, 1, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 24, 7.0F, -8.0F, -5.0F, 1, 5, 8, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 0, -8.0F, -8.0F, -5.0F, 1, 5, 8, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 18, 78, 7.0F, -8.0F, -9.0F, 1, 1, 4, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 78, -8.0F, -8.0F, -9.0F, 1, 1, 4, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 77, 24, 7.0F, -7.0F, -10.0F, 1, 4, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 40, 75, -8.0F, -7.0F, -10.0F, 1, 4, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 37, 7.0F, -7.0F, 7.0F, 1, 4, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 13, -8.0F, -7.0F, 7.0F, 1, 4, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 18, 73, 7.0F, -8.0F, 3.0F, 1, 1, 4, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 73, -8.0F, -8.0F, 3.0F, 1, 1, 4, 0.0F, false));
			body_r1 = new RendererModel(this);
			body_r1.setRotationPoint(-9.0009F, -4.8433F, 0.0F);
			body.addChild(body_r1);
			setRotationAngle(body_r1, 0.0F, 0.0F, -1.8762F);
			body_r1.cubeList.add(new ModelBox(body_r1, 73, 45, -3.0F, -0.5F, -5.0F, 6, 1, 8, 0.0F, false));
			body_r2 = new RendererModel(this);
			body_r2.setRotationPoint(9.0009F, -4.8433F, 0.0F);
			body.addChild(body_r2);
			setRotationAngle(body_r2, 0.0F, 0.0F, 1.8762F);
			body_r2.cubeList.add(new ModelBox(body_r2, 77, 45, -3.0F, -0.5F, -5.0F, 6, 1, 8, 0.0F, false));
			body_r3 = new RendererModel(this);
			body_r3.setRotationPoint(6.234F, -11.3572F, 0.0F);
			body.addChild(body_r3);
			setRotationAngle(body_r3, 0.0F, 0.0F, 0.8727F);
			body_r3.cubeList.add(new ModelBox(body_r3, 0, 45, 0.0F, -1.0F, -6.0F, 6, 2, 12, 0.0F, false));
			body_r4 = new RendererModel(this);
			body_r4.setRotationPoint(-6.234F, -11.3572F, 0.0F);
			body.addChild(body_r4);
			setRotationAngle(body_r4, 0.0F, 0.0F, -0.8727F);
			body_r4.cubeList.add(new ModelBox(body_r4, 39, 46, -6.0F, -1.0F, -6.0F, 6, 2, 12, 0.0F, false));
			body_r5 = new RendererModel(this);
			body_r5.setRotationPoint(-6.4587F, -10.9375F, -8.0F);
			body.addChild(body_r5);
			setRotationAngle(body_r5, 0.0F, 0.0F, -1.0472F);
			body_r5.cubeList.add(new ModelBox(body_r5, 18, 45, -5.0F, -0.625F, -2.0F, 5, 1, 4, 0.0F, false));
			body_r5.cubeList.add(new ModelBox(body_r5, 66, 45, -5.0F, -0.625F, 14.0F, 5, 1, 3, 0.0F, false));
			body_r6 = new RendererModel(this);
			body_r6.setRotationPoint(6.4587F, -10.9375F, 8.0F);
			body.addChild(body_r6);
			setRotationAngle(body_r6, 0.0F, 0.0F, 1.0472F);
			body_r6.cubeList.add(new ModelBox(body_r6, 0, 45, 0.0F, -0.625F, -2.0F, 5, 1, 3, 0.0F, false));
			body_r6.cubeList.add(new ModelBox(body_r6, 90, 45, 0.0F, -0.625F, -18.0F, 5, 1, 4, 0.0F, false));
			left_foot_front = new RendererModel(this);
			left_foot_front.setRotationPoint(8.375F, -5.0F, -7.75F);
			body.addChild(left_foot_front);
			left_foot_front.cubeList.add(new ModelBox(left_foot_front, 40, 49, -0.625F, -1.0F, -1.25F, 2, 4, 3, 0.0F, false));
			left_foot_end_a = new RendererModel(this);
			left_foot_end_a.setRotationPoint(0.375F, 3.25F, 0.25F);
			left_foot_front.addChild(left_foot_end_a);
			left_foot_end_a.cubeList.add(new ModelBox(left_foot_end_a, 77, 0, -1.0F, -0.25F, -1.25F, 1, 4, 2, 0.0F, false));
			left_foot_end_a.cubeList.add(new ModelBox(left_foot_end_a, 57, 85, -1.0F, 3.75F, -2.25F, 1, 0, 1, 0.0F, true));
			right_foot_front = new RendererModel(this);
			right_foot_front.setRotationPoint(-8.375F, -5.0F, -6.75F);
			body.addChild(right_foot_front);
			right_foot_front.cubeList.add(new ModelBox(right_foot_front, 47, 0, -1.375F, -1.0F, -2.25F, 2, 4, 3, 0.0F, false));
			right_foot_end_a = new RendererModel(this);
			right_foot_end_a.setRotationPoint(-0.375F, 3.25F, -0.75F);
			right_foot_front.addChild(right_foot_end_a);
			right_foot_end_a.cubeList.add(new ModelBox(right_foot_end_a, 0, 24, -0.75F, -0.25F, -1.25F, 1, 4, 2, 0.0F, false));
			right_foot_end_a.cubeList.add(new ModelBox(right_foot_end_a, 57, 86, -0.75F, 3.75F, -2.25F, 1, 0, 1, 0.0F, true));
			left_foot_back = new RendererModel(this);
			left_foot_back.setRotationPoint(8.375F, -5.0F, 4.75F);
			body.addChild(left_foot_back);
			left_foot_back.cubeList.add(new ModelBox(left_foot_back, 47, 24, -0.625F, -1.0F, -1.75F, 2, 4, 3, 0.0F, false));
			left_foot_end_b = new RendererModel(this);
			left_foot_end_b.setRotationPoint(0.375F, 3.25F, -0.25F);
			left_foot_back.addChild(left_foot_end_b);
			left_foot_end_b.cubeList.add(new ModelBox(left_foot_end_b, 10, 24, -1.0F, -0.25F, -1.25F, 1, 4, 2, 0.0F, false));
			left_foot_end_b.cubeList.add(new ModelBox(left_foot_end_b, 57, 85, -1.0F, 3.75F, -2.25F, 1, 0, 1, 0.0F, true));
			right_foot_back = new RendererModel(this);
			right_foot_back.setRotationPoint(-8.375F, -5.0F, 4.75F);
			body.addChild(right_foot_back);
			right_foot_back.cubeList.add(new ModelBox(right_foot_back, 0, 45, -1.375F, -1.0F, -1.75F, 2, 4, 3, 0.0F, false));
			right_foot_end_b = new RendererModel(this);
			right_foot_end_b.setRotationPoint(-0.375F, 3.25F, -0.25F);
			right_foot_back.addChild(right_foot_end_b);
			right_foot_end_b.cubeList.add(new ModelBox(right_foot_end_b, 0, 0, -0.75F, -0.25F, -1.25F, 1, 4, 2, 0.0F, false));
			right_foot_end_b.cubeList.add(new ModelBox(right_foot_end_b, 57, 85, -0.75F, 3.75F, -2.25F, 1, 0, 1, 0.0F, true));
			neck_rear = new RendererModel(this);
			neck_rear.setRotationPoint(0.0F, -4.375F, -10.275F);
			body.addChild(neck_rear);
			neck_rear.cubeList.add(new ModelBox(neck_rear, 70, 101, -5.0F, -3.625F, -2.725F, 10, 4, 3, 0.0F, false));
			neck_rear.cubeList.add(new ModelBox(neck_rear, 79, 45, -5.0F, -5.375F, -2.725F, 10, 2, 3, 0.0F, false));
			neck_6_r1 = new RendererModel(this);
			neck_6_r1.setRotationPoint(-4.1808F, -4.8014F, -1.225F);
			neck_rear.addChild(neck_6_r1);
			setRotationAngle(neck_6_r1, 0.0F, 0.0F, -0.9599F);
			neck_6_r1.cubeList.add(new ModelBox(neck_6_r1, 62, 47, -4.0F, -1.0F, -1.5F, 4, 1, 3, 0.0F, false));
			neck_5_r1 = new RendererModel(this);
			neck_5_r1.setRotationPoint(4.1808F, -4.8014F, -1.225F);
			neck_rear.addChild(neck_5_r1);
			setRotationAngle(neck_5_r1, 0.0F, 0.0F, 0.9599F);
			neck_5_r1.cubeList.add(new ModelBox(neck_5_r1, 62, 47, 0.0F, -1.0F, -1.5F, 4, 1, 3, 0.0F, false));
			neck_mid = new RendererModel(this);
			neck_mid.setRotationPoint(0.0F, -1.125F, -2.825F);
			neck_rear.addChild(neck_mid);
			neck_mid.cubeList.add(new ModelBox(neck_mid, 84, 34, -4.0F, -2.5F, -2.9F, 8, 4, 3, 0.0F, false));
			neck_mid.cubeList.add(new ModelBox(neck_mid, 16, 45, -4.0F, -3.5F, -2.9F, 8, 1, 3, 0.0F, false));
			neck_5_r2 = new RendererModel(this);
			neck_5_r2.setRotationPoint(-3.6956F, -3.1033F, -1.4F);
			neck_mid.addChild(neck_5_r2);
			setRotationAngle(neck_5_r2, 0.0F, 0.0F, -0.6545F);
			neck_5_r2.cubeList.add(new ModelBox(neck_5_r2, 10, 47, -1.75F, -0.5F, -1.5F, 1, 1, 3, 0.0F, false));
			neck_4_r1 = new RendererModel(this);
			neck_4_r1.setRotationPoint(3.6956F, -3.1033F, -1.4F);
			neck_mid.addChild(neck_4_r1);
			setRotationAngle(neck_4_r1, 0.0F, 0.0F, 0.6545F);
			neck_4_r1.cubeList.add(new ModelBox(neck_4_r1, 68, 45, 0.0F, -0.5F, -1.5F, 1, 1, 3, 0.0F, false));
			head = new RendererModel(this);
			head.setRotationPoint(0.0031F, -0.374F, -2.1771F);
			neck_mid.addChild(head);
			head.cubeList.add(new ModelBox(head, 60, 88, -2.7531F, -2.126F, -4.7229F, 5, 4, 4, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 13, 37, 2.7469F, -2.126F, -4.9729F, 1, 1, 2, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 13, 13, -3.7531F, -2.126F, -4.9729F, 1, 1, 2, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 4, 6, 3.7469F, -2.126F, -4.2229F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 0, 6, -4.7531F, -2.126F, -4.2229F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 15, 3, 2.7469F, 0.874F, -4.2229F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 4, 0, -3.7531F, 0.874F, -4.2229F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 36, 45, -3.0031F, -2.626F, -4.7229F, 6, 0, 4, 0.0F, false));
			mouth_up_r1 = new RendererModel(this);
			mouth_up_r1.setRotationPoint(-0.0031F, 0.906F, -7.6793F);
			head.addChild(mouth_up_r1);
			setRotationAngle(mouth_up_r1, 0.4363F, 0.0F, 0.0F);
			mouth_up_r1.cubeList.add(new ModelBox(mouth_up_r1, 24, 64, -1.0F, -0.5F, -2.0F, 2, 1, 4, 0.0F, false));
			tail_r1 = new RendererModel(this);
			tail_r1.setRotationPoint(0.0469F, 0.6086F, -4.9909F);
			head.addChild(tail_r1);
			setRotationAngle(tail_r1, 0.6981F, 0.0F, 0.0F);
			tail_r1.cubeList.add(new ModelBox(tail_r1, 7, 0, -2.3F, -2.0F, -1.7F, 4, 0, 3, 0.0F, false));
			tail_r2 = new RendererModel(this);
			tail_r2.setRotationPoint(-0.0031F, 0.6916F, -4.9695F);
			head.addChild(tail_r2);
			setRotationAngle(tail_r2, 0.6981F, 0.0F, 0.0F);
			tail_r2.cubeList.add(new ModelBox(tail_r2, 87, 9, -2.75F, -2.0F, -2.0F, 5, 2, 4, 0.0F, false));
			tail_r3 = new RendererModel(this);
			tail_r3.setRotationPoint(-0.0031F, 2.3828F, -6.029F);
			head.addChild(tail_r3);
			setRotationAngle(tail_r3, 0.3054F, 0.0F, 0.0F);
			tail_r3.cubeList.add(new ModelBox(tail_r3, 62, 70, 1.0F, -2.0F, -2.0F, 1, 2, 4, 0.0F, false));
			tail_r3.cubeList.add(new ModelBox(tail_r3, 78, 102, -2.25F, -2.0F, -2.0F, 1, 2, 4, 0.0F, false));
			head_full_r1 = new RendererModel(this);
			head_full_r1.setRotationPoint(-3.3567F, -1.9189F, -2.7229F);
			head.addChild(head_full_r1);
			setRotationAngle(head_full_r1, 0.0F, 0.0F, -0.7854F);
			head_full_r1.cubeList.add(new ModelBox(head_full_r1, 0, 45, -0.15F, -0.25F, -2.0F, 0, 0, 4, 0.0F, false));
			head_full_r2 = new RendererModel(this);
			head_full_r2.setRotationPoint(3.3504F, -1.9189F, -2.7229F);
			head.addChild(head_full_r2);
			setRotationAngle(head_full_r2, 0.0F, 0.0F, 0.7854F);
			head_full_r2.cubeList.add(new ModelBox(head_full_r2, 0, 45, -0.75F, -0.25F, -2.0F, 0, 0, 4, 0.0F, false));
			mouth = new RendererModel(this);
			mouth.setRotationPoint(-0.0031F, 2.7094F, -7.2626F);
			head.addChild(mouth);
			mouth_r1 = new RendererModel(this);
			mouth_r1.setRotationPoint(0.0F, 0.0465F, -0.1167F);
			mouth.addChild(mouth_r1);
			setRotationAngle(mouth_r1, 0.0436F, 0.0F, 0.0F);
			mouth_r1.cubeList.add(new ModelBox(mouth_r1, 47, 7, -1.0F, -0.5912F, -1.7958F, 2, 1, 2, 0.0F, false));
			tail_r4 = new RendererModel(this);
			tail_r4.setRotationPoint(2.0F, -0.1554F, 2.0125F);
			mouth.addChild(tail_r4);
			setRotationAngle(tail_r4, 0.3054F, 0.0F, 0.0F);
			tail_r4.cubeList.add(new ModelBox(tail_r4, 66, 19, -3.0F, -1.0F, -2.0F, 2, 1, 3, 0.0F, false));
			tail_rear = new RendererModel(this);
			tail_rear.setRotationPoint(0.0F, -5.6667F, 9.3333F);
			body.addChild(tail_rear);
			tail_rear.cubeList.add(new ModelBox(tail_rear, 93, 20, -4.0F, 2.6667F, -0.3333F, 8, 1, 1, 0.0F, false));
			tail_rear.cubeList.add(new ModelBox(tail_rear, 19, 76, -3.0F, 2.6667F, 0.6667F, 6, 1, 9, 0.0F, false));
			tail_rear.cubeList.add(new ModelBox(tail_rear, 47, 0, -5.0F, -2.3333F, -0.3333F, 10, 5, 10, 0.0F, false));
			tail_rear.cubeList.add(new ModelBox(tail_rear, 47, 45, -5.0F, -4.3333F, -0.3333F, 10, 2, 10, 0.0F, false));
			tail_r5 = new RendererModel(this);
			tail_r5.setRotationPoint(5.4242F, -2.1379F, 4.6667F);
			tail_rear.addChild(tail_r5);
			setRotationAngle(tail_r5, 0.0F, 0.0F, 0.9163F);
			tail_r5.cubeList.add(new ModelBox(tail_r5, 72, 45, -2.0F, -1.0F, -5.0F, 4, 2, 10, 0.0F, false));
			tail_r6 = new RendererModel(this);
			tail_r6.setRotationPoint(-5.4242F, -2.1379F, 4.6667F);
			tail_rear.addChild(tail_r6);
			setRotationAngle(tail_r6, 0.0F, 0.0F, -0.9163F);
			tail_r6.cubeList.add(new ModelBox(tail_r6, 0, 45, -2.0F, -1.0F, -5.0F, 4, 2, 10, 0.0F, false));
			tail_mid = new RendererModel(this);
			tail_mid.setRotationPoint(0.0F, 0.9167F, 9.6667F);
			tail_rear.addChild(tail_mid);
			tail_mid.cubeList.add(new ModelBox(tail_mid, 0, 37, -2.0F, 1.75F, 0.0F, 4, 1, 5, 0.0F, false));
			tail_mid.cubeList.add(new ModelBox(tail_mid, 77, 24, -4.0F, -3.25F, 0.0F, 8, 5, 5, 0.0F, false));
			tail_mid.cubeList.add(new ModelBox(tail_mid, 40, 45, -4.0F, -4.75F, 0.0F, 8, 1, 5, 0.0F, false));
			tail_r7 = new RendererModel(this);
			tail_r7.setRotationPoint(-3.9048F, -3.5036F, 2.5F);
			tail_mid.addChild(tail_r7);
			setRotationAngle(tail_r7, 0.0F, 0.0F, -1.0036F);
			tail_r7.cubeList.add(new ModelBox(tail_r7, 33, 45, -2.5F, -0.75F, -2.5F, 3, 1, 5, 0.0F, false));
			tail_r8 = new RendererModel(this);
			tail_r8.setRotationPoint(3.9048F, -3.5036F, 2.5F);
			tail_mid.addChild(tail_r8);
			setRotationAngle(tail_r8, 0.0F, 0.0F, 1.0036F);
			tail_r8.cubeList.add(new ModelBox(tail_r8, 45, 45, -1.0F, -0.75F, -2.5F, 3, 1, 5, 0.0F, false));
			tail_front = new RendererModel(this);
			tail_front.setRotationPoint(0.0F, -0.5F, 5.25F);
			tail_mid.addChild(tail_front);
			tail_front.cubeList.add(new ModelBox(tail_front, 24, 59, -1.0F, 2.25F, -0.25F, 2, 1, 4, 0.0F, false));
			tail_front.cubeList.add(new ModelBox(tail_front, 79, 79, -3.0F, -2.75F, -0.25F, 6, 5, 5, 0.0F, false));
			tail_front.cubeList.add(new ModelBox(tail_front, 0, 45, -2.0F, -3.75F, -0.25F, 4, 1, 5, 0.0F, false));
			tail_end = new RendererModel(this);
			tail_end.setRotationPoint(0.0F, 0.2071F, 4.8571F);
			tail_front.addChild(tail_end);
			tail_end.cubeList.add(new ModelBox(tail_end, 0, 59, -1.0F, 1.0429F, -0.1071F, 2, 1, 4, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 40, 83, -0.5F, 0.0429F, 4.8929F, 1, 1, 9, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 78, 89, -2.0F, -1.9571F, -0.1071F, 4, 3, 5, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 0, 45, -1.0F, -2.9571F, -0.1071F, 2, 1, 4, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 57, 78, -1.0F, -0.9571F, 4.8929F, 2, 1, 9, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 0, 85, -6.0F, -1.9571F, 8.8929F, 5, 3, 6, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 79, 66, 1.0F, -1.9571F, 8.8929F, 5, 3, 6, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 93, 15, -6.0F, 1.0429F, 9.8929F, 5, 1, 4, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 32, 93, 1.0F, 1.0429F, 9.8929F, 5, 1, 4, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 0, 64, -7.0F, -1.9571F, 9.8929F, 1, 3, 4, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 48, 61, 6.0F, -1.9571F, 9.8929F, 1, 3, 4, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 91, 89, -6.0F, -2.9571F, 9.8929F, 5, 1, 4, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 3, 89, 1.0F, -2.9571F, 9.8929F, 5, 1, 4, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 48, 68, -1.0F, -1.6071F, 13.8929F, 2, 2, 3, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 13, 16, -0.5F, -1.1071F, 16.8929F, 1, 1, 1, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 54, 61, -2.0F, -1.1071F, 14.8929F, 1, 1, 2, 0.0F, false));
			tail_end.cubeList.add(new ModelBox(tail_end, 47, 31, 1.0F, -1.1071F, 14.8929F, 1, 1, 2, 0.0F, false));
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
			this.right_foot_front.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.tail_mid.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
			this.tail_rear.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.tail_front.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
			this.left_foot_back.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.left_foot_front.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.right_foot_back.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.tail_end.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}
