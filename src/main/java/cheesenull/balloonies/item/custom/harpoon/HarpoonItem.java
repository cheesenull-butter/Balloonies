package cheesenull.balloonies.item.custom.harpoon;

import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.Item;

import java.util.List;

public class HarpoonItem extends Item {

    public HarpoonItem(Settings settings) {
        super(settings);
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2);
    }

}
