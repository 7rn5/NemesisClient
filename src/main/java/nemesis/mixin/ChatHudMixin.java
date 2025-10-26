/*package nemesis.mixin;

import nemesis.mixininterface.IChatHud;
import nemesis.mixininterface.IChatHudLine;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin implements IChatHud {
    @Shadow @Final private MinecraftClient client;
    
    @Shadow @Final private List<ChatHudLine.Visible> visibleMessages;
    
    @Shadow
    public abstract void addMessage(Text message);
    
    @Unique
    private void nemesis$setIndicatorOnLastLine(@Nullable String indicatorPath) {
        if (indicatorPath == null || indicatorPath.isEmpty()) return;
        if (visibleMessages.isEmpty()) return;

        ChatHudLine.Visible last = visibleMessages.get(visibleMessages.size() - 1);
        if (last instanceof IChatHudLine line) {
            line.nemesis$setIndicatorPath(indicatorPath);
        }
    }
    
    @Override
    public void nemesis$add(Text message) {
        this.addMessage(message);
    }

    @Override
    public void nemesis$add(Text message, @Nullable String indicatorPath) {
        this.addMessage(message);
        nemesis$setIndicatorOnLastLine(indicatorPath);
    }
}*/

package nemesis.mixin;

import nemesis.mixininterface.IChatHud;
import nemesis.mixininterface.IChatHudLine;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin implements IChatHud {
    @Shadow @Final private MinecraftClient client;
    
    @Shadow @Final private List<ChatHudLine.Visible> visibleMessages;
    
    @Shadow
    public abstract void addMessage(Text message);
    
    @Unique
    private void nemesis$setPropertiesOnLastLine(@Nullable String indicatorPath, int id) {
        if (visibleMessages.isEmpty()) return;
        ChatHudLine.Visible last = visibleMessages.get(visibleMessages.size() - 1);
        
        if ((Object) last instanceof IChatHudLine line) {
            line.nemesis$setId(id);
            if (indicatorPath != null && !indicatorPath.isEmpty()) {
                line.nemesis$setIndicatorPath(indicatorPath);
            }
        }
    }
    
    @Override
    public void nemesis$add(Text message, int id) {
        this.addMessage(message);
        nemesis$setPropertiesOnLastLine(null, id);
    }
    
    @Override
    public void nemesis$add(Text message, int id, @Nullable String indicatorPath) {
        this.addMessage(message);
        nemesis$setPropertiesOnLastLine(indicatorPath, id);
    }
    
    @Unique
    private void nemesis$setIndicatorOnLastLine(@Nullable String indicatorPath) {
        if (indicatorPath == null || indicatorPath.isEmpty()) return;
        if (visibleMessages.isEmpty()) return;
        
        ChatHudLine.Visible last = visibleMessages.get(visibleMessages.size() - 1);
        if ((Object) last instanceof IChatHudLine line) {
            line.nemesis$setIndicatorPath(indicatorPath);
        }
    }
    
    @Override
    public void nemesis$add(Text message) {
        this.addMessage(message);
    }
    
    @Override
    public void nemesis$add(Text message, @Nullable String indicatorPath) {
        this.addMessage(message);
        nemesis$setIndicatorOnLastLine(indicatorPath);
    }
}
