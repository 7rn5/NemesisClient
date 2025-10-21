package nemesis.impl.gui.widget.impl;

import nemesis.impl.gui.widget.Widget;
import nemesis.impl.module.client.Ui;
import nemesis.settings.impl.ColorSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

import static nemesis.NemesisClient.moduleManager;

public class ColorWidget implements Widget<ColorSetting> {
    private static final int SLIDER_WIDTH = 100;
    private static final int BUTTON_WIDTH = 40;
    private static final int BUTTON_HEIGHT = widgetHeight;
    
    private int x, y;
    private boolean rainbow = false;
    private boolean expanded = false;
    
    private boolean textShadow() {
        Ui uiModule = moduleManager.get(Ui.class);
        return uiModule.textShadow.get();
    }
    
    @Override
    public void render(DrawContext context, TextRenderer textRenderer, ColorSetting setting, int x, int y, int mouseX, int mouseY) {
        this.x = x;
        this.y = y;

        int currentY = y;
        
        if (!expanded) {
            //Base outline
            context.fill(x, y, x + widgetWidth, y + widgetHeight, Color.WHITE.getRGB());
            
            //Title
            context.getMatrices().push();
            context.getMatrices().scale(0.9f, 0.9f, 1.0f);
            context.drawText(textRenderer, setting.getName(), x, y + 3, Color.WHITE.getRGB(), textShadow());
            context.getMatrices().pop();
            
            //Color viewer
            context.drawBorder(x + widgetWidth - 14, y + 2, widgetHeight - 4, widgetHeight - 4, Color.BLACK.getRGB());
            context.fill(x + widgetWidth - 13, y + 3, x + widgetWidth - 3, y + widgetHeight - 3, setting.getRGB());
        }
        
        /*
        //color picker
        context.fill(x, currentY, x + SLIDER_WIDTH, currentY + widgetHeight, setting.get().getRGB());
        context.drawBorder(x, currentY, SLIDER_WIDTH, widgetHeight, Color.BLACK.getRGB());
        currentY += widgetHeight + PADDING;
        
        //rainbow toggle
        String rainbowText = "Rainbow: " + (rainbow ? "ON" : "OFF");
        if (textShadow()) {
            context.drawTextWithShadow(textRenderer, rainbowText, x, currentY + 3, Color.WHITE.getRGB());
        } else {
            context.drawText(textRenderer, rainbowText, x, currentY + 3, Color.WHITE.getRGB(), false);
        }
        currentY += widgetHeight + PADDING;
        
        //alpha slider
        int alphaWidth = (int) ((setting.get().getAlpha() / 255.0) * SLIDER_WIDTH);
        context.fill(x, currentY, x + alphaWidth, currentY + SLIDER_HEIGHT, new Color(200, 200, 200).getRGB());
        context.drawBorder(x, currentY, SLIDER_WIDTH, SLIDER_HEIGHT, Color.BLACK.getRGB());
        currentY += SLIDER_HEIGHT + PADDING;
        
        //copy button
        context.fill(x, currentY, x + BUTTON_WIDTH, currentY + BUTTON_HEIGHT, new Color(80, 80, 80).getRGB());
        if (textShadow()) {
            context.drawTextWithShadow(textRenderer, "Copy", x + 8, currentY + 3, Color.WHITE.getRGB());
        } else {
            context.drawText(textRenderer, "Copy", x + 8, currentY + 3, Color.WHITE.getRGB(), false);
        }
        
        //paste button
        int pasteX = x + BUTTON_WIDTH + PADDING;
        context.fill(pasteX, currentY, pasteX + BUTTON_WIDTH, currentY + BUTTON_HEIGHT, new Color(80, 80, 80).getRGB());
        if (textShadow()) {
            context.drawTextWithShadow(textRenderer, "Paste", pasteX + 6, currentY + 3, Color.WHITE.getRGB());
        } else {
            context.drawText(textRenderer, "Paste", pasteX + 6, currentY + 3, Color.WHITE.getRGB(), false);
        }
        */
    }
    
    @Override
    public boolean mouseClicked(ColorSetting setting, double mouseX, double mouseY, int button) {
        //let click
        /*if (button == 0) {
            int currentY = y + (widgetHeight + PADDING) * 2 + SLIDER_HEIGHT + PADDING;
            
            //copy button
            if (mouseX >= x && mouseX <= x + BUTTON_WIDTH &&
                mouseY >= currentY && mouseY <= currentY + BUTTON_HEIGHT) {
                copyToClipboard(setting.get());
                return true;
            }
            
            //paste button
            int pasteX = x + BUTTON_WIDTH + PADDING;
            if (mouseX >= pasteX && mouseX <= pasteX + BUTTON_WIDTH &&
                mouseY >= currentY && mouseY <= currentY + BUTTON_HEIGHT) {
                Color pasted = pasteFromClipboard();
                if (pasted != null) setting.set(pasted);
                return true;
            }
            
            //rainbow button
            int rainbowY = y + widgetHeight + PADDING;
            if (mouseY >= rainbowY && mouseY <= rainbowY + widgetHeight &&
                mouseX >= x && mouseX <= x + SLIDER_WIDTH) {
                rainbow = !rainbow;
                return true;
            }
        }*/
        return false;
    }
    
    @Override
    public void mouseDragged(ColorSetting setting, double mouseX) {
        /*
        int sliderY = y + (widgetHeight + PADDING) * 2;
        if (mouseX >= x && mouseX <= x + SLIDER_WIDTH) {
            int alpha = (int) (((mouseX - x) / SLIDER_WIDTH) * 255);
            alpha = Math.max(0, Math.min(255, alpha));
            Color c = setting.get();
            setting.set(new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha));
        }
        */
    }
    
    private void copyToClipboard(Color color) {
        String rgb = color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "," + color.getAlpha();
        StringSelection selection = new StringSelection(rgb);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    }
    
    private Color pasteFromClipboard() {
        try {
            String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            String[] parts = data.split(",");
            if (parts.length == 4) {
                int r = Integer.parseInt(parts[0]);
                int g = Integer.parseInt(parts[1]);
                int b = Integer.parseInt(parts[2]);
                int a = Integer.parseInt(parts[3]);
                return new Color(r, g, b, a);
            }
        } catch (UnsupportedFlavorException | IOException | NumberFormatException ignored) {}
        return null;
    }
}