package nemesis.impl.module.movement;

import nemesis.event.bus.Subscribe;
import nemesis.event.TickEvent;
import nemesis.impl.module.Module;
import nemesis.settings.impl.BoolSetting;
import nemesis.mixin.KeyBindingMixin;
import nemesis.util.CheckUtil;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import static nemesis.NemesisClient.mc;
import static nemesis.NemesisClient.eventHandler;

public class NoSlow extends Module {
    private final BoolSetting invMove = addSetting(new BoolSetting("InventoryMove", true));
    
    public NoSlow() {
        super("NoSlow", "Disable/custom some slow item/block", Category.Movement);
        eventHandler.subscribe(this);
    }
    
    @Subscribe
    public void onTick(TickEvent event) {
        if (invMove.get() && CheckUtil.checkScreen()) {
            final long handle = mc.getWindow().getHandle();
            KeyBinding[] keys = new KeyBinding[]{mc.options.jumpKey, mc.options.forwardKey, mc.options.backKey, mc.options.rightKey, mc.options.leftKey};
            for (KeyBinding binding : keys) {
                binding.setPressed(InputUtil.isKeyPressed(handle, ((KeyBindingMixin) binding).getBoundKey().getCode()));
            }
        }
    }
}