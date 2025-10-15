package nemesis.impl.gui.panel;

import nemesis.impl.gui.panel.CategoryPanel;
import nemesis.impl.gui.widget.Widget;
import nemesis.impl.module.Module;
import nemesis.settings.Setting;
import nemesis.settings.impl.*;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

public class ModulePanel {
    private final Module module;
    private final List<SettingPanel<?>> settingPanels = new ArrayList<>();
    private int x, y;
    private boolean expanded = false;
    
    public ModulePanel(Module module, int x, int y) {
        this.module = module;
        this.x = x;
        this.y = y;
        
        int offsetY = 28;
        for (Setting<?> setting : module.getSettings()) {
            settingPanels.add(new SettingPanel<>(setting, x, y + offsetY));
            offsetY += Widget.HEIGHT + 3;
        }
    }
    
    public void render(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        Color disabledColor = new Color(255, 255, 255, 80);
        Color enabledColor = new Color(255, 255, 255, 150);
        Color hoveredColor = new Color(255, 255, 255, 120);
        Color white = new Color(255, 255, 255, 255);
        
        /*if (isHovered(mouseX, mouseY)) {
            context.fill(x - 2, y - 2, x + (Widget.WIDTH - 6), y + (Widget.HEIGHT - 3), toRGBA(hoveredColor));
        }*/
        
        if (module.isEnabled()) {
            context.fill(x - 2, y - 2, x + (Widget.WIDTH - 6), y + (Widget.HEIGHT - 3), toRGBA(enabledColor));
        } else {
            context.fill(x - 2, y - 2, x + (Widget.WIDTH - 6), y + (Widget.HEIGHT - 3), toRGBA(disabledColor));
        }
        
        if (isHovered(mouseX, mouseY)) {
            context.drawText(textRenderer, module.getName(), x, y, toRGBA(white), false);
        } else {
            context.drawText(textRenderer, module.getName(), x, y - 1, toRGBA(white), false);
        }
        
        //context.drawBorder(x - 2, y - 2, Widget.WIDTH - 6, Widget.HEIGHT - 4, Color.WHITE.getRGB());
        
        if (expanded) {
            int offsetY = Widget.HEIGHT + 2;
            for (SettingPanel<?> panel : settingPanels) {
                panel.setPosition(x, y + offsetY);
                panel.render(context, textRenderer, mouseX, mouseY);
                offsetY += Widget.HEIGHT + 2;
            }
            if (module.isEnabled()) {
                context.drawBorder(x - 2, y - 2, Widget.WIDTH - 4, getExpandedHeight() - 1, toRGBA(enabledColor));
            } else {
                context.drawBorder(x - 2, y - 2, Widget.WIDTH - 4 + 17, getExpandedHeight() - 2 - 17, toRGBA(disabledColor));
            }
        }
    }
    
    private void drawBorderNoTop(DrawContext context, int x, int y, int width, int height, int color) {
        // 左の線
        context.fill(x, y, x + 1, y + height, color);
        // 下の線
        context.fill(x, y + height - 1, x + width, y + height, color);
        // 右の線
        context.fill(x + width - 1, y, x + width, y + height, color);
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean isExpanded() {
        return expanded;
    }
    
    public int getExpandedHeight() {
        if (!expanded) return Widget.HEIGHT + 2;
        return Widget.HEIGHT + 2 + settingPanels.size() * (Widget.HEIGHT + 2);
    }
    
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && isHovered(mouseX, mouseY) && module.canDisable()) {
            module.toggle();
            return true;
        }
        if (button == 1 && isHovered(mouseX, mouseY)) {
            expanded = !expanded;
            return true;
        }
        if (expanded) {
            for (SettingPanel<?> panel : settingPanels) {
                if (panel.mouseClicked(mouseX, mouseY, button)) return true;
            }
        }
        return false;
    }
    
    private boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= this.x - 2 && mouseX <= this.x + Widget.WIDTH - 6 &&
                mouseY >= this.y - 2 && mouseY <= this.y + Widget.HEIGHT - 3;
    }
    
    private static int toRGBA(Color color) {
        return (color.getAlpha() << 24) |
                (color.getRed() << 16) |
                (color.getGreen() << 8) |
                color.getBlue();
    }
}
