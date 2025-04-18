package cheesenull.balloonies.entity.client;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.entity.custom.HarpoonEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class HarpoonModel extends EntityModel<HarpoonEntity> {

	public static final EntityModelLayer HARPOON =
			new EntityModelLayer(Identifier.of(Balloonies.MOD_ID, "harpoon"), "main");

	private final ModelPart harpoon;

	public HarpoonModel(ModelPart root) {
		this.harpoon = root.getChild("harpoon");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData harpoon = modelPartData.addChild("harpoon", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -10.0F, -0.5F, 1.0F, 18.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 0).cuboid(-1.0F, -5.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F))
				.uv(4, 0).cuboid(0.0F, -17.0F, -3.0F, 0.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void setAngles(HarpoonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		harpoon.render(matrices ,vertices, light, overlay, color);
	}

}