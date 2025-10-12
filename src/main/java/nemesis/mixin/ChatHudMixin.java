package nemesis.mixin;

import nemesis.imixin.IChatHud;
import nemesis.imixin.IChatHudLine;
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
    @Shadow @Final private MinecraftClient client;
    @Shadow @Final private List<ChatHudLine.Visible> visibleMessages;
    @Shadow @Final private List<ChatHudLine> messages;
    
    @Unique
    private int nemesis$nextId;
    
    @Shadow
    public abstract void addMessage(Text message, @Nullable MessageSignatureData signature, @Nullable MessageIndicator indicator);
    
    @Shadow
    public abstract void addMessage(Text message);
    
    @Override
    public void nemesis$add(Text message, int id) {
        this.nemesis$nextId = id;
        addMessage(message);
        this.nemesis$nextId = 0;
    }
    
    @Inject(method = "addVisibleMessage", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V", shift = At.Shift.AFTER))
    private void onAddVisibleMessage(ChatHudLine message, CallbackInfo ci) {
        ((IChatHudLine)(Object) visibleMessages.getFirst()).nemesis$setId(nemesis$nextId);
    }
    
    @Inject(method = "addMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V", shift = At.Shift.AFTER))
    private void onAddMessage(ChatHudLine message, CallbackInfo ci) {
        ((IChatHudLine)(Object) messages.getFirst()).nemesis$setId(nemesis$nextId);
    }
}