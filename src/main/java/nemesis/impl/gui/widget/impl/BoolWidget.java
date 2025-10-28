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
    public void render(DrawContext context, TextRenderer textRenderer, BoolSetting setting, int x, int y, int mouseX, int mouseY) {
        //back ground
        int bgColor = setting.get() ? new Color(0, 200, 0, 150).getRGB() : new Color(200, 0, 0, 150).getRGB();
        
        if (fill()) {
            context.fill(x, y, x + widgetWidth, y + widgetHeight, bgColor);
        }
        
        //bool name
        context.getMatrices().push();
        context.getMatrices().scale(0.8f, 0.8f, 1.0f);
        context.drawText(textRenderer, setting.getName(), (int) ((x + PADDING) / 0.8f), (int) ((y + (widgetHeight - textRenderer.fontHeight) / 2) / 0.8f), Color.WHITE.getRGB(), textShadow());
        context.getMatrices().pop();
        
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