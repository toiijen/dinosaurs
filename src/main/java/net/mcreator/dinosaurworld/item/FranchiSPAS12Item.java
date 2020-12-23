
package net.mcreator.dinosaurworld.item;

@DinosaurworldElements.ModElement.Tag
public class FranchiSPAS12Item extends DinosaurworldElements.ModElement {

	@ObjectHolder("dinosaurworld:franchispas12")
	public static final Item block = null;

	@ObjectHolder("dinosaurworld:entitybulletfranchispas12")
	public static final EntityType arrow = null;

	public FranchiSPAS12Item(DinosaurworldElements instance) {
		super(instance, 61);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemRanged());
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("entitybulletfranchispas12").setRegistryName("entitybulletfranchispas12"));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void init(FMLCommonSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ArrowCustomEntity.class, renderManager -> {
			return new SpriteRenderer(renderManager, Minecraft.getInstance().getItemRenderer());
		});
	}

	public static class ItemRanged extends Item {

		public ItemRanged() {
			super(new Item.Properties().group(LeDinoModItemGroup.tab).maxDamage(100));

			setRegistryName("franchispas12");
		}

		@Override
		public UseAction getUseAction(ItemStack stack) {
			return UseAction.BOW;
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			entity.setActiveHand(hand);
			return new ActionResult(ActionResultType.SUCCESS, entity.getHeldItem(hand));
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 72000;
		}

		@Override
		public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot) {
			Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot);
			if (slot == EquipmentSlotType.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
						new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "ranged_item_damage", (double) 2, AttributeModifier.Operation.ADDITION));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
						new AttributeModifier(ATTACK_SPEED_MODIFIER, "ranged_item_attack_speed", -2.4, AttributeModifier.Operation.ADDITION));
			}
			return multimap;
		}

		@Override
		public void onUsingTick(ItemStack itemstack, LivingEntity entityLiving, int count) {
			World world = entityLiving.world;
			if (!world.isRemote && entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;
				float power = 10f;
				ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, world);
				entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 2, 0);
				entityarrow.setSilent(true);
				entityarrow.setIsCritical(false);
				entityarrow.setDamage(7);
				entityarrow.setKnockbackStrength(10);

				itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));

				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")),
						SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));

				entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;

				world.addEntity(entityarrow);

				entity.stopActiveHand();
			}
		}

	}

	@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
	public static class ArrowCustomEntity extends AbstractArrowEntity implements IRendersAsItem {

		public ArrowCustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			super(arrow, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, World world) {
			super(type, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, double x, double y, double z, World world) {
			super(type, x, y, z, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, LivingEntity entity, World world) {
			super(type, entity, world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack getItem() {
			return new ItemStack(Blocks.STONE, (int) (1));
		}

		@Override
		protected ItemStack getArrowStack() {
			return null;
		}

		@Override
		protected void arrowHit(LivingEntity entity) {
			super.arrowHit(entity);
			entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1);
		}

		@Override
		public void tick() {
			super.tick();
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			World world = this.world;
			Entity entity = this.getShooter();
			if (this.inGround) {
				this.remove();
			}
		}

	}

}
