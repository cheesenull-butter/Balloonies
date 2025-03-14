package cheesenull.balloonies.entity.client;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.entity.custom.BalloonieEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BalloonieModel<T extends BalloonieEntity> extends SinglePartEntityModel<T> {

	public static final EntityModelLayer BALLOONIE =
			new EntityModelLayer(Identifier.of(Balloonies.MOD_ID, "balloonie"), "main");

	private final ModelPart balloonie;

	public BalloonieModel(ModelPart root) {
		this.balloonie = root.getChild("balloonie");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData balloonie = modelPartData.addChild("balloonie", ModelPartBuilder.create().uv(0, 0).cuboid(-16.0F, -32.0F, -16.0F, 32.0F, 32.0F, 32.0F, new Dilation(0.0F))
		.uv(60, 64).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 64).cuboid(-5.0F, 1.0F, -5.0F, 10.0F, 2.0F, 10.0F, new Dilation(0.0F))
		.uv(40, 64).cuboid(-5.0F, 3.0F, 0.0F, 10.0F, 32.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public ModelPart getPart() {
		return balloonie;
	}

	@Override
	public void setAngles(BalloonieEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		super.render(matrices, vertices, light, overlay, color);
	}
}