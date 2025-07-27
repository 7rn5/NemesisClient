/*package nemesis.impl.gui;

import nemesis.NemesisClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class GUI extends Screen {
    private static ClickGui INSTANCE;
    static {
        INSTANCE = new clickGui();
    }
    
    public GUI() {
        super(Text.of(NemesisClient.CLIENT_NAME + "ClickGui"));
        setInstance();
        load();
    }
    
    private void setInstance() {
        INSTANCE = this;
    }
    
    private void load() {
        
    }
    
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }
}*/



/*package nemesis.impl.gui;

import nemesis.NemesisClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.List;

public class GUI extends Screen {
    private static GUI INSTANCE;
    private final ArrayList<Panel> panels = new ArrayList();

    static {
        INSTANCE = new GUI();
    }

    public static GUI getInstance() {
        return INSTANCE;
    }

    public GUI() {
        super(Text.of(NemesisClient.CLIENT_NAME + " ClickGUI"));
        setInstance();
        //load();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    private void load() {
        panels.clear();
        int x = 10;
        for (Module.Category category : Module.Category.values()) {
        panels.add(new Panel(category.getName(), x, 20, 100, 14));
        x += 110;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        for (Panel panel : panels) {
            panel.render(context, mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Panel panel : panels) {
            if (panel.mouseClicked(mouseX, mouseY, button)) return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Panel panel : panels) {
            if (panel.mouseReleased(mouseX, mouseY, button)) return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (Panel panel : panels) {
            if (panel.keyPressed(keyCode, scanCode, modifiers)) return true;
        }

        // ESCキーで閉じる
        if (keyCode == 256) {
            client.setScreen(null);
            return true;
            
            //Ui module = NemesisClient.moduleManager.getModules(Ui.class);
            //module.toggle();
            //FovModifier module = NotFound.moduleManager.getModuleByClass(FovModifier.class);
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}*/