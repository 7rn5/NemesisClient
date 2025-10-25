package nemesis.impl.module.client;

import nemesis.event.Render2DEvent;
import nemesis.event.bus.Subscribe;
import nemesis.impl.module.Module;
import nemesis.settings.impl.BoolSetting;
import nemesis.settings.impl.ColorSetting;

import static nemesis.NemesisClient.mc;
import static nemesis.NemesisClient.eventHandler;
import static nemesis.NemesisClient.CLIENT_NAME;
import static nemesis.NemesisClient.CLIENT_VERSION;
import static nemesis.NemesisClient.CLIENT_STATUS;
import static nemesis.NemesisClient.GIT_HASH;

import java.awt.Color;

public class Hud extends Module {
    private final BoolSetting waterMark = addSetting(new BoolSetting.Builder()
        .name("waterMark")
        .defaultValue(true)
        .build()
    );
    
    public final ColorSetting waterMarkColor = addSetting(new ColorSetting.Builder()
        .name("WaterMarkColor")
        .defaultValue(new Color(170, 170, 170))
        .alpha(false)
        .build()
    );
    
    public Hud() {
        super("Hud", "Make your gaming experience more comfortable!", Category.Client);
        eventHandler.subscribe(this);
    }
    
    @Subscribe
    public void onRender(Render2DEvent event) {
        if (waterMark.get()) {
            String name = null;
            if (GIT_HASH == null) {
                name = CLIENT_NAME + "-" + CLIENT_STATUS + CLIENT_VERSION;
            } else {
                name = CLIENT_NAME + "-" + CLIENT_STATUS + CLIENT_VERSION + "-" + GIT_HASH;
            }
            event.getContext().drawText(mc.textRenderer, name, 2, 2, waterMarkColor.getRGB(), true);
        }
    }
}