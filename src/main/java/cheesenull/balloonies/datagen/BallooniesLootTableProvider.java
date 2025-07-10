package cheesenull.balloonies.datagen;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.block.BallooniesBlocks;
import cheesenull.balloonies.item.BallooniesItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
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

        addDrop(BallooniesBlocks.TOFU_BLOCK, LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(BallooniesItems.TOFU)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(9))))));

    }

}
