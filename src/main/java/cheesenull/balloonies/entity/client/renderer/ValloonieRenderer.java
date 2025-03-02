package cheesenull.balloonies.entity.client.renderer;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.entity.client.ValloonieModel;
import cheesenull.balloonies.entity.custom.ValloonieEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ValloonieRenderer extends MobEntityRenderer<ValloonieEntity, SinglePartEntityModel<ValloonieEntity>> {

    public ValloonieRenderer(EntityRendererFactory.Context context) {
        super(context, new ValloonieModel<>(context.getPart(ValloonieModel.VALLOONIE)), 0.6F);
    }

    @Override
    public void render(ValloonieEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(ValloonieEntity entity) {
        return Identifier.of(Balloonies.MOD_ID, "textures/entity/valloonie.png");
    }

}
