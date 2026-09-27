package nl.delphinity.scrumcraft2.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModDimensions {
    public static final ResourceKey<Level> AGARTHA = ResourceKey.create(Registries.DIMENSION, identifierOf("agartha_dim"));
}
