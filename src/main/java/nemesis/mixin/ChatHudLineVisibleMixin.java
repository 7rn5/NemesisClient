package nemesis.mixin;

import nemesis.mixininterface.IChatHudLine;
import net.minecraft.client.gui.hud.ChatHudLine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChatHudLine.Visible.class)
public class ChatHudLineVisibleMixin implements IChatHudLine {
    @Unique
    private int nemesis$id;
    
    @Override
    public void nemesis$setId(int id) {
        this.nemesis$id = id;
    }
    
    @Override
    public int nemesis$getId() {
        return nemesis$id;
    }
}