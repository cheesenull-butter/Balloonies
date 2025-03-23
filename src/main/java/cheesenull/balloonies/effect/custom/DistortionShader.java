package cheesenull.balloonies.effect.custom;

import cheesenull.balloonies.effect.BallooniesEffects;
import cheesenull.balloonies.mixin.GameRendererMixin;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class DistortionShader {

    private static final Identifier DISTORTION_SHADER =
            Identifier.of("balloonies:shaders/post/distortion.json");

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                boolean hasDistortion = client.player.hasStatusEffect(BallooniesEffects.DISTORTION);

                if (hasDistortion && !isShaderActive(client)) {
                    applyShader(client);
                } else if (!hasDistortion && isShaderActive(client)) {
                    removeShader(client);
                }
            }
        });
    }

    private static boolean isShaderActive(MinecraftClient client) {
        return client.gameRenderer.getPostProcessor() != null;
    }

    private static void applyShader(MinecraftClient client) {
        if (client.gameRenderer.getPostProcessor() == null) {
            ((GameRendererMixin) client.gameRenderer)
                    .balloonies$loadPostProcessor(DISTORTION_SHADER);
        }
    }

    private static void removeShader(MinecraftClient client) {
        if (client.gameRenderer.getPostProcessor() != null) {
            client.gameRenderer.disablePostProcessor();
        }
    }

}
