package nemesis.mixininterface;

import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public interface IChatHud {
    void nemesis$add(Text text, int id);
    void nemesis$add(Text text, int id, @Nullable String indicatorPath);
    void nemesis$add(Text text);
    void nemesis$add(Text text, @Nullable String indicatorPath);
}