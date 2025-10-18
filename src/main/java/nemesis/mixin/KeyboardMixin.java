package nemesis.mixin;

import nemesis.NemesisClient;
import nemesis.event.KeyEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey", at = @At("TAIL"), cancellable = true)
    private void onKey(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
        if (action != 1) return;
        KeyEvent event = new KeyEvent(key);
        NemesisClient.eventHandler.post(event);
        if (event.isCancelled()) ci.cancel();
    }
}