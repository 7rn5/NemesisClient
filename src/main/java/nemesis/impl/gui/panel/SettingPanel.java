package nemesis.impl.gui.panel;

import nemesis.settings.Setting;
import nemesis.settings.impl.*;
import nemesis.impl.gui.widget.Widget;
import nemesis.impl.gui.widget.impl.*;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class SettingPanel<T extends Setting<?>> {
    private final T setting;
    private final Widget<T> widget;
    private int x, y;
    
    @SuppressWarnings("unchecked")
    public SettingPanel(T setting, int x, int y) {
        this.setting = setting;
        this.x = x;
        this.y = y;
        
        if (setting instanceof BindSetting) {
            this.widget = (Widget<T>) new BindWidget();
        } else if (setting instanceof BoolSetting) {
            this.widget = (Widget<T>) new BoolWidget();
        } else if (setting instanceof ColorSetting) {
            this.widget = (Widget<T>) new ColorWidget();
        } else if (setting instanceof DoubleSetting) {
            this.widget = (Widget<T>) new DoubleWidget(); 
        } else if (setting instanceof EnumSetting) {
            this.widget = (Widget<T>) new EnumWidget();
        } else {
            this.widget = null;
        }
    }
    
    public void render(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        if (widget != null) {
            widget.render(context, textRenderer, setting, x, y - 1, mouseX, mouseY);
        }
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return widget != null && widget.mouseClicked(setting, mouseX, mouseY, button);
    }
    
    public void mouseDragged(double mouseX) {
        if (widget != null) {
            widget.mouseDragged(setting, mouseX);
        }
    }
    
    public T getSetting() {
        return setting;
    }
}
