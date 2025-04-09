package cheesenull.balloonies.block;

import cheesenull.balloonies.Balloonies;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BallooniesBlocks {

    public static final Block BLUE_ROSE = registerBlock("blue_rose",
            new FlowerBlock(StatusEffects.NAUSEA, 3.0F,
                    AbstractBlock.Settings.copy(Blocks.POPPY)));
    public static final Block POTTED_BLUE_ROSE = Registry.register(Registries.BLOCK,
            Identifier.of(Balloonies.MOD_ID, "potted_blue_rose"),
            new FlowerPotBlock(BLUE_ROSE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block CLOVER = registerBlock("clover",
            new FlowerBlock(StatusEffects.LUCK, 5.0F,
                    AbstractBlock.Settings.copy(Blocks.POPPY)));
    public static final Block POTTED_CLOVER = Registry.register(Registries.BLOCK,
            Identifier.of(Balloonies.MOD_ID, "potted_clover"),
            new FlowerPotBlock(BLUE_ROSE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Balloonies.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Balloonies.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerBlocks() {
        Balloonies.LOGGER.info("Registering Mod Blocks for " + Balloonies.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {

            entries.addAfter(Blocks.WITHER_ROSE, BLUE_ROSE);
            entries.addAfter(Blocks.DEAD_BUSH, CLOVER);

        });
    }

}
