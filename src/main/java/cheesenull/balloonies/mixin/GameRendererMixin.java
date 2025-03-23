package cheesenull.balloonies.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GameRenderer.class)
public interface GameRendererMixin {

	@Invoker("loadPostProcessor")
	void balloonies$loadPostProcessor(Identifier id);

}