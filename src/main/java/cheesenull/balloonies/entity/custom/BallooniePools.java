package cheesenull.balloonies.entity.custom;

import cheesenull.balloonies.block.BallooniesBlocks;
import cheesenull.balloonies.entity.BallooniesEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class BallooniePools {

    Random ran = new Random();

    public BallooniePools() {
    }

    public void ballooniePool(World world, Vec3d pos, BlockPos blockPos) {

        int pool = ran.nextInt(0, 3);

        switch (pool) {

            case 0 :

                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        for (int z = 0; z < 2; z++) {

                            FallingBlockEntity blockEntity =
                                    FallingBlockEntity.spawnFromBlock(world, blockPos.add(x, y, z),
                                            Blocks.BOOKSHELF.getDefaultState());
                            ItemStack itemStack = new ItemStack(Items.BOOK);

                            world.spawnEntity(blockEntity);
                            world.spawnEntity(new ItemEntity(world,
                                    pos.getX(), pos.getY(), pos.getZ(), itemStack));

                        }
                    }
                }

            case 1:

                if (!world.isClient()) {

                    for (int i = 0; i < 5; i++) {

                        CreeperEntity creeper =
                                new CreeperEntity(EntityType.CREEPER, world);
                        creeper.refreshPositionAndAngles(
                                pos.getX(), pos.getY(), pos.getZ(),
                                0, 0);
                        world.spawnEntity(creeper);

                        double velocityX = (world.random.nextDouble() - 0.5) * 2;
                        double velocityY = world.random.nextDouble() * 0.5 + 0.5;
                        double velocityZ = (world.random.nextDouble() - 0.5) * 2;
                        creeper.setVelocity(velocityX, velocityY, velocityZ);

                    }

                }

            case 2:

                if (!world.isClient()) {

                    if (world instanceof ServerWorld serverWorld) {
                        serverWorld.setWeather(0, 6000, true, true);
                    }

                    LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                    world.spawnEntity(lightning);

                }

        }

    }

    public void whiteBallooniePool(World world, Vec3d pos) {

        int pool = ran.nextInt(0, 10);

        if (pool < 8) {

            for (int i = 0; i < 5; i++) {

                if (!world.isClient()) {

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

            }


        } else {

            if (!world.isClient()) {

                ItemStack itemStack = new ItemStack(BallooniesBlocks.BLUE_ROSE.asItem());
                world.spawnEntity(new ItemEntity(world,
                        pos.getX(), pos.getY(), pos.getZ(), itemStack));

            }

        }

    }

}
