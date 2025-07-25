package nemesis.impl.gui;

import nemesis.NemesisClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class GUI extends Screen {
    public GUI() {
        super(Text.of(NemesisClient.CLIENT_NAME + "ClickGui"));
    }
    
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }
}
