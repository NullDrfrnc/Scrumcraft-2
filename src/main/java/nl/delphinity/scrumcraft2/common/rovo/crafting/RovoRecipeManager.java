package nl.delphinity.scrumcraft2.common.rovo.crafting;

import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Recipe;
import nl.delphinity.scrumcraft2.Scrumcraft2;
import nl.delphinity.scrumcraft2.init.ModRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.util.*;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class RovoRecipeManager extends SimplePreparableReloadListener<Map<Identifier, RovoRecipe>> {

    private static final FileToIdConverter RECIPE_LISTER = FileToIdConverter.json(ModRegistries.ROVO_RECIPE.getPath());
    private static Map<Identifier, RovoRecipe> loadedPatterns = Map.of();
    private final HolderLookup.Provider registries;

    public RovoRecipeManager(HolderLookup.Provider provider) {
        this.registries = provider;
        Scrumcraft2.LOGGER.info("RovoRecipeManager Successfully initialised");
    }

    @Nullable
    public static RovoRecipe getRecipeFor(CraftingInput input) {
        for (RovoRecipe recipe : loadedPatterns.values()) {
            if (recipe.matches(input)) {
                return recipe;
            }
        }
        return null;
    }

    public static RovoRecipe getRecipeWithName(String name) {
        return loadedPatterns.get(identifierOf(name));
    }

    @Override
    protected @NotNull Map<Identifier, RovoRecipe> prepare(@NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        SortedMap<Identifier, RovoRecipe> patterns = new TreeMap<>();

        SimpleJsonResourceReloadListener.scanDirectory(
                resourceManager,
                RECIPE_LISTER,
                this.registries.createSerializationContext(JsonOps.INSTANCE),
                RovoRecipe.CODEC,
                patterns
        );
        return patterns;
    }

    @Override
    protected void apply(@NotNull Map<Identifier, RovoRecipe> prepared, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        loadedPatterns = Map.copyOf(prepared);
        Scrumcraft2.LOGGER.info("Loaded {} RovoRecipes", loadedPatterns.size());
        Scrumcraft2.LOGGER.info(loadedPatterns.toString());
    }
}
