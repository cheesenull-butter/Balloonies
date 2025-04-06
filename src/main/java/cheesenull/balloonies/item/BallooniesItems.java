package cheesenull.balloonies.item;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.item.custom.QuiverItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BallooniesItems {

    public static final Item QUIVER = registerItem("quiver",
            new QuiverItem(new Item.Settings()
                    .maxDamage(77)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Balloonies.MOD_ID, name), item);
    }

    public static void registerItems() {
        Balloonies.LOGGER.info("Registering Mod Items for " + Balloonies.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.CROSSBOW, QUIVER);
        });
    }

}
