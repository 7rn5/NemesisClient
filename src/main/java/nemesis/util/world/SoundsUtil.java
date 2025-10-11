package nemesis.util.world;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class SoundsUtil {
    private static final SoundEvent guiClick = register("gui.click");
    
    public static SoundEvent register(String soundName) {
        Identifier id = Identifier.of("Nemesis", soundName);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}