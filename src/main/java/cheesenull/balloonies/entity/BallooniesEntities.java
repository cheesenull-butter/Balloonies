package cheesenull.balloonies.entity;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.entity.custom.BalloonieEntity;
import cheesenull.balloonies.entity.custom.ValloonieEntity;
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
    public static final EntityType<ValloonieEntity> VALLOONIE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Balloonies.MOD_ID, "valloonie"),
            EntityType.Builder.create(ValloonieEntity::new, SpawnGroup.CREATURE)
                    .dimensions(2.0F, 2.0F).build());


    public static void registerModEntities() {
        Balloonies.LOGGER.info("Registering Mod Entities for " + Balloonies.MOD_ID);
    }

}
