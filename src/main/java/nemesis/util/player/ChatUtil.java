package nemesis.util.player;

import nemesis.NemesisClient;
import nemesis.mixin.imixin.IChatHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.client.gui.hud.InGameHud;
//import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;

import java.awt.Color;

public class ChatUtil {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    public static final String CLIENT_PREFIX = "§7[ §FNemesis §7] §r";
    
    public static void clientSendMessage(String message) {clientSendMessage(message, 0);}

    public static void clientSendMessage(String message, int id) {
        if (mc.player == null || mc.inGameHud == null) return;
        
        int gray = new Color(170, 170, 170).getRGB();

        MessageIndicator messageIndicator = new MessageIndicator(
            gray,
            null,
            Text.empty(),
            "Nemesis"
        );
        ((IChatHud) mc.inGameHud.getChatHud()).addMessage(Text.of(CLIENT_PREFIX + message), messageIndicator, id);
        //ChatHud chatHud = mc.inGameHud.getChatHud();

        // メッセージ送信
        //chatHud.addMessage(Text.of(CLIENT_PREFIX + message), indicator, id);
    }
    
    //info
    public static void info(String message) {
        clientSendMessage(Formatting.GRAY + message);
    }
    //warning 
    public static void warning(String message) {
        clientSendMessage(Formatting.YELLOW + message);
    }
    //error
    public static void error(String message) {
        clientSendMessage(Formatting.RED + message);
    }
    //server send message 
    public static void serverSendMessage(PlayerEntity player, String message)
    {
        if (mc.player != null)
        {
            String reply = "/msg " + player.getName().getString() + " ";
            mc.player.networkHandler.sendChatMessage(reply + message);
        }
    }
}

/*
minecraft color formatting list 
AQUA
BLACK
BLUE
BOLD
DARK_AQUA
DARK_BLUE
DARK_GRAY
DARK_GREEN
DARK_PURPLE
DARK_RED
GOLD
GRAY
GREEN 
ITALIC
LIGHT_PURPLE
OBFUSCATED
RED
RESET 
STRIKETHROUGH
UNDERLINE
WHITE
YELLOW
*/