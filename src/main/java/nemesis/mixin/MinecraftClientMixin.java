package nemesis.mixin;

import nemesis.NemesisClient;
import nemesis.event.TickEvent;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo info) {
        NemesisClient.eventHandler.post(new TickEvent());
    }
}