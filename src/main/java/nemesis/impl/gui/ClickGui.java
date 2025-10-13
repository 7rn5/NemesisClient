package nemesis.impl.gui;

import nemesis.NemesisClient;
import nemesis.event.ClickGuiCloseEvent;
import nemesis.impl.gui.panel.CategoryPanel;
import nemesis.impl.module.client.Ui;
import nemesis.impl.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

import static nemesis.NemesisClient.moduleManager;

public class ClickGui extends Screen {
    private final List<CategoryPanel> categoryPanels = new ArrayList<>();
    
    private static Ui getUiModule() {
        return moduleManager.get(Ui.class);
    }

    public ClickGui() {
        super(Text.of("Click GUI"));
        
        int x = 20;
        for (Module.Category category : Module.Category.values()) {
            List<Module> mods = moduleManager.getByCategory(category);
            categoryPanels.add(new CategoryPanel(category, mods, x, 20));
            x += 102;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        TextRenderer textRenderer = this.textRenderer;

        for (CategoryPanel panel : categoryPanels) {
            panel.render(context, textRenderer, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (CategoryPanel panel : categoryPanels) {
            if (panel.mouseClicked(mouseX, mouseY, button)) return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    public void removed() {
        super.removed();
        getUiModule().disable();
        NemesisClient.eventHandler.post(new ClickGuiCloseEvent());
    }
    
    @Override
    public boolean shouldPause() {
        return false;
    }
}