package nemesis.mixininterface;

import net.minecraft.text.Text;
import net.minecraft.client.gui.hud.MessageIndicator;

public interface IChatHud {
    void nemesis$add(Text text, int id);
    void nemesis$add(Text text, MessageIndicator indicator, int id);
}