package cheesenull.balloonies.entity.custom.balloonie;

import cheesenull.balloonies.block.BallooniesBlocks;
import cheesenull.balloonies.entity.BallooniesEntities;
import cheesenull.balloonies.entity.custom.BallooningEntity;
import cheesenull.balloonies.item.BallooniesItems;
import cheesenull.balloonies.sound.BallooniesSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Random;

public class BallooniePools {

    Random ran = new Random();

    public BallooniePools() {
    }

    public void ballooniePool(World world, BlockPos pos) {

        int pool = ran.nextInt(1, 101);

        world.playSound(null, pos, BallooniesSounds.POP, SoundCategory.NEUTRAL);

        if (pool <= 25) {

            BalloonieItemPools balItemPools = new BalloonieItemPools();
            Item ranItem = balItemPools.itemPools[ran.nextInt(balItemPools.itemPools.length)];
            ItemStack itemStack = new ItemStack(ranItem);

            if (Arrays.asList(balItemPools.singlePools).contains(ranItem)) {

                world.spawnEntity(new ItemEntity(world,
                        pos.getX(), pos.getY(), pos.getZ(), itemStack));

            } else if (Arrays.asList(balItemPools.sextuplePools).contains(ranItem)) {

                for (int i = ran.nextInt(0, 6); i < 6; i++) {
                    world.spawnEntity(new ItemEntity(world,
                            pos.getX(), pos.getY(), pos.getZ(), itemStack));
                }

            } else {

                for (int i = ran.nextInt(0, 3); i < 3; i++) {
                    world.spawnEntity(new ItemEntity(world,
                            pos.getX(), pos.getY(), pos.getZ(), itemStack));
                }

            }

        } else if (pool > 25 && pool <= 40) {

            for (int x = 0; x < 2; x++) {
                for (int y = 0; y < 2; y++) {
                    for (int z = 0; z < 2; z++) {

                        FallingBlockEntity blockEntity =
                                FallingBlockEntity.spawnFromBlock(world, pos.add(x, y, z),
                                        Blocks.BOOKSHELF.getDefaultState());
                        ItemStack itemStack = new ItemStack(Items.BOOK);

                        world.spawnEntity(blockEntity);
                        world.spawnEntity(new ItemEntity(world,
                                pos.getX(), pos.getY(), pos.getZ(), itemStack));

                    }
                }
            }

        } else if (pool > 40 && pool <= 60) {

            for (int i = 0; i < 5; i++) {

                CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, world);
                creeper.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
                world.spawnEntity(creeper);

                double velocityX = (world.random.nextDouble() - 0.5) * 2;
                double velocityY = world.random.nextDouble() * 0.5 + 0.5;
                double velocityZ = (world.random.nextDouble() - 0.5) * 2;
                creeper.setVelocity(velocityX, velocityY, velocityZ);

            }

        } else if (pool > 60 && pool <= 65) {

            if (world instanceof ServerWorld serverWorld) {
                serverWorld.setWeather(0, 6000, true, true);
            }

            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            world.spawnEntity(lightning);

        } else if (pool > 65 && pool <= 80) {

            FireworkRocketEntity fireworkRocket =
                    new FireworkRocketEntity(world, pos.getX(), pos.getY(), pos.getZ(), ItemStack.EMPTY);
            world.spawnEntity(fireworkRocket);

        } else if (pool > 80 && pool <= 85) {

            for (int i = 0; i < 3; i++) {

                BatEntity bat = new BatEntity(EntityType.BAT, world);
                SkeletonEntity skeleton = new SkeletonEntity(EntityType.SKELETON, world);

                bat.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
                skeleton.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);

                world.spawnEntity(bat);
                world.spawnEntity(skeleton);

                skeleton.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.BOW));
                skeleton.startRiding(bat);

            }

        }

    }

    public void whiteBallooniePool(World world, BlockPos pos) {

        int pool = ran.nextInt(0, 10);

        if (pool < 8) {

            for (int i = 0; i < 5; i++) {

                BallooningEntity ballooning =
                        new BallooningEntity(BallooniesEntities.BALLOONING, world);
                ballooning.refreshPositionAndAngles(
                        pos.getX(), pos.getY(), pos.getZ(),
                        0, 0);
                world.spawnEntity(ballooning);

                double velocityX = (world.random.nextDouble() - 0.5) * 2;
                double velocityY = world.random.nextDouble() * 0.5 + 0.5;
                double velocityZ = (world.random.nextDouble() - 0.5) * 2;
                ballooning.setVelocity(velocityX, velocityY, velocityZ);

            }

            world.playSound(null, pos, BallooniesSounds.LOBOTOMY, SoundCategory.HOSTILE);

        } else {

            int insidePool = ran.nextInt(0, 2);

            switch (insidePool) {

                case 0:

                    ItemStack blueRose = new ItemStack(BallooniesBlocks.BLUE_ROSE.asItem());
                    world.spawnEntity(new ItemEntity(world,
                            pos.getX(), pos.getY(), pos.getZ(), blueRose));

                case 1:

                    ItemStack quiver = new ItemStack(BallooniesItems.QUIVER);
                    world.spawnEntity(new ItemEntity(world,
                            pos.getX(), pos.getY(), pos.getZ(), quiver));

            }

        }

    }



}
