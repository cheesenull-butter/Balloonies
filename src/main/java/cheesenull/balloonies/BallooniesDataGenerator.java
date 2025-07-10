package cheesenull.balloonies;

import cheesenull.balloonies.datagen.BallooniesItemTagProvider;
import cheesenull.balloonies.datagen.BallooniesBlockLootTableProvider;
import cheesenull.balloonies.datagen.BallooniesModelProvider;
import cheesenull.balloonies.datagen.BallooniesRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BallooniesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider((output, registries) ->
				new BallooniesItemTagProvider(output, registries, null)
		);
		pack.addProvider(BallooniesBlockLootTableProvider::new);
		pack.addProvider(BallooniesModelProvider::new);
		pack.addProvider(BallooniesRecipeProvider::new);

	}
}
