package nemesis.mixin;

import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

import nemesis.impl.module.misc.SplashText;
import static nemesis.NemesisClient.moduleManager;

@Mixin(SplashTextResourceSupplier.class)
public abstract class SplashTextResourceSupplierMixin {
    @Unique
    private static final Random random = new Random();
    
    @Unique
    private final List<String> splashes = getSplashes();
    
    private SplashText getSplashTextModule() {
        return moduleManager.get(SplashText.class);
    }

    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private void onApply(CallbackInfoReturnable<SplashTextRenderer> cir) {
        if (!getSplashTextModule().isEnabled()) return;
        
        cir.setReturnValue(new SplashTextRenderer(splashes.get(random.nextInt(splashes.size()))));
    }
    
    @Unique
    private static List<String> getSplashes() {
        return List.of(
              "NemesisClient",
              "Welcome to Nemesis",
              "Best Client",
              "Nemesis for 2b2t",
              "2b2t.org",
              "Meow",
              "PRIVATE CLIENT",
              "Who is 7rn5",
              "WE ARE CETD",
              "Cetd Family"
        );
    }
}
