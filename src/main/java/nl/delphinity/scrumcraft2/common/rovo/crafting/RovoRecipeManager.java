package nl.delphinity.scrumcraft2.common.rovo.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.Ingredient;
import nl.delphinity.scrumcraft2.Scrumcraft2;
import nl.delphinity.scrumcraft2.init.ModRovo;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class RovoRecipeManager extends SimplePreparableReloadListener<Map<Identifier, RovoRecipe>> {

    private static final FileToIdConverter RECIPE_LISTER = FileToIdConverter.json(ModRovo.ROVO_RECIPE.getPath());
    private static Map<Identifier, RovoRecipe> loadedPatterns = Map.of();

    public RovoRecipeManager() {
        Scrumcraft2.LOGGER.info("RovoRecipeManager Successfully initialised");
    }

    public static RovoRecipe getRecipeWithName(String name) {
        Identifier ident = name.contains(":") ? Identifier.parse(name) : identifierOf(name);
        return loadedPatterns.get(ident);
    }

    public static void sendRovoMessage(ServerPlayer sp) {
        sp.connection.send(new ClientboundSetTitleTextPacket(
                Component.literal("[RovoAI] ").withColor(0x0052CC)
        ));
        sp.connection.send(new ClientboundSetSubtitleTextPacket(
                Component.translatable("title.scrumcraft2.pullrequest")
                        .withStyle(ChatFormatting.WHITE)
                        .append(Component.translatable("title.scrumcraft2.denied").withStyle(ChatFormatting.DARK_RED))
        ));
    }

    @Override
    protected @NotNull Map<Identifier, RovoRecipe> prepare(@NotNull ResourceManager manager, @NotNull ProfilerFiller profiler) {
        Map<Identifier, RovoRecipe> recipes = new HashMap<>();

        RECIPE_LISTER.listMatchingResources(manager).forEach((file, resource) -> {
            try (var reader = resource.openAsReader()) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

                // Parse key
                Map<Character, Ingredient> key = new HashMap<>();
                for (var entry : json.getAsJsonObject("key").entrySet()) {
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

                // rovo/book.json -> scrumcraft2:book
                Identifier id = RECIPE_LISTER.fileToId(file);
                recipes.put(id, new RovoRecipe(key, pattern));
                Scrumcraft2.LOGGER.info("Loaded RovoRecipe {}", id);
            } catch (Exception e) {
                Scrumcraft2.LOGGER.error("Failed to load RovoRecipe {}", file, e);
            }
        });

        return recipes;
    }

    @Override
    protected void apply(@NotNull Map<Identifier, RovoRecipe> prepared, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        loadedPatterns = Map.copyOf(prepared);
        Scrumcraft2.LOGGER.info("Loaded {} RovoRecipes", loadedPatterns.size());
    }
}
