package nemesis.mixin;

import nemesis.event.Render2DEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.lwjgl.opengl.GL11;

import static nemesis.NemesisClient.eventHandler;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("RETURN"))
    public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        Render2DEvent event = new Render2DEvent(context, context.getScaledWindowWidth(), context.getScaledWindowWidth(), tickCounter.getTickDelta(true));
        eventHandler.post(event);
        if (event.isCancelled()) ci.cancel();
    }
}