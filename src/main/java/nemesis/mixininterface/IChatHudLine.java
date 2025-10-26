package nemesis.mixininterface;

import net.minecraft.client.gui.DrawContext;

public interface IChatHudLine {
    void nemesis$setId(int id);
    int nemesis$getId();
    
    void nemesis$setIndicatorPath(String path);
    String nemesis$getIndicatorPath();
    
    void nemesis$renderIndicator(DrawContext context, int x, int y);
}
