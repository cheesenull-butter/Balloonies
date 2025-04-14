package cheesenull.balloonies.item;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.item.custom.BaguetteItem;
import cheesenull.balloonies.item.custom.QuiverItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BallooniesItems {

    public static final Item OXIDIZED_BLADE = registerItem("oxidized_blade",
            new SwordItem(ToolMaterials.IRON, new Item.Settings()
                    .attributeModifiers(SwordItem
                            .createAttributeModifiers(ToolMaterials.IRON,
                                    3, -2.4F))
                    .food(BallooniesFoodComponents.BAGUETTE)));
    public static final Item QUIVER = registerItem("quiver",
            new QuiverItem(new Item.Settings()
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
            entries.addAfter(Items.WOODEN_SWORD, OXIDIZED_BLADE);
            entries.addAfter(Items.CROSSBOW, QUIVER);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addBefore(Items.BREAD, BAGUETTE);
        });

    }

}
