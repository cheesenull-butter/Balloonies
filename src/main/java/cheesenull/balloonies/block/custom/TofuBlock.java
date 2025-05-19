package cheesenull.balloonies.block.custom;

import cheesenull.balloonies.item.BallooniesItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TofuBlock extends Block {

    public TofuBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {

        if (entity.bypassesLandingEffects()) {
            super.onLandedUpon(world, state, pos, entity, fallDistance);
        } else {

            entity.handleFallDamage(fallDistance, 0.0F, world.getDamageSources().fall());

            if (entity instanceof PlayerEntity player) {

                if (player.isCreative()) {
                    world.breakBlock(pos, false);
                } else {
                    world.breakBlock(pos, true);
                }

            }

        }

    }
}
