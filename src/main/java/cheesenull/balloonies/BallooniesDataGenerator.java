package cheesenull.balloonies;

import cheesenull.balloonies.datagen.BallooniesLootTableProvider;
import cheesenull.balloonies.datagen.BallooniesModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BallooniesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();


		pack.addProvider(BallooniesLootTableProvider::new);
		pack.addProvider(BallooniesModelProvider::new);

	}
}
