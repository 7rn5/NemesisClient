package nemesis.impl.gui.panel;

import nemesis.impl.gui.panel.CategoryPanel;
import nemesis.impl.gui.widget.Widget;
import nemesis.impl.module.Module;
import nemesis.impl.module.client.Ui;
import nemesis.settings.Setting;
import nemesis.settings.impl.*;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import static nemesis.NemesisClient.moduleManager;

public class ModulePanel {
    private final Module module;
    private final List<SettingPanel<?>> settingPanels = new ArrayList<>();
    private int x, y;
    private int widgetPadding = 1;
    private boolean expanded = false;
    private CategoryPanel category;
    
    public ModulePanel(CategoryPanel category, Module module, int x, int y) {
        this.category = category;
        this.module = module;
        this.x = x;
        this.y = y;
        
        int offsetY = 18;
        for (Setting<?> setting : module.getSettings()) {
            settingPanels.add(new SettingPanel<>(setting, x, y + offsetY));
            offsetY += Widget.HEIGHT;
        }
    }
    
    private boolean fill() {
        Ui uiModule = moduleManager.get(Ui.class);
        return uiModule.fill.get();
    }
    
    private boolean textShadow() {
        Ui uiModule = moduleManager.get(Ui.class);
        return uiModule.textShadow.get();
    }
    
    private boolean bounce() {
        Ui uiModule = moduleManager.get(Ui.class);
        return uiModule.bounce.get();
    }
    
    public void render(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        int disabledColor = toRGBA(new Color(255, 255, 255, 80));
        int enabledColor = toRGBA(new Color(255, 255, 255, 150));
        int hoveredColor = toRGBA(new Color(255, 255, 255, 120));
        int white = toRGBA(new Color(255, 255, 255, 255));
        
        if (fill()) {
            if (module.isEnabled()) {
                context.fill(x - 2, y - 1, x + Widget.WIDTH - 6, y + Widget.HEIGHT - 1, enabledColor);
            } else {
                context.fill(x - 2, y - 1, x + Widget.WIDTH - 6, y + Widget.HEIGHT - 1, disabledColor);
            }
        }
        
        if (bounce()) {
            if (isHovered(mouseX, mouseY)) {
                context.getMatrices().push();
                context.getMatrices().scale(0.8f, 0.8f, 1.0f);
                context.drawText(textRenderer, module.getName(), (int) (x / 0.8f), (int) (y / 0.8f) + 2, white, textShadow());
                context.getMatrices().pop();
            } else {
                context.getMatrices().push();
                context.getMatrices().scale(0.8f, 0.8f, 1.0f);
                context.drawText(textRenderer, module.getName(), (int) (x / 0.8f), (int) (y / 0.8f) + 1, white, textShadow());
                context .getMatrices().pop();
            }
        } else {
            context.getMatrices().push();
            context.getMatrices().scale(0.8f, 0.8f, 1.0f);
            context.drawText(textRenderer, module.getName(), (int) (x / 0.8f), (int) (y / 0.8f) + 1, white, textShadow());
            context .getMatrices().pop();
        }
        
        if (expanded) {
            int offsetY = Widget.HEIGHT + 3;
            for (SettingPanel<?> panel : settingPanels) {
                panel.setPosition(x, y + offsetY);
                panel.render(context, textRenderer, mouseX, mouseY);
                offsetY += Widget.HEIGHT + widgetPadding;
            }
            if (module.isEnabled()) {
                context.drawBorder(x - 2, y + 9, Widget.WIDTH - 4, getExpandedHeight() - Widget.HEIGHT + 1, enabledColor);
            } else {
                context.drawBorder(x - 2, y + 9, Widget.WIDTH - 4, getExpandedHeight() - Widget.HEIGHT + 1, disabledColor);
            }
        }
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean isExpanded() {
        return expanded;
    }
    
    public int getExpandedHeight() {
        if (!expanded) return Widget.HEIGHT + widgetPadding;
        return Widget.HEIGHT + widgetPadding + settingPanels.size() * (Widget.HEIGHT + widgetPadding);
    }
    
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && isHovered(mouseX, mouseY) && module.canDisable() && category.isExpanded()) {
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
                mouseY >= this.y - 1 && mouseY <= this.y + Widget.HEIGHT - 1;
    }
    
    private static int toRGBA(Color color) {
        return (color.getAlpha() << 24) |
                (color.getRed() << 16) |
                (color.getGreen() << 8) |
                color.getBlue();
    }
}
