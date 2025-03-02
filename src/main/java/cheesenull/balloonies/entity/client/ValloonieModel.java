package cheesenull.balloonies.entity.client;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.entity.custom.ValloonieEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ValloonieModel<T extends ValloonieEntity> extends SinglePartEntityModel<T> {

	public static final EntityModelLayer VALLOONIE =
			new EntityModelLayer(Identifier.of(Balloonies.MOD_ID, "valloonie"), "main");
	private final ModelPart valloonie;

	public ValloonieModel(ModelPart root) {
		this.valloonie = root.getChild("valloonie");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData valloonie = modelPartData.addChild("valloonie", ModelPartBuilder.create().uv(0, 0).cuboid(-16.0F, -32.0F, -16.0F, 32.0F, 32.0F, 32.0F, new Dilation(0.0F))
		.uv(60, 64).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 64).cuboid(-5.0F, 1.0F, -5.0F, 10.0F, 2.0F, 10.0F, new Dilation(0.0F))
		.uv(40, 64).cuboid(-5.0F, 3.0F, 0.0F, 10.0F, 32.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public ModelPart getPart() {
		return valloonie;
	}

	@Override
	public void setAngles(ValloonieEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		super.render(matrices, vertices, light, overlay, color);
	}
}