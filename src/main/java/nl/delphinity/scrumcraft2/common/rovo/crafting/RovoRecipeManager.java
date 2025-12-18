package nl.delphinity.scrumcraft2.common.rovo.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import nl.delphinity.scrumcraft2.Scrumcraft2;
import nl.delphinity.scrumcraft2.init.ModRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class RovoRecipeManager extends SimplePreparableReloadListener<Map<Identifier, RovoRecipe>> {

    private static final FileToIdConverter RECIPE_LISTER = FileToIdConverter.json(ModRegistries.ROVO_RECIPE.getPath());
    private static Map<Identifier, RovoRecipe> loadedPatterns = Map.of();

    public RovoRecipeManager() {
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
        Identifier ident = Identifier.parse(name);

        if (!name.contains(":"))
            ident = identifierOf(name);
        return loadedPatterns.get(ident);
    }

    public static void sendRovoMessage(ServerPlayer sp) {
        sp.connection.send(
                new ClientboundSetTitleTextPacket(
                        Component.literal("[RovoAI] ")
                                .withColor(0x0052CC)
                )
        );
        sp.connection.send(
                new ClientboundSetSubtitleTextPacket(
                        Component.translatable("title.scrumcraft2.pullrequest")
                                .withStyle(ChatFormatting.WHITE)
                                .append(
                                        Component.translatable(
                                                "title.scrumcraft2.denied"
                                        ).withStyle(
                                                ChatFormatting.DARK_RED
                                        )
                                )
                )
        );
    }

    @Override
    protected @NotNull Map<Identifier, RovoRecipe> prepare(@NotNull ResourceManager manager, @NotNull ProfilerFiller profiler) {
        Map<Identifier, RovoRecipe> recipes = new HashMap<>();

        RECIPE_LISTER.listMatchingResources(manager).forEach((id, resource) -> {
            try (var reader = resource.openAsReader()) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

                // Parse key
                Map<Character, Ingredient> key = new HashMap<>();
                JsonObject keyObj = json.getAsJsonObject("key");
                for (var entry : keyObj.entrySet()) {
                    char symbol = entry.getKey().charAt(0);
                    Identifier itemId = Identifier.parse(entry.getValue().getAsString());
                    key.put(symbol, Ingredient.of(BuiltInRegistries.ITEM.getValue(itemId)));
                }

                // Parse pattern
                List<String> pattern = json.getAsJsonArray("pattern")
                        .asList()
                        .stream()
                        .map(JsonElement::getAsString)
                        .toList();

                // Clean up the id: remove ".json" and "rovo/" prefix
                String path = id.getPath();
                if (path.endsWith(".json")) path = path.substring(0, path.length() - 5); // remove .json
                if (path.startsWith("rovo/")) path = path.substring(5); // remove rovo/
                Identifier cleanedId = Identifier.fromNamespaceAndPath(id.getNamespace(), path);

                recipes.put(cleanedId, new RovoRecipe(key, pattern));
                Scrumcraft2.LOGGER.info("Loaded RovoRecipe {}", cleanedId);
            } catch (Exception e) {
                Scrumcraft2.LOGGER.error("Failed to load RovoRecipe {}", id, e);
            }
        });

        return recipes;
    }


    @Override
    protected void apply(@NotNull Map<Identifier, RovoRecipe> prepared, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        loadedPatterns = Map.copyOf(prepared);
        Scrumcraft2.LOGGER.info("Loaded {} RovoRecipes", loadedPatterns.size());
        Scrumcraft2.LOGGER.info(loadedPatterns.toString());
    }
}
