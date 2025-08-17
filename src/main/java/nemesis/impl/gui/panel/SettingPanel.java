/*package nemesis.impl.gui.panel;

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

    public SettingPanel(T setting, Widget<T> widget, int x, int y) {
        this.setting = setting;
        this.widget = widget;
        this.x = x;
        this.y = y;
    }

    public void render(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        widget.render(context, textRenderer, setting, x, y, mouseX, mouseY);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return widget.mouseClicked(setting, mouseX, mouseY, button);
    }

    public void mouseDragged(double mouseX) {
        widget.mouseDragged(setting, mouseX);
    }

    public int getHeight() {
        return Widget.HEIGHT;
    }

    public T getSetting() {
        return setting;
    }
}*/


package nemesis.impl.gui.panel;

import nemesis.settings.Setting;
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

        // Setting の型を見て Widget を割り当てる
        if (setting instanceof nemesis.settings.impl.BindSetting) {
            this.widget = (Widget<T>) new BindWidget();
        } else if (setting instanceof nemesis.settings.impl.BoolSetting) {
            this.widget = (Widget<T>) new BoolWidget();
        } else if (setting instanceof nemesis.settings.impl.ColorSetting) {
            this.widget = (Widget<T>) new ColorWidget();
        } else if (setting instanceof nemesis.settings.impl.DoubleSetting) {
            this.widget = (Widget<T>) new DoubleWidget(); 
        } else if (setting instanceof nemesis.settings.impl.EnumSetting) {
            this.widget = (Widget<T>) new EnumWidget();
        } else {
            this.widget = null; // 未対応
        }
    }

    public void render(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        if (widget != null) {
            widget.render(context, textRenderer, setting, x, y, mouseX, mouseY);
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return widget != null && widget.mouseClicked(setting, mouseX, mouseY, button);
    }

    public void mouseDragged(double mouseX) {
        if (widget != null) {
            widget.mouseDragged(setting, mouseX);
        }
    }

    public int getHeight() {
        return Widget.HEIGHT;
    }

    public T getSetting() {
        return setting;
    }
}