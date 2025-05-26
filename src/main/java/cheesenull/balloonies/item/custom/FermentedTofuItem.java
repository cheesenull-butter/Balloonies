package cheesenull.balloonies.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FermentedTofuItem extends Item {

    public FermentedTofuItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        if (!world.isClient) {
            user.removeStatusEffect(StatusEffects.POISON);
        }

        return super.finishUsing(stack, world, user);
    }
}
