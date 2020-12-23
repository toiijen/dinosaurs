
package net.mcreator.dinosaurworld.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.mcreator.dinosaurworld.procedures.ShellshieldToolInInventoryTickProcedure;
import net.mcreator.dinosaurworld.procedures.ShellshieldToolInHandTickProcedure;
import net.mcreator.dinosaurworld.procedures.ShellshieldOnPlayerStoppedUsingProcedure;
import net.mcreator.dinosaurworld.itemgroup.LeDinoModItemGroup;
import net.mcreator.dinosaurworld.DinosaurworldElements;

import com.google.common.collect.Multimap;

@DinosaurworldElements.ModElement.Tag
public class ShellshieldItem extends DinosaurworldElements.ModElement {
	@ObjectHolder("dinosaurworld:shellshield")
	public static final Item block = null;
	public ShellshieldItem(DinosaurworldElements instance) {
		super(instance, 26);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemToolCustom() {
			@Override
			public void onPlayerStoppedUsing(ItemStack itemstack, World world, LivingEntity entity, int time) {
				super.onPlayerStoppedUsing(itemstack, world, entity, time);
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					ShellshieldOnPlayerStoppedUsingProcedure.executeProcedure($_dependencies);
				}
			}

			@Override
			public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean selected) {
				super.inventoryTick(itemstack, world, entity, slot, selected);
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				if (selected) {
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					ShellshieldToolInHandTickProcedure.executeProcedure($_dependencies);
				}
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					ShellshieldToolInInventoryTickProcedure.executeProcedure($_dependencies);
				}
			}
		}.setRegistryName("shellshield"));
	}
	private static class ItemToolCustom extends Item {
		protected ItemToolCustom() {
			super(new Item.Properties().group(LeDinoModItemGroup.tab).maxDamage(1000));
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
			return 30;
		}

		@Override
		public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
			Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
			if (equipmentSlot == EquipmentSlotType.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
						new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", -2f, AttributeModifier.Operation.ADDITION));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
						new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", -3, AttributeModifier.Operation.ADDITION));
			}
			return multimap;
		}
	}
}
