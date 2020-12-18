
package net.mcreator.dinosaurworld.item;

@DinosaurworldElements.ModElement.Tag
public class AroowstaffItem extends DinosaurworldElements.ModElement {

	@ObjectHolder("dinosaurworld:aroowstaff")
	public static final Item block = null;

	public AroowstaffItem(DinosaurworldElements instance) {
		super(instance, 44);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemToolCustom() {

			@Override
			public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
				ActionResult<ItemStack> retval = super.onItemRightClick(world, entity, hand);
				ItemStack itemstack = retval.getResult();
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("world", world);

					AroowstaffRightClickedInAirProcedure.executeProcedure($_dependencies);
				}
				return retval;
			}

		}.setRegistryName("aroowstaff"));
	}

	private static class ItemToolCustom extends Item {

		protected ItemToolCustom() {
			super(new Item.Properties().group(ItemGroup.TOOLS).maxDamage(100));
		}

		@Override
		public float getDestroySpeed(ItemStack itemstack, BlockState blockstate) {
			return 0;
		}

		@Override
		public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
			stack.damageItem(1, entityLiving, i -> i.sendBreakAnimation(EquipmentSlotType.MAINHAND));
			return true;
		}

		@Override
		public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
			stack.damageItem(2, attacker, i -> i.sendBreakAnimation(EquipmentSlotType.MAINHAND));
			return true;
		}

		@Override
		public int getItemEnchantability() {
			return 2;
		}

		@Override
		public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
			Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
			if (equipmentSlot == EquipmentSlotType.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
						new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 2f, AttributeModifier.Operation.ADDITION));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
						new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", -3, AttributeModifier.Operation.ADDITION));
			}

			return multimap;
		}

	}

}
