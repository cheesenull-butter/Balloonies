package cheesenull.balloonies;

import cheesenull.balloonies.block.BallooniesBlocks;
import cheesenull.balloonies.entity.BallooniesEntities;
import cheesenull.balloonies.entity.custom.BalloonieEntity;
import cheesenull.balloonies.entity.custom.ValloonieEntity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Balloonies implements ModInitializer {

	public static final String MOD_ID = "balloonies";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		BallooniesBlocks.registerBlocks();
		BallooniesEntities.registerModEntities();

		FabricDefaultAttributeRegistry.register(BallooniesEntities.BALLOONIE,
				BalloonieEntity.createBalloonieAttributes());
		FabricDefaultAttributeRegistry.register(BallooniesEntities.VALLOONIE,
				ValloonieEntity.createValloonieAttributes());

		LOGGER.info("Hello Fabric world!");

	}
}