package cheesenull.balloonies.entity;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.entity.custom.balloonie.BalloonieEntity;
import cheesenull.balloonies.entity.custom.BallooningEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BallooniesEntities {

    public static final EntityType<BalloonieEntity> BALLOONIE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Balloonies.MOD_ID, "balloonie"),
            EntityType.Builder.create(BalloonieEntity::new, SpawnGroup.CREATURE)
                    .dimensions(2.0F, 2.0F).build());
    public static final EntityType<BallooningEntity> BALLOONING = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Balloonies.MOD_ID, "ballooning"),
            EntityType.Builder.create(BallooningEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.625F, 0.625F).build());

    public static void registerEntities() {
        Balloonies.LOGGER.info("Registering Mod Entities for " + Balloonies.MOD_ID);
    }

}
