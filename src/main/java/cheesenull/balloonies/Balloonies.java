package cheesenull.balloonies;

import cheesenull.balloonies.block.BallooniesBlocks;
import cheesenull.balloonies.effect.BallooniesEffects;
import cheesenull.balloonies.entity.BallooniesEntities;
import cheesenull.balloonies.entity.custom.balloonie.BalloonieEntity;
import cheesenull.balloonies.entity.custom.BallooningEntity;
import cheesenull.balloonies.item.BallooniesCreativeTabs;
import cheesenull.balloonies.item.BallooniesItems;
import cheesenull.balloonies.particle.BallooniesParticles;
import cheesenull.balloonies.sound.BallooniesSounds;
import cheesenull.balloonies.world.gen.BallooniesWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Balloonies implements ModInitializer {

	public static final String MOD_ID = "balloonies";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		BallooniesCreativeTabs.registerItemGroups();

		BallooniesItems.registerItems();
		BallooniesBlocks.registerBlocks();
		BallooniesEntities.registerEntities();

		BallooniesParticles.registerParticles();
		BallooniesEffects.registerEffects();
		BallooniesSounds.registerSounds();

		BallooniesWorldGeneration.generateModWorldGen();

		FabricDefaultAttributeRegistry.register(BallooniesEntities.BALLOONIE,
				BalloonieEntity.createBalloonieAttributes());
		FabricDefaultAttributeRegistry.register(BallooniesEntities.BALLOONING,
				BallooningEntity.createBallooningAttributes());

		LOGGER.info("Hello Fabric world!");

	}
}