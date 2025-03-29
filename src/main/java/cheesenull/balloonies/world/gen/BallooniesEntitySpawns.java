package cheesenull.balloonies.world.gen;

import cheesenull.balloonies.entity.BallooniesEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class BallooniesEntitySpawns {

    public static void addSpawns() {

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(
                BiomeKeys.PLAINS,
                        BiomeKeys.SUNFLOWER_PLAINS,
                        BiomeKeys.SNOWY_PLAINS,
                        BiomeKeys.MEADOW),
                SpawnGroup.AMBIENT, BallooniesEntities.BALLOONIE, 1, 0, 1);

        SpawnRestriction.register(BallooniesEntities.BALLOONIE, SpawnLocationTypes.UNRESTRICTED,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FlyingEntity::canMobSpawn);
    }

}
