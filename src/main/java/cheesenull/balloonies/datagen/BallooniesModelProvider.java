package cheesenull.balloonies.datagen;

import cheesenull.balloonies.block.BallooniesBlocks;
import cheesenull.balloonies.item.BallooniesItems;
import com.ibm.icu.text.Normalizer2;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class BallooniesModelProvider extends FabricModelProvider {

    public BallooniesModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        blockStateModelGenerator.registerFlowerPotPlant(BallooniesBlocks.BLUE_ROSE,
                BallooniesBlocks.POTTED_BLUE_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(BallooniesItems.HARPOON, Models.HANDHELD);
        itemModelGenerator.register(BallooniesItems.QUIVER, Models.HANDHELD);
        itemModelGenerator.register(BallooniesItems.BAGUETTE, Models.HANDHELD);

    }

}
