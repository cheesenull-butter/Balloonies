package cheesenull.balloonies;

import cheesenull.balloonies.block.BallooniesBlocks;
import cheesenull.balloonies.effect.custom.DistortionShader;
import cheesenull.balloonies.entity.BallooniesEntities;
import cheesenull.balloonies.entity.client.BalloonieModel;
import cheesenull.balloonies.entity.client.BallooningModel;
import cheesenull.balloonies.entity.client.renderer.BalloonieRenderer;
import cheesenull.balloonies.entity.client.renderer.BallooningRenderer;
import cheesenull.balloonies.particle.BallooniesParticles;
import cheesenull.balloonies.particle.custom.ConfettiParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class BallooniesClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		DistortionShader.init();

		BlockRenderLayerMap.INSTANCE.putBlock(BallooniesBlocks.BLUE_ROSE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BallooniesBlocks.POTTED_BLUE_ROSE, RenderLayer.getCutout());

		EntityModelLayerRegistry.registerModelLayer(BalloonieModel.BALLOONIE,
				BalloonieModel::getTexturedModelData);
		EntityRendererRegistry.register(BallooniesEntities.BALLOONIE, BalloonieRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(BallooningModel.BALLOONING,
				BallooningModel::getTexturedModelData);
		EntityRendererRegistry.register(BallooniesEntities.BALLOONING, BallooningRenderer::new);

		ParticleFactoryRegistry.getInstance().register(BallooniesParticles.CONFETTI_BLACK,
				ConfettiParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(BallooniesParticles.CONFETTI_BLUE,
				ConfettiParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(BallooniesParticles.CONFETTI_ORANGE,
				ConfettiParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(BallooniesParticles.CONFETTI_RED,
				ConfettiParticle.Factory::new);

	}

}