package nemesis.mixin;

import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
	@ModifyConstant(method = "<init>", constant = @Constant(intValue = 110))
    private int maxFovValueHook(int constant) {
        return 160;
    }
}