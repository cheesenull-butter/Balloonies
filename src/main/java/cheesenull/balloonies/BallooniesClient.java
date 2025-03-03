package cheesenull.balloonies;

import cheesenull.balloonies.block.BallooniesBlocks;
import cheesenull.balloonies.entity.BallooniesEntities;
import cheesenull.balloonies.entity.client.BalloonieModel;
import cheesenull.balloonies.entity.client.ValloonieModel;
import cheesenull.balloonies.entity.client.renderer.BalloonieRenderer;
import cheesenull.balloonies.entity.client.renderer.ValloonieRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class BallooniesClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		BlockRenderLayerMap.INSTANCE.putBlock(BallooniesBlocks.BLUE_ROSE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BallooniesBlocks.POTTED_BLUE_ROSE, RenderLayer.getCutout());

		EntityModelLayerRegistry.registerModelLayer(BalloonieModel.BALLOONIE,
				BalloonieModel::getTexturedModelData);
		EntityRendererRegistry.register(BallooniesEntities.BALLOONIE, BalloonieRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ValloonieModel.VALLOONIE,
				ValloonieModel::getTexturedModelData);
		EntityRendererRegistry.register(BallooniesEntities.VALLOONIE, ValloonieRenderer::new);

	}

}