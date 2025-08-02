package nemesis.impl.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.minecraft.text.Text;

import static nemesis.NemesisClient.mc;
import static nemesis.NemesisClient.clickGuiScreen;

public class OpenGui {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                ClientCommandManager.literal("opengui")
                    .executes(context -> {
                        mc.inGameHud.getChatHud().addMessage(Text.literal("open gui..."));
                        if (mc == null || mc.mouse == null) {
                            mc.setScreen(clickGuiScreen);
                        }
                        return 1;
                    })
            );
        });
    }
}