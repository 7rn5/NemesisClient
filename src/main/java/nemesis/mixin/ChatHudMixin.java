package nemesis.mixin;

import nemesis.mixininterface.IChatHud;
import nemesis.mixininterface.IChatHudLine;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin implements IChatHud {
    @Shadow private List<ChatHudLine> messages;
    @Shadow private List<ChatHudLine.Visible> visibleMessages;
    @Shadow private MinecraftClient client;
    
    private int nemesis$id = 0;
    
    @Shadow
    public abstract void addMessage(Text message);

    @Override
    public void nemesis$add(Text message, int id) {
        this.nemesis$id = id;
        
        visibleMessages.removeIf(msg -> ((IChatHudLine)(Object) msg).nemesis$getId() == id && id != 0);
        messages.removeIf(msg -> ((IChatHudLine)(Object) msg).nemesis$getId() == id && id != 0);
        
        addMessage(message);
        this.nemesis$id = 0;
    }

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("TAIL"))
    private void onAddMessage(Text message, CallbackInfo ci) {
        if (nemesis$id == 0) return;
        if (!messages.isEmpty()) ((IChatHudLine)(Object) messages.getFirst()).nemesis$setId(nemesis$id);
        if (!visibleMessages.isEmpty()) ((IChatHudLine)(Object) visibleMessages.getFirst()).nemesis$setId(nemesis$id);
    }
}