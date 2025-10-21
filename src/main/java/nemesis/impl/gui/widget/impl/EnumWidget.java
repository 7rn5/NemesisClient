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
    
    private boolean textShadow() {
        Ui uiModule = moduleManager.get(Ui.class);
        return uiModule.textShadow.get();
    }
    
    @Override
    public void render(DrawContext context, TextRenderer textRenderer, EnumSetting<T> setting, int x, int y, int mouseX, int mouseY) {
        //now
        context.drawText(textRenderer, setting.getName() + ": " + setting.get().name(), x, y, Color.WHITE.getRGB(), textShadow());
        
        //list
        if (expanded) {
            List<T> values = setting.getValues();
            int offsetY = widgetHeight;
            for (T val : values) {
                context.getMatrices().push();
                context.getMatrices().scale(0.9f, 0.9f, 1.0f);
                context.drawText(textRenderer, "- " + val.name(), x + 4, y + offsetY, Color.LIGHT_GRAY.getRGB(), textShadow());
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