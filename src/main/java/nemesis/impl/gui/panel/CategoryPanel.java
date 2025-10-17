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
        
        offsetY = 12;
        for (Module module : this.modules) {
            modulePanels.add(new ModulePanel(module, x, y + offsetY));
            offsetY += 14;
        }
    }
    
    private int calculateTotalHeight() {
        int countModules = moduleManager.getCountCategory(category);
        int totalHeight = Widget.HEIGHT * countModules;
        for (ModulePanel panel : modulePanels) {
            if (panel.isExpanded()) {
                totalHeight += panel.getExpandedHeight() - Widget.HEIGHT;
            }
        }
        return totalHeight;
    }
    
    private boolean textShadow() {
        Ui uiModule = moduleManager.get(Ui.class);
        return uiModule.textShadow.get();
    }
    
    public void render(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        int enabledColor = toRGBA(new Color(255, 255, 255, 150));
        
        context.drawBorder(x - 4, y - 4, Widget.WIDTH, Widget.HEIGHT + 4, Color.WHITE.getRGB());
        context.fill(x - 4, y - 4, x + Widget.WIDTH - 4, y + Widget.HEIGHT, enabledColor);
        
        if (isHovered(mouseX, mouseY)) {
            context.getMatrices().push();
            context.getMatrices().scale(0.9f, 0.9f, 1.0f);
            context.drawText(textRenderer, category.getName(), (int) (x / 0.9f), (int) (y + 1 / 0.9f), Color.WHITE.getRGB(), textShadow());
            context.getMatrices().pop();
        } else {
            context.getMatrices().push();
            context.getMatrices().scale(0.9f, 0.9f, 1.0f);
            context.drawText(textRenderer, category.getName(), (int) (x / 0.9f), (int) (y / 0.9f), Color.WHITE.getRGB(), textShadow());
            context.getMatrices().pop();
        }
        
        if (expanded) {
            int offsetY = 16;
            for (ModulePanel panel : modulePanels) {
                panel.setPosition(x, y + offsetY);
                panel.render(context, textRenderer, mouseX, mouseY);
                
                if (panel.isExpanded()) {
                    offsetY += panel.getExpandedHeight();
                } else {
                    offsetY += Widget.HEIGHT;
                }
            }
            context.drawBorder(x - 4, y + 9, Widget.WIDTH, 3 + calculateTotalHeight(), Color.WHITE.getRGB());
        }
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
        return mouseX >= this.x - 4 && mouseX <= this.x + Widget.WIDTH &&
                mouseY >= this.y - 4 && mouseY <= this.y + Widget.HEIGHT + 4;
    }
    
    private static int toRGBA(Color color) {
        return (color.getAlpha() << 24) |
                (color.getRed() << 16) |
                (color.getGreen() << 8) |
                color.getBlue();
    }
}
