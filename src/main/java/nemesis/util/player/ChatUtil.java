package nemesis.util.player;

import nemesis.NemesisClient;
import nemesis.mixin.imixin.IChatHud;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import static nemesis.NemesisClient.mc;

import java.awt.Color;

public class ChatUtil {
    public static Text getNemesisPrefix() {
        return Text.empty()
            .setStyle(Style.EMPTY.withFormatting(Formatting.GRAY))
            .append("[")
            .append(Text.literal("Nemesis").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(new Color(255, 0, 0).getRGB()))))
            .append("] ");
    }
    
    public static void clientSendMessage(String message) {
        mc.inGameHud.getChatHud().addMessage(Text.literal(getNemesisPrefix() + message));
    }
    
    //info
    public static void info(String message) {
        clientSendMessage("§7" + message);
    }
    
    //warning 
    public static void warning(String message) {
        clientSendMessage("§e" + message);
    }
    
    //error
    public static void error(String message) {
        clientSendMessage("§7" + message);
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

//((IChatHud) mc.inGameHud.getChatHud()).meteor$add(message, id);

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