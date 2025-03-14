package cheesenull.balloonies.entity.client;

// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.entity.custom.BallooningEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BallooningModel<T extends BallooningEntity> extends SinglePartEntityModel<T> {

	public static final EntityModelLayer BALLOONING =
			new EntityModelLayer(Identifier.of(Balloonies.MOD_ID, "ballooning"), "main");

	private final ModelPart ballooning;

	public BallooningModel(ModelPart root) {
		this.ballooning = root.getChild("ballooning");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData ballooning = modelPartData.addChild("ballooning", ModelPartBuilder.create().uv(-12, -8).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F))
		.uv(-3, -2).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData string = ballooning.addChild("string", ModelPartBuilder.create().uv(2, 2).cuboid(-0.5F, 1.0F, 0.0F, 1.0F, 17.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 16, 16);
	}

	@Override
	public ModelPart getPart() {
		return ballooning;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		super.render(matrices, vertices, light, overlay, color);
	}
}