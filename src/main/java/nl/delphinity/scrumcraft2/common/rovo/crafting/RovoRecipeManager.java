package nl.delphinity.scrumcraft2.common.rovo.crafting;

import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import nl.delphinity.scrumcraft2.Scrumcraft2;
import nl.delphinity.scrumcraft2.init.ModRegistries;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class RovoRecipeManager extends SimplePreparableReloadListener<Map<Identifier, RovoRecipe>>
        implements IdentifiableResourceReloadListener {

    private final HolderLookup.Provider registries;

    public RovoRecipeManager(HolderLookup.Provider provider) {
        this.registries = provider;
        Scrumcraft2.LOGGER.info("RovoRecipeManager Successfully initialised");
    }

    private static Map<Identifier, RovoRecipe> loadedPatterns = Map.of();

    @Override
    protected Map<Identifier, RovoRecipe> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        SortedMap<Identifier, RovoRecipe> patterns = new TreeMap<>();

        resourceManager.listResources("scrumcraft2/rovo", identifier -> identifier.getPath().endsWith(".json"))
                        .forEach((identifier, resource) -> Scrumcraft2.LOGGER.info("Found resource: {}", identifier));

        SimpleJsonResourceReloadListener.scanDirectory(
                resourceManager,
                ModRegistries.ROVO_RECIPE_REGISTRY,
                this.registries.createSerializationContext(JsonOps.INSTANCE),
                RovoRecipe.CODEC,
                patterns
        );
        return patterns;
    }

    @Override
    protected void apply(Map<Identifier, RovoRecipe> prepared, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        loadedPatterns = Map.copyOf(prepared);
        Scrumcraft2.LOGGER.info("Loaded {} RovoRecipes", loadedPatterns.size());
    }
    @Override
    public @NonNull Identifier getFabricId() {
        return identifierOf("rovo_recipe_manager");
    }
}
