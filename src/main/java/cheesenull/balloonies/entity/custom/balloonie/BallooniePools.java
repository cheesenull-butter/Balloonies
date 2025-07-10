package cheesenull.balloonies.entity.custom.balloonie;

import cheesenull.balloonies.block.BallooniesBlocks;
import cheesenull.balloonies.entity.BallooniesEntities;
import cheesenull.balloonies.entity.custom.BallooningEntity;
import cheesenull.balloonies.item.BallooniesItems;
import cheesenull.balloonies.particle.BallooniesParticles;
import cheesenull.balloonies.sound.BallooniesSounds;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Random;

public class BallooniePools {

    Random ran = new Random();

    public BallooniePools() {}

    public void ballooniePool(World world, BlockPos pos, Entity entity) {

        int pool = ran.nextInt(100);

        world.playSound(null, pos, BallooniesSounds.ENTITY_BALLOONIE_POP, SoundCategory.NEUTRAL);

        if (pool < 25) {

            BalloonieItemPools balItemPools = new BalloonieItemPools();
            Item ranItem = balItemPools.itemPools[ran.nextInt(balItemPools.itemPools.length)];
            ItemStack itemStack = new ItemStack(ranItem);

            if (Arrays.asList(balItemPools.singlePools).contains(ranItem)) {
                entity.dropStack(new ItemStack(itemStack.getItem()));
            } else if (Arrays.asList(balItemPools.sextuplePools).contains(ranItem)) {
                for (int i = ran.nextInt(6); i < 6; i++) {
                    entity.dropStack(new ItemStack(itemStack.getItem()));
                }
            } else {
                for (int i = ran.nextInt(3); i < 3; i++) {
                    entity.dropStack(new ItemStack(itemStack.getItem()));
                }
            }

        } else if (pool < 40) {

            if (ran.nextBoolean()) {

                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        for (int z = 0; z < 2; z++) {

                            FallingBlockEntity blockEntity =
                                    FallingBlockEntity.spawnFromBlock(world, pos.add(x, y, z),
                                            Blocks.BOOKSHELF.getDefaultState());

                            world.spawnEntity(blockEntity);
                            entity.dropStack(new ItemStack(Items.BOOK));

                        }
                    }
                }

            } else {

                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        for (int z = 0; z < 2; z++) {

                            FallingBlockEntity blockEntity =
                                    FallingBlockEntity.spawnFromBlock(world, pos.add(x, y, z),
                                            BallooniesBlocks.TOFU_BLOCK.getDefaultState());

                            world.spawnEntity(blockEntity);
                            entity.dropStack(new ItemStack(BallooniesItems.TOFU));

                        }
                    }
                }

            }

        } else if (pool < 60) {

            for (int i = 0; i < 5; i++) {

                CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, world);
                creeper.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
                world.spawnEntity(creeper);

                double velocityX = (world.random.nextDouble() - 0.5) * 2;
                double velocityY = world.random.nextDouble() * 0.5 + 0.5;
                double velocityZ = (world.random.nextDouble() - 0.5) * 2;
                creeper.setVelocity(velocityX, velocityY, velocityZ);

            }

        } else if (pool < 65) {

            if (world instanceof ServerWorld serverWorld) {
                serverWorld.setWeather(0, 6000, true, true);
            }

            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            world.spawnEntity(lightning);

        } else if (pool < 80) {

            FireworkRocketEntity fireworkRocket =
                    new FireworkRocketEntity(world, pos.getX(), pos.getY(), pos.getZ(), ItemStack.EMPTY);
            world.spawnEntity(fireworkRocket);

        } else if (pool < 85) {

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

        } else {

            BalloonieCropPools cropPools = new BalloonieCropPools();
            Block ranBlock = cropPools.cropPools[ran.nextInt(cropPools.cropPools.length)];

            FallingBlockEntity farmlandEntity =
                    FallingBlockEntity.spawnFromBlock(world, pos,
                            Blocks.FARMLAND.getDefaultState().with(FarmlandBlock.MOISTURE, 7));
            FallingBlockEntity cropEntity =
                    FallingBlockEntity.spawnFromBlock(world, pos.up(), ranBlock.getDefaultState());

            world.spawnEntity(farmlandEntity);
            world.spawnEntity(cropEntity);

        }

    }

    public void whiteBallooniePool(World world, BlockPos pos, Entity entity) {

        int whitePool = ran.nextInt(10);

        if (whitePool < 8) {

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

            ServerWorld serverWorld = (ServerWorld) world;

            for (ServerPlayerEntity player : serverWorld.getPlayers()) {

                BlockPos playerPos = player.getBlockPos();

                double x = playerPos.getX() - pos.getX();
                double y = playerPos.getY() - pos.getY();
                double z = playerPos.getZ() - pos.getZ();

                double distance = Math.sqrt(x*x + y*y + z*z);

                if (distance <= 32) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200, 0));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 5));
                }

            }

            world.playSound(null, pos, BallooniesSounds.ENTITY_BALLOONIE_LOBOTOMY, SoundCategory.HOSTILE);

        } else {

            int insidePool = ran.nextInt(2);

            switch (insidePool) {

                case 0:
                    entity.dropStack(new ItemStack(BallooniesBlocks.BLUE_ROSE.asItem()));
                case 1:
                    entity.dropStack(new ItemStack(BallooniesItems.QUIVER));
            }

        }

    }

}
