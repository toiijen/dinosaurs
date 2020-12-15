
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
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(3f, 1.8f)).build("ankylosaurus")
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
			return new MobRenderer(renderManager, new Modelankylosaurus(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("dinosaurworld:textures/ankylosaurus.png");
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
					float forward = 0;
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

	public static class Modelankylosaurus extends EntityModel {
		private final RendererModel tail1;
		private final RendererModel tail2;
		private final RendererModel tail3;
		private final RendererModel body;
		private final RendererModel rightlegs;
		private final RendererModel leftlegs;
		private final RendererModel head;
		public Modelankylosaurus() {
			textureWidth = 128;
			textureHeight = 128;
			tail1 = new RendererModel(this);
			tail1.setRotationPoint(0.0F, 15.75F, 29.0F);
			tail1.cubeList.add(new ModelBox(tail1, 0, 36, -1.5F, -1.75F, 0.0F, 3, 3, 6, 0.0F, false));
			tail2 = new RendererModel(this);
			tail2.setRotationPoint(0.0F, -0.25F, 6.0F);
			tail1.addChild(tail2);
			tail2.cubeList.add(new ModelBox(tail2, 12, 22, -1.5F, -1.5F, 0.0F, 3, 3, 6, 0.0F, false));
			tail3 = new RendererModel(this);
			tail3.setRotationPoint(0.0F, 0.0F, 6.0F);
			tail2.addChild(tail3);
			tail3.cubeList.add(new ModelBox(tail3, 0, 19, -1.5F, -1.5F, 0.0F, 3, 3, 6, 0.0F, false));
			tail3.cubeList.add(new ModelBox(tail3, 0, 0, -4.0F, -2.0F, 6.0F, 8, 4, 5, 0.0F, false));
			body = new RendererModel(this);
			body.setRotationPoint(0.0F, 10.0F, 2.0F);
			body.cubeList.add(new ModelBox(body, 0, 0, -13.0F, 3.0F, -4.0F, 26, 5, 31, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 36, -11.0F, 0.0F, -2.0F, 22, 3, 27, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 66, -9.0F, -1.0F, 0.0F, 18, 1, 23, 0.0F, false));
			rightlegs = new RendererModel(this);
			rightlegs.setRotationPoint(-9.0F, 24.0F, 25.0F);
			rightlegs.cubeList.add(new ModelBox(rightlegs, 0, 66, -2.0F, -6.0F, -26.0F, 3, 6, 3, 0.0F, false));
			rightlegs.cubeList.add(new ModelBox(rightlegs, 13, 48, -2.0F, -6.0F, 0.0F, 3, 6, 3, 0.0F, false));
			leftlegs = new RendererModel(this);
			leftlegs.setRotationPoint(9.0F, 24.0F, 25.0F);
			leftlegs.cubeList.add(new ModelBox(leftlegs, 59, 66, -1.0F, -6.0F, -26.0F, 3, 6, 3, 0.0F, false));
			leftlegs.cubeList.add(new ModelBox(leftlegs, 0, 51, -1.0F, -6.0F, 0.0F, 3, 6, 3, 0.0F, false));
			head = new RendererModel(this);
			head.setRotationPoint(-0.5F, 15.5F, -2.15F);
			head.cubeList.add(new ModelBox(head, 0, 45, -2.5F, -1.5F, -2.85F, 5, 3, 3, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 0, 9, -3.0F, -2.0F, -8.85F, 6, 4, 6, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			tail1.render(f5);
			body.render(f5);
			rightlegs.render(f5);
			leftlegs.render(f5);
			head.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
		}
	}
}
