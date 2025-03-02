package cheesenull.balloonies;

import cheesenull.balloonies.entity.BallooniesEntities;
import cheesenull.balloonies.entity.client.BalloonieModel;
import cheesenull.balloonies.entity.client.ValloonieModel;
import cheesenull.balloonies.entity.client.renderer.BalloonieRenderer;
import cheesenull.balloonies.entity.client.renderer.ValloonieRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BallooniesClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		EntityModelLayerRegistry.registerModelLayer(BalloonieModel.BALLOONIE,
				BalloonieModel::getTexturedModelData);
		EntityRendererRegistry.register(BallooniesEntities.BALLOONIE, BalloonieRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ValloonieModel.VALLOONIE,
				ValloonieModel::getTexturedModelData);
		EntityRendererRegistry.register(BallooniesEntities.VALLOONIE, ValloonieRenderer::new);

	}

}