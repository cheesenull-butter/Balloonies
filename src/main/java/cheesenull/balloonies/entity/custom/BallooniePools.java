package cheesenull.balloonies.entity.custom;

import cheesenull.balloonies.block.BallooniesBlocks;
import cheesenull.balloonies.entity.BallooniesEntities;
import cheesenull.balloonies.sound.BallooniesSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BallooniePools {

    Random ran = new Random();

    public BallooniePools() {
    }

    public void ballooniePool(World world, BlockPos pos) {

        int pool = ran.nextInt(5, 6);

        if (!world.isClient()) {

            switch (pool) {

                case 0 :

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

                    break;

                case 1:

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



                    break;

                case 2:

                    if (world instanceof ServerWorld serverWorld) {
                        serverWorld.setWeather(0, 6000, true, true);
                    }

                    LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                    world.spawnEntity(lightning);

                    break;

                case 3:

                    ItemStack diamond = new ItemStack(Items.DIAMOND);
                    world.spawnEntity(new ItemEntity(world,
                            pos.getX(), pos.getY(), pos.getZ(), diamond));

                    break;

                case 4:

                    FireworkRocketEntity fireworkRocket =
                            new FireworkRocketEntity(world, pos.getX(), pos.getY(), pos.getZ(), ItemStack.EMPTY);
                    world.spawnEntity(fireworkRocket);

                    break;

                case 5:

                    Item[] possibleItems = {
                            Items.EMERALD,
                            Items.AMETHYST_SHARD,
                            Items.IRON_INGOT,
                            Items.IRON_NUGGET,
                            Items.GOLD_INGOT,
                            Items.GOLD_NUGGET,
                            Items.COPPER_INGOT,
                            Items.BREAD,
                            Items.CARROT,
                            Items.POTATO,
                            Items.BAKED_POTATO,
                            Items.LEATHER,
                            Items.STICK,
                            Items.PAPER,
                            Items.SLIME_BALL,
                            Items.LEAD,
                            Items.SADDLE,
                            Items.NAME_TAG
                    };

                    Random random = new Random();
                    Item ranItem = possibleItems[random.nextInt(possibleItems.length)];

                    ItemStack itemStack = new ItemStack(ranItem);

                    for (int i = ran.nextInt(0, 3); i < 3; i++) {
                        world.spawnEntity(new ItemEntity(world,
                                pos.getX(), pos.getY(), pos.getZ(), itemStack));
                    }

                    break;

            }
        }

    }

    public void whiteBallooniePool(World world, BlockPos pos) {

        int pool = ran.nextInt(0, 10);

        if (!world.isClient()) {

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

                world.playSound(null,pos, BallooniesSounds.LOBOTOMY, SoundCategory.HOSTILE);

            } else {

                ItemStack itemStack = new ItemStack(BallooniesBlocks.BLUE_ROSE.asItem());
                world.spawnEntity(new ItemEntity(world,
                        pos.getX(), pos.getY(), pos.getZ(), itemStack));

            }

        }

    }

}
