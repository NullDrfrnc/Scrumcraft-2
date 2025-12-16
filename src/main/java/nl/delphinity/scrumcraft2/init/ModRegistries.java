package nl.delphinity.scrumcraft2.init;

import net.minecraft.resources.ResourceKey;

import net.minecraft.core.Registry;
import nl.delphinity.scrumcraft2.common.rovo.crafting.RovoRecipe;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModRegistries {

    public static final ResourceKey<Registry<RovoRecipe>> ROVO_RECIPE_REGISTRY = createRegistryKey("rovo");

    public static void init() {
    }

    private static <T> ResourceKey<net.minecraft.core.Registry<T>> createRegistryKey(String string) {
        return ResourceKey.createRegistryKey(identifierOf(string));
    }
}
