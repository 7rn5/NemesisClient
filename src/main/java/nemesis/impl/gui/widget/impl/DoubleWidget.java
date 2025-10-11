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

        // 数値表示（左側）
        String valueStr = String.format("%.2f", value);
        context.drawText(textRenderer, valueStr, x, y + 2, Color.WHITE.getRGB(), false);

        // スライダーの位置
        int sliderX = x + 5; // 数字の横から開始
        int sliderY = y + HEIGHT / 2;
        int sliderWidth = WIDTH;
        int sliderHeight = 2;

        // 背景バー
        context.fill(sliderX, sliderY, sliderX + sliderWidth, sliderY + sliderHeight, new Color(80, 80, 80).getRGB());

        // 現在の値位置
        double normalized = (value - min) / (max - min);
        int knobX = sliderX + (int) (normalized * sliderWidth);

        // ポインタ（四角）
        int knobSize = 6;
        context.fill(knobX - knobSize / 2, sliderY - 2, knobX + knobSize / 2, sliderY + sliderHeight + 2, Color.WHITE.getRGB());
    }

    @Override
    public boolean mouseClicked(DoubleSetting setting, double mouseX, double mouseY, int button) {
        double min = setting.getMin();
        double max = setting.getMax();
        int sliderX = 40;
        int sliderWidth = WIDTH;

        // スライダー範囲内ならドラッグ開始
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
            int sliderWidth = WIDTH;
            updateValueFromMouse(setting, mouseX, sliderX, sliderWidth, min, max);
        }
    }

    private void updateValueFromMouse(DoubleSetting setting, double mouseX, int sliderX, int sliderWidth, double min, double max) {
        double percent = Math.max(0, Math.min(1, (mouseX - sliderX) / sliderWidth));
        double newValue = min + percent * (max - min);
        setting.set(newValue);
    }
    
    public static int getWHeight() {
        return Widget.HEIGHT;
    }
}