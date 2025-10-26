package nemesis.mixin;

import nemesis.mixininterface.IChatHudLine;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChatHudLine.Visible.class)
public class ChatHudLineVisibleMixin implements IChatHudLine {
    @Unique private int nemesis$id;
    @Unique private String nemesis$indicatorPath;

    @Override
    public void nemesis$setId(int id) {
        this.nemesis$id = id;
    }

    @Override
    public int nemesis$getId() {
        return nemesis$id;
    }

    @Override
    public void nemesis$setIndicatorPath(String path) {
        this.nemesis$indicatorPath = path;
    }

    @Override
    public String nemesis$getIndicatorPath() {
        return this.nemesis$indicatorPath;
    }

    @Unique
    public void nemesis$renderIndicator(DrawContext context, int x, int y) {
        if (nemesis$indicatorPath == null || nemesis$indicatorPath.isEmpty()) return;

        Identifier indicator = Identifier.of("nemesis", "image/" + nemesis$indicatorPath);
        context.drawTexture(RenderLayer::getGuiTextured, indicator, x - 9, y, 0.0F, 0.0F, 9, 9, 128, 128);
    }
}