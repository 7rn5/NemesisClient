package nemesis.util;

import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;

import static nemesis.NemesisClient.mc;

public class CheckUtil {
    public static boolean checkScreen() {
        return mc.currentScreen != null && !(mc.currentScreen instanceof ChatScreen || mc.currentScreen instanceof SignEditScreen || mc.currentScreen instanceof DeathScreen);
    }
}