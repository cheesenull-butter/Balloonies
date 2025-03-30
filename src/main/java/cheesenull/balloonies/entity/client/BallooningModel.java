package cheesenull.balloonies.entity.client;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.entity.custom.BallooningEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
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
		ModelPartData ballooning = modelPartData.addChild("ballooning", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F))
		.uv(0, 20).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(16, 20).cuboid(-1.0F, 1.0F, 0.0F, 2.0F, 17.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 48, 48);
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