package nemesis.impl.gui.panel;

import nemesis.impl.gui.widget.Widget;
import nemesis.impl.module.Module;
import nemesis.impl.module.client.Ui;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import static nemesis.NemesisClient.moduleManager;

public class CategoryPanel {
    private final Module.Category category;
    private boolean expanded = true;
    private final List<ModulePanel> modulePanels = new ArrayList<>();
    private final List<Module> modules;
    private int x, y, offsetY;
    
    public CategoryPanel(Module.Category category, List<Module> modules, int x, int y) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.modules = modules != null ? modules : new ArrayList<>();
        
        offsetY = 0;
        for (Module module : this.modules) {
            modulePanels.add(new ModulePanel(this, module, x, y + offsetY));
            offsetY += Widget.HEIGHT;
        }
    }
    
    private int calculateTotalHeight() {
        int countModules = moduleManager.getCountCategory(category);
        int totalHeight = (Widget.HEIGHT + 1) * countModules;
        for (ModulePanel panel : modulePanels) {
            if (panel.isExpanded()) {
                totalHeight += panel.getExpandedHeight() - Widget.HEIGHT;
            }
        }
        return totalHeight;
    }
    
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
    
    public void render(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        int enabledColor = toRGBA(new Color(255, 255, 255, 150));
        
        context.drawBorder(x - 4, y - 4, Widget.WIDTH, Widget.HEIGHT + 4, Color.WHITE.getRGB());
        if (fill()) {
            context.fill(x - 4, y - 4, x + Widget.WIDTH - 4, y + Widget.HEIGHT, enabledColor);
        }
        
        if (bounce()) {
            if (isHovered(mouseX, mouseY)) {
                context.getMatrices().push();
                context.getMatrices().scale(0.9f, 0.9f, 1.0f);
                context.drawText(textRenderer, category.getName(), (int) (x / 0.9f), (int) (y / 0.9f) + 1, Color.WHITE.getRGB(), textShadow());
                context.getMatrices().pop();
            } else {
                context.getMatrices().push();
                context.getMatrices().scale(0.9f, 0.9f, 1.0f);
                context.drawText(textRenderer, category.getName(), (int) (x / 0.9f), (int) (y / 0.9f), Color.WHITE.getRGB(), textShadow());
                context.getMatrices().pop();
            }
        } else {
            context.getMatrices().push();
            context.getMatrices().scale(0.9f, 0.9f, 1.0f);
            context.drawText(textRenderer, category.getName(), (int) (x / 0.9f), (int) (y / 0.9f), Color.WHITE.getRGB(), textShadow());
            context.getMatrices().pop();
        }
        
        if (expanded) {
            int expandedOffsetY = 13;
            for (ModulePanel panel : modulePanels) {
                panel.setPosition(x, y + expandedOffsetY);
                panel.render(context, textRenderer, mouseX, mouseY);
                
                if (panel.isExpanded()) {
                    expandedOffsetY += (panel.getExpandedHeight() + 2);
                } else {
                    expandedOffsetY += Widget.HEIGHT + 1;
                }
            }
            context.drawBorder(x - 4, y + 10, Widget.WIDTH, 3 + calculateTotalHeight(), Color.WHITE.getRGB());
        }
    }
    
    public boolean isExpanded() {
        return expanded;
    }
    
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 1 && isHovered(mouseX, mouseY)) {
            expanded = !expanded;
            return true;
        }
        if (expanded) {
            for (ModulePanel panel : modulePanels) {
                if (panel.mouseClicked(mouseX, mouseY, button)) return true;
            }
        }
        return false;
    }
    
    private boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= this.x - 4 && mouseX <= this.x + Widget.WIDTH - 4 &&
                mouseY >= this.y - 4 && mouseY <= this.y + Widget.HEIGHT;
    }
    
    private static int toRGBA(Color color) {
        return (color.getAlpha() << 24) |
                (color.getRed() << 16) |
                (color.getGreen() << 8) |
                color.getBlue();
    }
}
