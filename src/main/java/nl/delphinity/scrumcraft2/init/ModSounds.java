package nl.delphinity.scrumcraft2.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;


public class ModSounds {
    public static final SoundEvent RUBBER_DUCKY_SQUEAK = registerSound("rubber_ducky_squeak");
    public static final SoundEvent RUBBER_DUCKY_THROW = registerSound("rubber_ducky_throw");

    public static void init() {}
    
    private static SoundEvent registerSound(String id) {
        ResourceLocation identifier = identifierOf(id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }
}
