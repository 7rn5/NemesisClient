package nemesis.impl.gui.panel;

import nemesis.impl.module.Module;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

import static nemesis.NemesisClient.moduleManager;

public class CategoryPanel {
    private final Module.Category category;
    private final List<ModulePanel> modulePanels = new ArrayList<>();
    private final List<Module> modules;
    private int x, y;

    /*public CategoryPanel(Module.Category category, int x, int y) {
    *    this.category = category;
    *    this.x = x;
    *    this.y = y;
    *
    *    int offsetY = 20;
    *    for (Module module : moduleManager.getByCategory(category)) {
    *        modulePanels.add(new ModulePanel(module, x + 5, y + offsetY));
    *        offsetY += 60; // モジュール1個分の高さ仮置き
    *    }
    }*/
    
    public CategoryPanel(Module.Category category, List<Module> modules, int x, int y) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.modules = modules != null ? modules : new ArrayList<>();
        
        int offsetY = 20;
        for (Module module : this.modules) {
            modulePanels.add(new ModulePanel(module, x + 5, y+ offsetY));
            offsetY += 14;
        }
    }

    public void render(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        // カテゴリ名
        context.drawText(textRenderer, category.getName(), x, y, 0x00FFAA, false);

        for (ModulePanel panel : modulePanels) {
            panel.render(context, textRenderer, mouseX, mouseY);
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (ModulePanel panel : modulePanels) {
            if (panel.mouseClicked(mouseX, mouseY, button)) return true;
        }
        return false;
    }
}