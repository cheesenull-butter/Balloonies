package cheesenull.balloonies.datagen;

import cheesenull.balloonies.block.BallooniesBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BallooniesLootTableProvider extends FabricBlockLootTableProvider {

    public BallooniesLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        addDrop(BallooniesBlocks.BLUE_ROSE);
        addPottedPlantDrops(BallooniesBlocks.POTTED_BLUE_ROSE);
        addDrop(BallooniesBlocks.CLOVER);
        addPottedPlantDrops(BallooniesBlocks.POTTED_CLOVER);

    }

}
