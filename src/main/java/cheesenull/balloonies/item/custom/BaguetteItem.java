package cheesenull.balloonies.item.custom;

import cheesenull.balloonies.item.BallooniesItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BaguetteItem extends SwordItem {

    Random ran = new Random();

    public BaguetteItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (ran.nextInt(10) == 0) {

            Hand hand = attacker.getMainHandStack() == stack ? Hand.MAIN_HAND : Hand.OFF_HAND;

            EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

            attacker.dropItem(Items.BREAD, 2);

            stack.damage(59, attacker, slot);

        }

        return super.postHit(stack, target, attacker);

    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {

        if (ran.nextInt(10) == 0) {

            Hand hand = miner.getMainHandStack() == stack ? Hand.MAIN_HAND : Hand.OFF_HAND;

            EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

            miner.dropItem(Items.BREAD, 2);

            stack.damage(59, miner, slot);

        }

        return super.postMine(stack, world, state, pos, miner);

    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.WHEAT) || super.canRepair(stack, ingredient);
    }

}
