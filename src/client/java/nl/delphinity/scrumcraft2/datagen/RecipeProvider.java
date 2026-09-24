package nl.delphinity.scrumcraft2.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.Items;
import nl.delphinity.scrumcraft2.init.ModBlocks;
import nl.delphinity.scrumcraft2.init.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }
    
    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/recipes">documentation</a>
     *
     * @param registryLookup HolderLookup.Provider
     * @param recipes recipe output
     * @param advancements recipe advancement output
     * @return RecipeGenerator
     */
    @Override
    protected net.minecraft.data.recipes.@NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new net.minecraft.data.recipes.RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                // Recipes go here
                // shapeless rovo_recipe for scrum ball
                shapeless(RecipeCategory.COMBAT, ModItems.SCRUM_BALL, 1)
                        .requires(Items.GOLD_INGOT)
                        .requires(Items.LAPIS_LAZULI)
                        .requires(Items.REDSTONE)
                        .requires(Items.EMERALD)
                        .unlockedBy(getHasName(Items.GOLD_INGOT), this.has(Items.GOLD_INGOT))
                        .save(output, "scrum_ball");
                // rovo_recipe for ultimate scrum ball
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
                        .save(output, "ultimate_scrum_ball");
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
                        .save(output, "scrum_master_ball");
                shapeless(RecipeCategory.MISC, ModItems.CATAMARAN, 1)
                        .requires(ItemTags.BOATS)
                        .requires(ItemTags.BOATS)
                        .requires(ItemTags.BOATS)
                        .requires(ItemTags.BOATS)
                        .requires(ItemTags.BOATS)
                        .requires(ItemTags.BOATS)
                        .requires(ItemTags.BOATS)
                        .requires(ItemTags.BOATS)
                        .requires(ItemTags.BOATS)
                        .unlockedBy("has_boats", this.has(ItemTags.BOATS))
                        .save(output, "catamaran");
                shapeless(RecipeCategory.MISC, ModItems.NS_TRAIN, 1)
                        .requires(Items.MINECART)
                        .requires(Items.MINECART)
                        .requires(Items.MINECART)
                        .requires(Items.MINECART)
                        .requires(Items.MINECART)
                        .requires(Items.MINECART)
                        .requires(Items.MINECART)
                        .requires(Items.MINECART)
                        .requires(Items.MINECART)
                        .unlockedBy("has_minecarts", this.has(Items.MINECART))
                        .save(output, "ns_train");
                shaped(RecipeCategory.MISC, ModItems.AGARTHA_POTION, 1)
                        .define('#', Items.GOLD_INGOT)
                        .define('D', Items.DIAMOND_BLOCK)
                        .pattern("###")
                        .pattern("#D#")
                        .pattern("###")
                        .unlockedBy(getHasName(Items.GOLD_INGOT), this.has(Items.GOLD_INGOT))
                        .save(output, "agarta_potion");
                shaped(RecipeCategory.MISC, ModItems.AYRAN, 1)
                        .define('#', Items.GLASS_BOTTLE)
                        .define('S', Items.SPIDER_EYE)
                        .pattern("SSS")
                        .pattern("S#S")
                        .pattern("SSS")
                        .unlockedBy(getHasName(Items.SPIDER_EYE), this.has(Items.SPIDER_EYE))
                        .save(output, "ayran");
                shaped(RecipeCategory.MISC, ModItems.POTION_OF_TERRORISM, 1)
                        .define('T', Items.GUNPOWDER)
                        .define('B', Items.GLASS_BOTTLE)
                        .pattern("TTT")
                        .pattern("TBT")
                        .pattern("TTT")
                        .unlockedBy(getHasName(Items.GUNPOWDER), this.has(Items.GUNPOWDER))
                        .save(output, "potion_of_terrorism");
                shaped(RecipeCategory.MISC, ModItems.WEAK_HEART, 1)
                        .define('G', Items.GOLDEN_APPLE)
                        .define('H', Items.HEART_OF_THE_SEA)
                        .define('S', Items.SPIDER_EYE)
                        .pattern("SSS")
                        .pattern("SHS")
                        .pattern("SGS")
                        .unlockedBy(getHasName(Items.HEART_OF_THE_SEA), this.has(Items.HEART_OF_THE_SEA))
                        .save(output, "weak_heart");
                shaped(RecipeCategory.MISC, ModItems.GOLDEN_FISH, 1)
                        .define('F', ItemTags.FISHES)
                        .define('G', Items.GOLD_INGOT)
                        .pattern("GGG")
                        .pattern("GFG")
                        .pattern("GGG")
                        .unlockedBy(getHasName(Items.GOLD_INGOT), this.has(ItemTags.FISHES))
                        .save(output, "golden_fish");

                shaped(RecipeCategory.MISC, ModItems.WORSTE_BOLUS, 1)
                        .define('M', ItemTags.MEAT)
                        .define('B', Items.BREAD)
                        .pattern("BBB")
                        .pattern("BMB")
                        .pattern("BBB")
                        .unlockedBy(getHasName(Items.BREAD), this.has(ItemTags.MEAT))
                        .save(output, "worste_bolus");

                shaped(RecipeCategory.MISC, ModBlocks.SCRUM_BLOCK, 1)
                        .define('#', Items.GOLD_INGOT)
                        .define('S', ModItems.SCRUM_BALL)
                        .define('B', Items.GOLD_BLOCK)
                        .pattern("#B#")
                        .pattern("BSB")
                        .pattern("#B#")
                        .unlockedBy(getHasName(ModItems.SCRUM_BALL), this.has(ModItems.SCRUM_BALL))
                        .save(output, "scrum_block");

                shaped(RecipeCategory.MISC, ModItems.LINKED_IN, 1)
                        .define('C', BlockItemTags.CHAINS.item())
                        .define('L', Items.LAPIS_LAZULI)
                        .pattern("CCC")
                        .pattern("CLC")
                        .pattern("CCC")
                        .unlockedBy(getHasName(Items.LAPIS_LAZULI), this.has(BlockItemTags.CHAINS.item()))
                        .save(output, "linked_in");

                shaped(RecipeCategory.MISC, ModItems.AGARTHA_LINKED_IN, 1)
                        .define('C', Items.GOLD_INGOT)
                        .define('L', ModItems.LINKED_IN)
                        .pattern("CCC")
                        .pattern("CLC")
                        .pattern("CCC")
                        .unlockedBy(getHasName(Items.LAPIS_LAZULI), this.has(BlockItemTags.CHAINS.item()))
                        .save(output, "agartha_linked_in");

                shaped(RecipeCategory.MISC, ModItems.EVIL_LINKED_IN, 1)
                        .define('C', Items.REDSTONE)
                        .define('L', ModItems.LINKED_IN)
                        .pattern("CCC")
                        .pattern("CLC")
                        .pattern("CCC")
                        .unlockedBy(getHasName(Items.LAPIS_LAZULI), this.has(BlockItemTags.CHAINS.item()))
                        .save(output, "evil_linked_in");

                shapeless(RecipeCategory.MISC, ModItems.VERY_WHITE_BREW, 1)
                        .requires(ModItems.AGARTHA_POTION)
                        .requires(ModItems.AGARTHA_POTION)
                        .requires(ModItems.AGARTHA_POTION)
                        .requires(ModItems.AGARTHA_POTION)
                        .requires(ModItems.AGARTHA_POTION)
                        .requires(ModItems.AGARTHA_POTION)
                        .requires(ModItems.AGARTHA_POTION)
                        .requires(ModItems.AGARTHA_POTION)
                        .requires(ModItems.AGARTHA_POTION)
                        .unlockedBy(getHasName(ModItems.AGARTHA_POTION), this.has(ModItems.AGARTHA_POTION))
                        .save(output, "very_white_brew");

                shaped(RecipeCategory.MISC, ModItems.BOWL_OF_CODE, 1)
                        .define('G', Items.GOLD_INGOT)
                        .define('B', Items.BOWL)
                        .pattern("G")
                        .pattern("B")
                        .unlockedBy(getHasName(Items.GOLD_INGOT), this.has(Items.GOLD_INGOT))
                        .save(output, "bowl_of_code");

                shaped(RecipeCategory.MISC, ModItems.PULLREQUEST_DECLINED, 1)
                        .define('R', Items.REDSTONE)
                        .define('V', ModItems.VERY_WHITE_BREW)
                        .pattern("RRR")
                        .pattern("RVR")
                        .pattern("RRR")
                        .unlockedBy(getHasName(ModItems.VERY_WHITE_BREW), this.has(ModItems.VERY_WHITE_BREW))
                        .save(output, "pullrequest_declined");

                shaped(RecipeCategory.MISC, ModItems.WEED_DUCKY, 1)
                        .define('D',ModItems.RUBBER_DUCKY)
                        .define('S', Items.SUGAR_CANE)
                        .pattern("SSS")
                        .pattern("SDS")
                        .pattern("SSS")
                        .unlockedBy(getHasName(ModItems.RUBBER_DUCKY), this.has(ModItems.RUBBER_DUCKY))
                        .save(output, "weed_ducky");

                shaped(RecipeCategory.MISC, ModItems.RUBBER_DUCKY, 1)
                        .define('G', Items.GOLD_INGOT)
                        .define('S', ModItems.SCRUM_BALL)
                        .pattern("GGG")
                        .pattern("GSG")
                        .pattern("GGG")
                        .unlockedBy(getHasName(ModItems.SCRUM_BALL), this.has(ModItems.SCRUM_BALL))
                        .save(output, "rubber_ducky");
            };
        };
    }

    @Override
    public @NotNull String getName() {
        return "RecipeProvider";
    }
}
