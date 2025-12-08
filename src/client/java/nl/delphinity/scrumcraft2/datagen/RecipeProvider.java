package nl.delphinity.scrumcraft2.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }
    
    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/recipes">documentation</a>
     *
     * @param registryLookup HolderLookup.Provider
     * @param exporter RecipeOutput
     * @return RecipeGenerator
     */
    @Override
    protected net.minecraft.data.recipes.@NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new net.minecraft.data.recipes.RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                // Recipes go here
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "RecipeProvider";
    }
}
