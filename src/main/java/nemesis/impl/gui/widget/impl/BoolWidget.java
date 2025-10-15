package nemesis.impl.gui.widget.impl;

import nemesis.impl.gui.widget.Widget;
import nemesis.impl.module.client.Ui;
import nemesis.settings.impl.BoolSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.awt.Color;

import static nemesis.NemesisClient.moduleManager;

public class BoolWidget implements Widget<BoolSetting> {
    private int x, y;
    
    private boolean textShadow() {
        Ui uiModule = moduleManager.get(Ui.class);
        return uiModule.textShadow.get();
    }
    
    @Override
    public void render(DrawContext context, TextRenderer textRenderer, BoolSetting setting, int x, int y, int mouseX, int mouseY) {
        //back ground
        int bgColor = setting.get() ? new Color(0, 200, 0, 150).getRGB() : new Color(200, 0, 0, 150).getRGB();
        context.fill(x, y, x + widgetWidth, y + widgetHeight, bgColor);
        
        //outline
        context.drawBorder(x, y, widgetWidth, widgetHeight, Color.BLACK.getRGB());
        
        if (textShadow()) {
            context.drawTextWithShadow(textRenderer, setting.getName(), x + PADDING, y + (widgetHeight - textRenderer.fontHeight) / 2, Color.WHITE.getRGB());
        } else {
            context.drawText(textRenderer, setting.getName(), x + PADDING, y + (widgetHeight - textRenderer.fontHeight) / 2, Color.WHITE.getRGB(), false);
        }
        
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean mouseClicked(BoolSetting setting, double mouseX, double mouseY, int button) {
        //left click
        if (button == 0 && isHovered(mouseX, mouseY)) {
            setting.toggle();
            return true;
        }
        return false;
    }
    
    private boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= this.x && mouseX <= this.x + widgetWidth &&
                mouseY >= this.y && mouseY <= this.y + widgetHeight;
    }
    
    @Override
    public void mouseDragged(BoolSetting setting, double mouseX) {}
}