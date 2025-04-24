 package cheesenull.balloonies.item.custom;

import cheesenull.balloonies.entity.custom.HarpoonEntity;
import cheesenull.balloonies.sound.BallooniesSounds;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ProjectileItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.List;

public class HarpoonItem extends Item implements ProjectileItem {

    public HarpoonItem(Item.Settings settings) {
        super(settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 4.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.9000000953674316, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build();
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2);
    }

    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {

        if (user instanceof PlayerEntity playerEntity) {

            int var6 = this.getMaxUseTime(stack, user) - remainingUseTicks;

            if (var6 >= 10) {

                if (!isAboutToBreak(stack)) {

                    if (!world.isClient) {

                        stack.damage(1, playerEntity, LivingEntity.getSlotForHand(user.getActiveHand()));

                        HarpoonEntity harpoon = new HarpoonEntity(world, playerEntity, stack);
                        harpoon.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 2.5F, 1.0F);

                        world.spawnEntity(harpoon);
                        world.playSoundFromEntity((PlayerEntity)null, harpoon, BallooniesSounds.ITEM_HARPOON_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);

                        playerEntity.getInventory().removeOne(stack);

                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));

                }

            }

        }

    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);

        if (isAboutToBreak(stack)) {
            return TypedActionResult.fail(stack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);

        }

    }

    private static boolean isAboutToBreak(ItemStack stack) {
        return stack.getDamage() >= stack.getMaxDamage() - 1;
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    public int getEnchantability() {
        return 1;
    }

    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        HarpoonEntity harpoonEntity = new HarpoonEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1));
        harpoonEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        return harpoonEntity;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.COPPER_INGOT) || super.canRepair(stack, ingredient);
    }

}
