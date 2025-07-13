/*package nemesis.mixin;

import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(SplashTextResourceSupplier.class)
public abstract class SplashTextResourceSupplierMixin {
    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private void injectSplash(CallbackInfoReturnable<String> cir) {
        List<String> list = List.of(
            "NemesisClient",
            "Welcome to Nemesis",
            "Best client",
            "Nemesis for 2b2t",
            "2b2t.org"
        );
        String splash = list.get(new Random().nextInt(list.size()));
        cir.setReturnValue(splash);
    }
}*/