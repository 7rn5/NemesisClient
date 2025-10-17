package nemesis.impl.gui.widget;

import nemesis.settings.Setting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public interface Widget<T extends Setting<?>> {
    int HEIGHT = 10;
    int WIDTH = 100;
    int PADDING = 2;
    int SLIDER_HEIGHT = 6;
    int widgetHeight = 16;
    int widgetWidth = 92;
    
    void render(DrawContext context, TextRenderer textRenderer, T setting, int x, int y, int mouseX, int mouseY);
    boolean mouseClicked(T setting, double mouseX, double mouseY, int button);
    void mouseDragged(T setting, double mouseX);
}
