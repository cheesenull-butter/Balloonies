package cheesenull.balloonies.entity.client.renderer;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.entity.client.BalloonieModel;
import cheesenull.balloonies.entity.client.BallooningModel;
import cheesenull.balloonies.entity.custom.BalloonieEntity;
import cheesenull.balloonies.entity.custom.BalloonieVariant;
import cheesenull.balloonies.entity.custom.BallooningEntity;
import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class BallooningRenderer extends MobEntityRenderer<BallooningEntity, SinglePartEntityModel<BallooningEntity>> {

    public BallooningRenderer(EntityRendererFactory.Context context) {
        super(context, new BallooningModel<>(context.getPart(BallooningModel.BALLOONING)), 0.6F);
    }

    @Override
    public void render(BallooningEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(BallooningEntity entity) {
        return Identifier.of(Balloonies.MOD_ID, "textures/entity/ballooning.png");
    }

}
