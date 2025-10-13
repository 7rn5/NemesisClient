package nemesis.impl.gui.widget.impl;

import nemesis.settings.impl.BoolSetting;
import nemesis.impl.gui.widget.Widget;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.awt.Color;

public class BoolWidget implements Widget<BoolSetting> {
    @Override
    public void render(DrawContext context, TextRenderer textRenderer, BoolSetting setting, int x, int y, int mouseX, int mouseY) {
        //back ground
        int bgColor = setting.get() ? new Color(0, 200, 0, 150).getRGB() : new Color(200, 0, 0, 150).getRGB();
        context.fill(x, y, x + WIDTH, y + HEIGHT, bgColor);
        
        //outline
        context.drawBorder(x, y, WIDTH, HEIGHT, Color.BLACK.getRGB());
        
        context.drawText(textRenderer, setting.getName(), x + PADDING, y + (HEIGHT - textRenderer.fontHeight) / 2, Color.WHITE.getRGB(), false);
    }
    
    @Override
    public boolean mouseClicked(BoolSetting setting, double mouseX, double mouseY, int button) {
        //left click
        if (button == 0 && mouseX >= 0 && mouseX <= WIDTH && mouseY >= 0 && mouseY <= HEIGHT) {
            setting.toggle();
            return true;
        }
        return false;
    }
    
    @Override
    public void mouseDragged(BoolSetting setting, double mouseX) {}
}