package cheesenull.balloonies.item;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.block.BallooniesBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BallooniesCreativeTabs {

    public static final ItemGroup BALLOONIES_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Balloonies.MOD_ID, "balloonies_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(BallooniesBlocks.BLUE_ROSE))
                    .displayName(Text.translatable("itemGroup.balloonies.balloonies_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(BallooniesBlocks.BLUE_ROSE);
                        entries.add(BallooniesBlocks.CLOVER);
                        entries.add(BallooniesItems.QUIVER);
                    }).build());


    public static void registerItemGroups() {
        Balloonies.LOGGER.info("Registering Item Groups for " + Balloonies.MOD_ID);
    }

}
