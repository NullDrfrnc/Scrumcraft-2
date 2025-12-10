package nl.delphinity.scrumcraft2.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import nl.delphinity.scrumcraft2.init.ModItems;
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
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
                // shapeless recipe for scrum ball
                shapeless(RecipeCategory.COMBAT, ModItems.SCRUM_BALL, 1)
                        .requires(Items.GOLD_INGOT)
                        .requires(Items.LAPIS_LAZULI)
                        .requires(Items.REDSTONE)
                        .requires(Items.EMERALD)
                        .unlockedBy(getHasName(Items.GOLD_INGOT), this.has(Items.GOLD_INGOT))
                        .save(exporter, "scrum_ball");
                // recipe for ultimate scrum ball
                shapeless(RecipeCategory.COMBAT, ModItems.ULTIMATE_SCRUM_BALL, 1)
                        .requires(ModItems.SCRUM_BALL)
                        .requires(ModItems.SCRUM_BALL)
                        .requires(ModItems.SCRUM_BALL)
                        .requires(ModItems.SCRUM_BALL)
                        .requires(ModItems.SCRUM_BALL)
                        .requires(ModItems.SCRUM_BALL)
                        .requires(ModItems.SCRUM_BALL)
                        .requires(ModItems.SCRUM_BALL)
                        .requires(ModItems.SCRUM_BALL)
                        .unlockedBy(getHasName(ModItems.SCRUM_BALL), this.has(ModItems.SCRUM_BALL))
                        .save(exporter, "ultimate_scrum_ball");
                shapeless(RecipeCategory.COMBAT, ModItems.SCRUM_MASTER_BALL, 1)
                        .requires(ModItems.ULTIMATE_SCRUM_BALL)
                        .requires(ModItems.ULTIMATE_SCRUM_BALL)
                        .requires(ModItems.ULTIMATE_SCRUM_BALL)
                        .requires(ModItems.ULTIMATE_SCRUM_BALL)
                        .requires(ModItems.ULTIMATE_SCRUM_BALL)
                        .requires(ModItems.ULTIMATE_SCRUM_BALL)
                        .requires(ModItems.ULTIMATE_SCRUM_BALL)
                        .requires(ModItems.ULTIMATE_SCRUM_BALL)
                        .requires(ModItems.ULTIMATE_SCRUM_BALL)
                        .unlockedBy(getHasName(ModItems.ULTIMATE_SCRUM_BALL), this.has(ModItems.ULTIMATE_SCRUM_BALL))
                        .save(exporter, "scrum_master_ball");
            };
        };
    }

    @Override
    public @NotNull String getName() {
        return "RecipeProvider";
    }
}
