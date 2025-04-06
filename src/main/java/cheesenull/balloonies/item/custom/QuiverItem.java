package cheesenull.balloonies.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.*;

public class QuiverItem extends Item {

    public QuiverItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        if (!world.isClient) {

            List<ItemStack> arrowPool = new ArrayList<>();
            Map<ItemStack, Integer> stackToIndex = new HashMap<>();
            ItemStack quiver = player.getStackInHand(hand);

            for (int i = 0; i < player.getInventory().size(); i++) {

                ItemStack stack = player.getInventory().getStack(i);

                if (stack.getItem() instanceof ArrowItem && stack.getCount() > 0) {

                    for (int j = 0; j < stack.getCount(); j++) {
                        arrowPool.add(stack);
                    }

                    stackToIndex.put(stack, i);

                }

            }

            Collections.shuffle(arrowPool);
            int arrowsToShoot = Math.min(5, arrowPool.size());

            Vec3d lookVec = player.getRotationVec(1.0F).normalize();
            Vec3d origin = player.getPos().add(0, player.getStandingEyeHeight(), 0).add(lookVec.multiply(1.5));

            Vec3d right = new Vec3d(-lookVec.z, 0, lookVec.x).normalize();
            Vec3d up = new Vec3d(0, 1, 0);
            double offsetAmount = 0.2;
            float speed = 2.0F;

            Vec3d[] offsets = new Vec3d[] {

                    Vec3d.ZERO,
                    right.add(up).normalize().multiply(offsetAmount),
                    right.negate().add(up).normalize().multiply(offsetAmount),
                    right.add(up.negate()).normalize().multiply(offsetAmount),
                    right.negate().add(up.negate()).normalize().multiply(offsetAmount)

            };

            for (int i = 0; i < arrowsToShoot; i++) {

                ItemStack selectedArrowStack = arrowPool.get(i);

                ArrowItem arrowItem = (ArrowItem) selectedArrowStack.getItem();
                PersistentProjectileEntity arrow = arrowItem.createArrow(world, selectedArrowStack, player, null);

                Vec3d offset = offsets[i];
                Vec3d dir = lookVec.add(offset).normalize();

                arrow.setPos(origin.x, origin.y, origin.z);
                arrow.setVelocity(dir.x, dir.y, dir.z, speed, 0.5F);
                arrow.setOwner(player);
                world.spawnEntity(arrow);

                selectedArrowStack.decrementUnlessCreative(1, player);

            }

            if (!player.isCreative()) {

                EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                quiver.damage(1, player, slot);

            }
            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS);
            player.getItemCooldownManager().set(this, 20);

        }

        return TypedActionResult.success(player.getStackInHand(hand), world.isClient());
    }

}
