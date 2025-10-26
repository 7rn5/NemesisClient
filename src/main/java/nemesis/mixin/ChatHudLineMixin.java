package nemesis.mixin;

import nemesis.mixininterface.IChatHudLine;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChatHudLine.class)
public class ChatHudLineMixin implements IChatHudLine {
    @Unique private int nemesis$id;
    
    @Override
    public int nemesis$getId() {
        return nemesis$id;
    }

    @Override
    public void nemesis$setId(int id) {
        this.nemesis$id = id;
    }
    
    @Override
    public void nemesis$setIndicatorPath(String path) {}
    
    @Override
    public String nemesis$getIndicatorPath() {
        return null;
    }
    
    @Override
    public void nemesis$renderIndicator(DrawContext context, int x, int y) {}
}
