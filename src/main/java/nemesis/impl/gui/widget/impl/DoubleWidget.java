package nemesis.impl.gui.widget.impl;

import nemesis.impl.gui.widget.Widget;
import nemesis.settings.impl.DoubleSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class DoubleWidget implements Widget<DoubleSetting> {
    private boolean dragging = false;

    @Override
    public void render(DrawContext context, TextRenderer textRenderer, DoubleSetting setting, int x, int y, int mouseX, int mouseY) {
        double value = setting.get();
        double min = setting.getMin();
        double max = setting.getMax();
        
        String valueStr = String.format("%.2f", value);
        context.drawText(textRenderer, valueStr, x, y + 2, Color.WHITE.getRGB(), false);
        
        int sliderX = x + 5;
        int sliderY = y + widgetHeight / 2;
        int sliderWidth = widgetWidth;
        int sliderHeight = 2;
        
        //background
        context.fill(sliderX, sliderY, sliderX + sliderWidth, sliderY + sliderHeight, new Color(80, 80, 80).getRGB());
        
        //now int
        double normalized = (value - min) / (max - min);
        int knobX = sliderX + (int) (normalized * sliderWidth);
        
        //pointer
        int knobSize = 6;
        context.fill(knobX - knobSize / 2, sliderY - 2, knobX + knobSize / 2, sliderY + sliderHeight + 2, Color.WHITE.getRGB());
    }
    
    @Override
    public boolean mouseClicked(DoubleSetting setting, double mouseX, double mouseY, int button) {
        double min = setting.getMin();
        double max = setting.getMax();
        int sliderX = 40;
        int sliderWidth = widgetWidth;
        
        //slider
        if (mouseX >= sliderX && mouseX <= sliderX + sliderWidth) {
            dragging = true;
            updateValueFromMouse(setting, mouseX, sliderX, sliderWidth, min, max);
            return true;
        }
        return false;
    }
    
    @Override
    public void mouseDragged(DoubleSetting setting, double mouseX) {
        if (dragging) {
            double min = setting.getMin();
            double max = setting.getMax();
            int sliderX = 40;
            int sliderWidth = widgetWidth;
            updateValueFromMouse(setting, mouseX, sliderX, sliderWidth, min, max);
        }
    }
    
    private void updateValueFromMouse(DoubleSetting setting, double mouseX, int sliderX, int sliderWidth, double min, double max) {
        double percent = Math.max(0, Math.min(1, (mouseX - sliderX) / sliderWidth));
        double newValue = min + percent * (max - min);
        setting.set(newValue);
    }
}