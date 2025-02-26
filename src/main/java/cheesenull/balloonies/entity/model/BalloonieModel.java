package cheesenull.balloonies.entity.model;

import cheesenull.balloonies.entity.custom.BalloonieEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class BalloonieModel extends HierarchyModel {
	private final ModelPart balloonie;

	public BalloonieModel(ModelPart root) {
        super();
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
	public void setAngles(BalloonieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		balloonie.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}