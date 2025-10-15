package nemesis.impl.gui.widget.impl;

import nemesis.impl.gui.widget.Widget;
import nemesis.impl.module.client.Ui;
import nemesis.settings.impl.BindSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

import java.awt.Color;

import static nemesis.NemesisClient.mc;
import static nemesis.NemesisClient.moduleManager;

public class BindWidget implements Widget<BindSetting> {
    private boolean listening = false;
    private int x, y;
    
    private boolean textShadow() {
        Ui uiModule = moduleManager.get(Ui.class);
        return uiModule.textShadow.get();
    }
    
    @Override
    public void render(DrawContext context, TextRenderer textRenderer, BindSetting setting, int x, int y, int mouseX, int mouseY) {
        //back ground
        int bgColor = listening ? new Color(50, 50, 150, 150).getRGB() : new Color(50, 50, 50, 150).getRGB();
        context.fill(x, y, x + widgetWidth, y + widgetHeight, bgColor);
        
        //outline
        context.drawBorder(x, y, widgetWidth, widgetHeight, Color.BLACK.getRGB());
        
        //draw text
        String keyName = setting.getKeyName();
        String label = setting.getName() + ": " + (listening ? "Press a key..." : keyName);
        if (textShadow()) {
            context.drawTextWithShadow(textRenderer, label, x + PADDING, y + (widgetHeight - textRenderer.fontHeight) / 2, Color.WHITE.getRGB());
        } else {
            context.drawText(textRenderer, label, x + PADDING, y + (widgetHeight - textRenderer.fontHeight) / 2, Color.WHITE.getRGB(), false);
        }
        
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean mouseClicked(BindSetting setting, double mouseX, double mouseY, int button) {
        //left click
        if (button == 0 && isHovered(mouseX, mouseY)) {
            listening = !listening;
            return true;
        }
        return false;
    }
    
    private boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= this.x && mouseX <= this.x + widgetWidth &&
                mouseY >= this.y && mouseY <= this.y + widgetHeight;
    }

    @Override
    public void mouseDragged(BindSetting setting, double mouseX) {}
    
    //これはあとで連携させる
    public boolean keyPressed(BindSetting setting, int keyCode, int scanCode, int modifiers) {
        if (!listening) return false;
        
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            setting.set(BindSetting.KEY_NONE);
        } else {
            setting.set(keyCode);
        }
        
        listening = false;
        return true;
    }
}