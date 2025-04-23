package cheesenull.balloonies.item;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.item.custom.BaguetteItem;
import cheesenull.balloonies.item.custom.OxidizedBladeItem;
import cheesenull.balloonies.item.custom.QuiverItem;
import cheesenull.balloonies.item.custom.HarpoonItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class BallooniesItems {

    public static final Item OXIDIZED_BLADE = registerItem("oxidized_blade",
            new OxidizedBladeItem(ToolMaterials.IRON, new Item.Settings()
                    .rarity(Rarity.RARE)
                    .attributeModifiers(SwordItem
                            .createAttributeModifiers(ToolMaterials.IRON,
                                    3, -2.4F))
                    .food(BallooniesFoodComponents.BAGUETTE)));
    public static final Item HARPOON = registerItem("harpoon",
            new HarpoonItem(new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .maxDamage(232)
                    .attributeModifiers(HarpoonItem
                            .createAttributeModifiers())
                    .component(DataComponentTypes.TOOL, HarpoonItem.createToolComponent())));
    public static final Item QUIVER = registerItem("quiver",
            new QuiverItem(new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .maxDamage(77)));
    public static final Item BAGUETTE = registerItem("baguette",
            new BaguetteItem(ToolMaterials.WOOD, new Item.Settings()
                    .attributeModifiers(SwordItem
                            .createAttributeModifiers(ToolMaterials.WOOD,
                                    2, -2.4F))
                    .food(BallooniesFoodComponents.BAGUETTE)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Balloonies.MOD_ID, name), item);
    }

    public static void registerItems() {
        Balloonies.LOGGER.info("Registering Mod Items for " + Balloonies.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addBefore(Items.WOODEN_SWORD, OXIDIZED_BLADE);
            entries.addAfter(OXIDIZED_BLADE, BAGUETTE);
            entries.addBefore(Items.TRIDENT, HARPOON);
            entries.addAfter(Items.CROSSBOW, QUIVER);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addBefore(Items.BREAD, BAGUETTE);
        });

    }

}
