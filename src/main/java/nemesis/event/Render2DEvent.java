package nemesis.event;

import nemesis.event.bus.Event;
import net.minecraft.client.gui.DrawContext;

public class Render2DEvent extends Event {
    private static DrawContext context;
    private static int screenWidth, screenHeight;
    private static float tickDelta;
    
    public Render2DEvent(DrawContext context, int screenWidth, int screenHeight, float tickDelta) {
        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.tickDelta = tickDelta;
    }
    
    public DrawContext getContext() {
        return context;
    }
    
    public int getScreenWidth() {
        return screenWidth;
    }
    
    public int getScreenHeight() {
        return screenHeight;
    }
}