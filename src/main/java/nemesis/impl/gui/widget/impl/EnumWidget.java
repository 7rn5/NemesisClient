package nemesis.impl.gui.widget.impl;

import nemesis.impl.gui.widget.Widget;
import nemesis.impl.module.client.Ui;
import nemesis.settings.impl.EnumSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.List;

import static nemesis.NemesisClient.moduleManager;

public class EnumWidget<T extends Enum<T>> implements Widget<EnumSetting<T>> {
    private boolean expanded = false;
    
    private Ui getUiModule() {
        return moduleManager.get(Ui.class);
    }
    
    private int getColor() {
        return getUiModule().color.getRGB();
    }
    
    private boolean fill() {
        return getUiModule().fill.get();
    }
    
    private boolean textShadow() {
        return getUiModule().textShadow.get();
    }
    
    private boolean bounce() {
        return getUiModule().bounce.get();
    }
    
    @Override
    public void render(DrawContext context, TextRenderer textRenderer, EnumSetting<T> setting, int x, int y, int mouseX, int mouseY) {
        //now
        context.getMatrices().push();
        context.getMatrices().scale(0.8f, 0.8f, 1.0f);
        context.drawText(textRenderer, setting.getName() + ": " + setting.get().name(), (int) (x / 0.8f), (int) (y / 0.8f), Color.WHITE.getRGB(), textShadow());
        context.getMatrices().pop();
        
        //list
        if (expanded) {
            List<T> values = setting.getValues();
            int offsetY = widgetHeight;
            for (T val : values) {
                context.getMatrices().push();
                context.getMatrices().scale(0.8f, 0.8f, 1.0f);
                context.drawText(textRenderer, "- " + val.name(), (int) ((x + 4) / 0.8f), (int) ((y + offsetY) / 0.8f), Color.LIGHT_GRAY.getRGB(), textShadow());
                context.getMatrices().pop();
                offsetY += widgetHeight;
            }
            //ouline
            int listCount = setting.getSize();
            context.drawBorder(x, y, widgetWidth, widgetHeight * listCount, Color.WHITE.getRGB());
        } else {
            context.drawBorder(x, y, widgetWidth, widgetHeight, Color.WHITE.getRGB());
        }
    }

    @Override
    public boolean mouseClicked(EnumSetting<T> setting, double mouseX, double mouseY, int button) {
        if (button == 1) {
            expanded = !expanded;
            return true;
        }
        if (button == 0 && expanded) {
            List<T> values = setting.getValues();
            return true;
        }
        return false;
    }
    
    @Override
    public void mouseDragged(EnumSetting<T> setting, double mouseX) {}
}