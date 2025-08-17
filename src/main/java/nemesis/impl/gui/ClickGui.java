/*package nemesis.impl.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import nemesis.util.player.ChatUtil;

public class ClickGui extends Screen {
    int scissorRegionX = 200;
    int scissorRegionY = 20;
    int scissorRegionWidth = 100;
    int scissorRegionHeight = this.height - 40;

    public ClickGui() {
        super(Text.of("Click Gui"));
    }

    @Override
    protected void init() {
        // 画面中央にボタンを追加
        this.addDrawableChild(ButtonWidget.builder(Text.of("Click Me"), button -> {
            // ボタンを押したときの処理
            //System.out.println("Button clicked!");
            ChatUtil.info("button clicked");
        }).position(this.width / 2 - 50, this.height / 2 - 10).size(100, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // 背景を描画
        //this.renderBackground(context);

        // タイトル描画
        //context.drawCenteredText(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        
        context.enableScissor(scissorRegionX, scissorRegionY, scissorRegionX + scissorRegionWidth, scissorRegionY + scissorRegionHeight);
        
        context.fillGradient(0, 0, this.width, this.height, 0xFFFF0000, 0xFF0000FF);
        
        context.disableScissor();

        // その他の描画
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}*/


package nemesis.impl.gui;

import nemesis.impl.gui.panel.CategoryPanel;
import nemesis.impl.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGui extends Screen {
    private final List<CategoryPanel> categoryPanels = new ArrayList<>();

    public ClickGui() {
        super(Text.of("Click GUI"));

        int x = 10;
        for (Module.Category category : Module.Category.values()) {
            categoryPanels.add(new CategoryPanel(category, x, 20));
            x += 120; // パネル間の間隔
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
}