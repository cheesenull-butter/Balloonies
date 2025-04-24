package cheesenull.balloonies.entity.client.renderer;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.entity.client.HarpoonModel;
import cheesenull.balloonies.entity.custom.HarpoonEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

public class HarpoonRenderer extends EntityRenderer<HarpoonEntity> {

    protected HarpoonModel model;
    private static final Identifier STRING_TEXTURE = Identifier.of(Balloonies.MOD_ID, "textures/entity/harpoon/harpoon_string.png");
    private static final RenderLayer LAYER;

    public HarpoonRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new HarpoonModel(ctx.getPart(HarpoonModel.HARPOON));
    }

    public void render(HarpoonEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

        matrixStack.push();

        Entity owner = entity.getOwner();

        if (owner != null) {

            float j = entity.getStringTicks() + g;
            float k = j * 0.5F % 1.0F;
            float l = entity.getStandingEyeHeight();

            matrixStack.push();

            matrixStack.translate(0.0F, l - 0.5F, 0.0F);

            Vec3d vec3d = this.fromLerpedPosition(owner, (double)owner.getHeight() * 0.5, g);
            Vec3d vec3d2 = this.fromLerpedPosition(entity, (double)l, g);
            Vec3d vec3d3 = vec3d.subtract(vec3d2);

            float m = (float)(vec3d3.length() + 1.0);

            vec3d3 = vec3d3.normalize();

            float n = (float)Math.acos(vec3d3.y);
            float o = (float)Math.atan2(vec3d3.z, vec3d3.x);

            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((1.5707964F - o) * 57.295776F));
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(n * 57.295776F));

            float q = j * 0.05F * -1.5F;

            int s = 255;
            int t = 255;
            int u = 255;

            float x = MathHelper.cos(q + 2.3561945F) * 0.282F;
            float y = MathHelper.sin(q + 2.3561945F) * 0.282F;
            float z = MathHelper.cos(q + 0.7853982F) * 0.282F;
            float aa = MathHelper.sin(q + 0.7853982F) * 0.282F;
            float ab = MathHelper.cos(q + 3.926991F) * 0.282F;
            float ac = MathHelper.sin(q + 3.926991F) * 0.282F;
            float ad = MathHelper.cos(q + 5.4977875F) * 0.282F;
            float ae = MathHelper.sin(q + 5.4977875F) * 0.282F;
            float af = MathHelper.cos(q + 3.1415927F) * 0.2F;
            float ag = MathHelper.sin(q + 3.1415927F) * 0.2F;
            float ah = MathHelper.cos(q + 0.0F) * 0.2F;
            float ai = MathHelper.sin(q + 0.0F) * 0.2F;
            float aj = MathHelper.cos(q + 1.5707964F) * 0.2F;
            float ak = MathHelper.sin(q + 1.5707964F) * 0.2F;
            float al = MathHelper.cos(q + 4.712389F) * 0.2F;
            float am = MathHelper.sin(q + 4.712389F) * 0.2F;

            float an = m;
            float aq = -1.0F + k;
            float ar = m * 2.5F + aq;

            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(LAYER);
            MatrixStack.Entry entry = matrixStack.peek();

            vertex(vertexConsumer, entry, af, an, ag, s, t, u, 0.4999F, ar);
            vertex(vertexConsumer, entry, af, 0.0F, ag, s, t, u, 0.4999F, aq);
            vertex(vertexConsumer, entry, ah, 0.0F, ai, s, t, u, 0.0F, aq);
            vertex(vertexConsumer, entry, ah, an, ai, s, t, u, 0.0F, ar);
            vertex(vertexConsumer, entry, aj, an, ak, s, t, u, 0.4999F, ar);
            vertex(vertexConsumer, entry, aj, 0.0F, ak, s, t, u, 0.4999F, aq);
            vertex(vertexConsumer, entry, al, 0.0F, am, s, t, u, 0.0F, aq);
            vertex(vertexConsumer, entry, al, an, am, s, t, u, 0.0F, ar);

            float as = 0.0F;

            if (entity.age % 2 == 0) {
                as = 0.5F;
            }

            vertex(vertexConsumer, entry, x, an, y, s, t, u, 0.5F, as + 0.5F);
            vertex(vertexConsumer, entry, z, an, aa, s, t, u, 1.0F, as + 0.5F);
            vertex(vertexConsumer, entry, ad, an, ae, s, t, u, 1.0F, as);
            vertex(vertexConsumer, entry, ab, an, ac, s, t, u, 0.5F, as);

            matrixStack.pop();

        }

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, entity.prevYaw, entity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, entity.prevPitch, entity.getPitch()) + 90.0F));

        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(entity)), false, entity.isEnchanted());
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);

        matrixStack.pop();

        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);

    }

    private static void vertex(VertexConsumer vertexConsumer, MatrixStack.Entry matrix, float x, float y, float z, int red, int green, int blue, float u, float v) {
        vertexConsumer.vertex(matrix, x, y, z).color(255, 255, 255, 255).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(15728880).normal(matrix, 0.0F, 1.0F, 0.0F);
    }

    public boolean shouldRender(HarpoonEntity entity, Frustum frustum, double d, double e, double f) {
        if (super.shouldRender(entity, frustum, d, e, f)) {
            return true;
        } else {
            if (entity.getOwner() != null) {
                Entity owner = entity.getOwner();
                if (owner != null) {
                    Vec3d vec3d = this.fromLerpedPosition(owner, (double)owner.getHeight() * 0.5, 1.0F);
                    Vec3d vec3d2 = this.fromLerpedPosition(entity, (double)entity.getStandingEyeHeight(), 1.0F);
                    return frustum.isVisible(new Box(vec3d2.x, vec3d2.y, vec3d2.z, vec3d.x, vec3d.y, vec3d.z));
                }
            }

            return false;
        }
    }

    private Vec3d fromLerpedPosition(Entity entity, double yOffset, float delta) {
        double d = MathHelper.lerp((double)delta, entity.lastRenderX, entity.getX());
        double e = MathHelper.lerp((double)delta, entity.lastRenderY, entity.getY()) + yOffset;
        double f = MathHelper.lerp((double)delta, entity.lastRenderZ, entity.getZ());
        return new Vec3d(d, e, f);
    }

    @Override
    public Identifier getTexture(HarpoonEntity entity) {
        return Identifier.of(Balloonies.MOD_ID, "textures/entity/harpoon/harpoon.png");
    }

    static {
        LAYER = RenderLayer.getEntityCutoutNoCull(STRING_TEXTURE);
    }

}