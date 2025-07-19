package nemesis.mixin;

import nemesis.NemesisClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    @Shadow
    private long backgroundFadeStart;

    @Shadow
    @Final
    private boolean doBackgroundFade;

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void hookRender(final DrawContext context, final int mouseX, final int mouseY, final float delta, final CallbackInfo info) {
        float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0f : 1.0f;
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0f, 0.0f, 1.0f) : 1.0f;
        int alpha = MathHelper.ceil(g * 255.0f) << 24;

        if ((alpha & 0xFC000000) == 0) return;

        String text = String.format("%s v%s (%s)", NemesisClient.CLIENT_NAME, NemesisClient.CLIENT_VERSION, NemesisClient.CLIENT_STATUS);
        int x = 2;
        int y = height - (client.textRenderer.fontHeight * 2) - 3;
        int color = 0xFFFFFF | alpha;

        context.drawTextWithShadow(client.textRenderer, text, x, y, color);

        int textWidth = client.textRenderer.getWidth(text);
        int textHeight = client.textRenderer.fontHeight;
        boolean hovered = mouseX >= x && mouseX <= x + textWidth &&
                          mouseY >= y && mouseY <= y + textHeight;

        if (hovered) {
            context.fill(x, y + textHeight + 1, x + textWidth, y + textHeight + 2, 0xFFFFFFFF);
        }
    }
    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
    String text = String.format("%s v%s (%s)", NemesisClient.CLIENT_NAME, NemesisClient.CLIENT_VERSION, NemesisClient.CLIENT_STATUS);
    
        int x = 2;
        int y = height - (client.textRenderer.fontHeight * 2) - 3;
        int textWidth = client.textRenderer.getWidth(text);
        int textHeight = client.textRenderer.fontHeight;

        boolean hovered = mouseX >= x && mouseX <= x + textWidth &&
                      mouseY >= y && mouseY <= y + textHeight;

        if (hovered && button == 0) {
            try {
                Util.getOperatingSystem().open("https://github.com/potato7103/NemesisClient");
            } catch (Exception e) {
                e.printStackTrace();
            }
            cir.setReturnValue(true);  
        }
    }
}