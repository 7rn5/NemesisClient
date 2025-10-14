package nemesis.util.player;

import nemesis.NemesisClient;
import nemesis.imixin.IChatHud;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.text.MutableText;
import net.minecraft.text.TextColor;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;

import nemesis.impl.module.client.Notification;
import static nemesis.NemesisClient.moduleManager;
import static nemesis.NemesisClient.mc;

import java.awt.Color;

public class ChatUtil {
    private static Notification getNotificationModule() {
        return moduleManager.get(Notification.class);
    }
    
    public static Text getNemesisPrefix() {
        return Text.empty()
            .append(Text.literal("[").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(getNotificationModule().bracketColor.get().getRGB()))))
            .append(Text.literal("Nemesis").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(getNotificationModule().watermarkColor.get().getRGB()))))
            .append(Text.literal("] ").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(getNotificationModule().bracketColor.get().getRGB()))));
    }
    
    public static void clientSendMessage(String text) {
        if (mc.world == null) return;
        Text message = Text.empty()
            .append(getNemesisPrefix())
            .append(Text.literal(text));
        
        ((IChatHud) mc.inGameHud.getChatHud()).nemesis$add(message, 0);
    }
    
    public static void clientSendMessage(String text, Color color) {
        if (mc.world == null) return;
        Text message = Text.empty()
            .append(getNemesisPrefix())
            .append(Text.literal(text).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(color.getRGB()))));
        
        ((IChatHud) mc.inGameHud.getChatHud()).nemesis$add(message, 0);
    }
    
    public static void clientSendMessage(String text, int id) {
        if (mc.world == null) return;
        Text message = Text.empty()
            .append(getNemesisPrefix())
            .append(Text.literal(text));
        
        ((IChatHud) mc.inGameHud.getChatHud()).nemesis$add(message, id);
    }
    
    public static void clientSendMessage(String text, int id, Color color) {
        if (mc.world == null) return;
        Text message = Text.empty()
            .append(getNemesisPrefix())
            .append(Text.literal(text).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(color.getRGB()))));
        
        ((IChatHud) mc.inGameHud.getChatHud()).nemesis$add(message, id);
    }
    
    //info
    public static void info(String message, int id) {
        clientSendMessage(message, id);
    }
    
    public static void info(String message, int id, Color color) {
        clientSendMessage(message, id, color);
    }
    
    //warning
    public static void warning(String message, int id) {
        clientSendMessage("§e[!]" + message, id);
    }
    
    public static void warning(String message, int id, Color color) {
        clientSendMessage("§e[!]" + message, id, color);
    }
    
    //error
    public static void error(String message, int id) {
        clientSendMessage("§c[☓]" + message, id);
    }
    
    public static void error(String message, int id, Color color) {
        clientSendMessage("§c[☓]" + message, id, color);
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