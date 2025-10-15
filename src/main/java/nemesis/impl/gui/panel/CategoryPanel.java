package nemesis.impl.gui.panel;

import nemesis.impl.gui.widget.Widget;
import nemesis.impl.module.Module;
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
    private int x, y;
    public static int offsetY;
    
    public CategoryPanel(Module.Category category, List<Module> modules, int x, int y) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.modules = modules != null ? modules : new ArrayList<>();
        
        offsetY = 16;
        for (Module module : this.modules) {
            modulePanels.add(new ModulePanel(module, x, y + offsetY));
            offsetY += 17;
        }
    }
    
    public void render(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        context.drawBorder(x - 4, y - 4, Widget.WIDTH, Widget.HEIGHT, Color.WHITE.getRGB());
        //Color fillCategory = new Color(255, 255, 255, 170);
        //context.fill(x - 4, y - 4, Widget.WIDTH, Widget.HEIGHT, toRGBA(fillCategory));
        
        context.drawText(textRenderer, category.getName(), x, y, Color.WHITE.getRGB(), false);
        
        if (expanded) {
            int offsetY = 16;
            for (ModulePanel panel : modulePanels) {
                panel.setPosition(x, y + offsetY);
                panel.render(context, textRenderer, mouseX, mouseY);
                
                if (panel.isExpanded()) {
                    offsetY += panel.getExpandedHeight();
                } else {
                    offsetY += Widget.HEIGHT + 1;
                }
            }
            
            int countModules = moduleManager.getCountCategory(category);
            int moduleHeight = 17;
            context.drawBorder(x - 4, y + 11, Widget.WIDTH, 3 + moduleHeight * countModules, Color.WHITE.getRGB());
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
        return mouseX >= this.x - 4 && mouseX <= this.x + Widget.WIDTH - 4&&
                mouseY >= this.y - 4 && mouseY <= this.y + Widget.HEIGHT - 4;
    }
    
    private static int toRGBA(Color color) {
        return (color.getAlpha() << 24) |
                (color.getRed() << 16) |
                (color.getGreen() << 8) |
                color.getBlue();
    }
}
