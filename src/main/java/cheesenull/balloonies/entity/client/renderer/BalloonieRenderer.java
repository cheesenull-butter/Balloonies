package cheesenull.balloonies.entity.client.renderer;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.entity.client.BalloonieModel;
import cheesenull.balloonies.entity.custom.balloonie.BalloonieEntity;
import cheesenull.balloonies.entity.custom.balloonie.BalloonieVariants;
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
public class BalloonieRenderer extends MobEntityRenderer<BalloonieEntity, SinglePartEntityModel<BalloonieEntity>> {

    private static final Map<BalloonieVariants, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BalloonieVariants.class), map -> {
                map.put(BalloonieVariants.BLUE,
                        Identifier.of(Balloonies.MOD_ID, "textures/entity/balloonie/balloonie_blue.png"));
                map.put(BalloonieVariants.GREEN,
                        Identifier.of(Balloonies.MOD_ID, "textures/entity/balloonie/balloonie_green.png"));
                map.put(BalloonieVariants.ORANGE,
                        Identifier.of(Balloonies.MOD_ID, "textures/entity/balloonie/balloonie_orange.png"));
                map.put(BalloonieVariants.RED,
                        Identifier.of(Balloonies.MOD_ID, "textures/entity/balloonie/balloonie_red.png"));
                map.put(BalloonieVariants.WHITE,
                        Identifier.of(Balloonies.MOD_ID, "textures/entity/balloonie/balloonie_white.png"));
            });

    public BalloonieRenderer(EntityRendererFactory.Context context) {
        super(context, new BalloonieModel<>(context.getPart(BalloonieModel.BALLOONIE)), 0.6F);
    }

    @Override
    public void render(BalloonieEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(BalloonieEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

}
