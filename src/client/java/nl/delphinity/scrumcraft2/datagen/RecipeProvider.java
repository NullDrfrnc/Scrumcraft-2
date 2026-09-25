package nl.delphinity.scrumcraft2.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
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
     */
    @Override
    protected net.minecraft.data.recipes.@NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new net.minecraft.data.recipes.RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                // Scrum balls
                shapeless(RecipeCategory.COMBAT, ModItems.SCRUM_BALL, 1)
                        .requires(Items.GOLD_INGOT)
                        .requires(Items.LAPIS_LAZULI)
                        .requires(Items.REDSTONE)
                        .requires(Items.EMERALD)
                        .unlockedBy(getHasName(Items.GOLD_INGOT), this.has(Items.GOLD_INGOT))
                        .save(output, "scrum_ball");
                shapeless(RecipeCategory.COMBAT, ModItems.ULTIMATE_SCRUM_BALL, 1)
                        .requires(ModItems.SCRUM_BALL, 9)
                        .unlockedBy(getHasName(ModItems.SCRUM_BALL), this.has(ModItems.SCRUM_BALL))
                        .save(output, "ultimate_scrum_ball");
                shapeless(RecipeCategory.COMBAT, ModItems.SCRUM_MASTER_BALL, 1)
                        .requires(ModItems.ULTIMATE_SCRUM_BALL, 9)
                        .unlockedBy(getHasName(ModItems.ULTIMATE_SCRUM_BALL), this.has(ModItems.ULTIMATE_SCRUM_BALL))
                        .save(output, "scrum_master_ball");

                shapeless(RecipeCategory.MISC, ModItems.CATAMARAN, 1)
                        .requires(tag(ItemTags.BOATS), 9)
                        .unlockedBy("has_boats", this.has(ItemTags.BOATS))
                        .save(output, "catamaran");
                shapeless(RecipeCategory.MISC, ModItems.NS_TRAIN, 1)
                        .requires(Items.MINECART, 9)
                        .unlockedBy("has_minecarts", this.has(Items.MINECART))
                        .save(output, "ns_train");
                shapeless(RecipeCategory.MISC, ModItems.VERY_WHITE_BREW, 1)
                        .requires(ModItems.AGARTHA_POTION, 9)
                        .unlockedBy(getHasName(ModItems.AGARTHA_POTION), this.has(ModItems.AGARTHA_POTION))
                        .save(output, "very_white_brew");

                // Food and potions
                surrounded(ModItems.AGARTHA_POTION)
                        .define('#', Items.GOLD_INGOT)
                        .define('X', Items.DIAMOND_BLOCK)
                        .unlockedBy(getHasName(Items.GOLD_INGOT), this.has(Items.GOLD_INGOT))
                        .save(output, "agarta_potion");
                surrounded(ModItems.AYRAN)
                        .define('#', Items.SPIDER_EYE)
                        .define('X', Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(Items.SPIDER_EYE), this.has(Items.SPIDER_EYE))
                        .save(output, "ayran");
                surrounded(ModItems.POTION_OF_TERRORISM)
                        .define('#', Items.GUNPOWDER)
                        .define('X', Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(Items.GUNPOWDER), this.has(Items.GUNPOWDER))
                        .save(output, "potion_of_terrorism");
                surrounded(ModItems.GOLDEN_FISH)
                        .define('#', Items.GOLD_INGOT)
                        .define('X', ItemTags.FISHES)
                        .unlockedBy(getHasName(Items.GOLD_INGOT), this.has(ItemTags.FISHES))
                        .save(output, "golden_fish");
                surrounded(ModItems.WORSTE_BOLUS)
                        .define('#', Items.BREAD)
                        .define('X', ItemTags.MEAT)
                        .unlockedBy(getHasName(Items.BREAD), this.has(ItemTags.MEAT))
                        .save(output, "worste_bolus");
                shaped(RecipeCategory.MISC, ModItems.BOWL_OF_CODE, 1)
                        .define('G', Items.GOLD_INGOT)
                        .define('B', Items.BOWL)
                        .pattern("G")
                        .pattern("B")
                        .unlockedBy(getHasName(Items.GOLD_INGOT), this.has(Items.GOLD_INGOT))
                        .save(output, "bowl_of_code");

                // LinkedIn
                surrounded(ModItems.LINKED_IN)
                        .define('#', BlockItemTags.CHAINS.item())
                        .define('X', Items.LAPIS_LAZULI)
                        .unlockedBy(getHasName(Items.LAPIS_LAZULI), this.has(BlockItemTags.CHAINS.item()))
                        .save(output, "linked_in");
                surrounded(ModItems.AGARTHA_LINKED_IN)
                        .define('#', Items.GOLD_INGOT)
                        .define('X', ModItems.LINKED_IN)
                        .unlockedBy(getHasName(Items.LAPIS_LAZULI), this.has(BlockItemTags.CHAINS.item()))
                        .save(output, "agartha_linked_in");
                surrounded(ModItems.EVIL_LINKED_IN)
                        .define('#', Items.REDSTONE)
                        .define('X', ModItems.LINKED_IN)
                        .unlockedBy(getHasName(Items.LAPIS_LAZULI), this.has(BlockItemTags.CHAINS.item()))
                        .save(output, "evil_linked_in");

                // Other stuff
                shaped(RecipeCategory.MISC, ModItems.WEAK_HEART, 1)
                        .define('G', Items.GOLDEN_APPLE)
                        .define('H', Items.HEART_OF_THE_SEA)
                        .define('S', Items.SPIDER_EYE)
                        .pattern("SSS")
                        .pattern("SHS")
                        .pattern("SGS")
                        .unlockedBy(getHasName(Items.HEART_OF_THE_SEA), this.has(Items.HEART_OF_THE_SEA))
                        .save(output, "weak_heart");
                shaped(RecipeCategory.MISC, ModBlocks.SCRUM_BLOCK, 1)
                        .define('#', Items.GOLD_INGOT)
                        .define('S', ModItems.SCRUM_BALL)
                        .define('B', Items.GOLD_BLOCK)
                        .pattern("#B#")
                        .pattern("BSB")
                        .pattern("#B#")
                        .unlockedBy(getHasName(ModItems.SCRUM_BALL), this.has(ModItems.SCRUM_BALL))
                        .save(output, "scrum_block");
                surrounded(ModItems.PULLREQUEST_DECLINED)
                        .define('#', Items.REDSTONE)
                        .define('X', ModItems.VERY_WHITE_BREW)
                        .unlockedBy(getHasName(ModItems.VERY_WHITE_BREW), this.has(ModItems.VERY_WHITE_BREW))
                        .save(output, "pullrequest_declined");
                surrounded(ModItems.WEED_DUCKY)
                        .define('#', Items.SUGAR_CANE)
                        .define('X', ModItems.RUBBER_DUCKY)
                        .unlockedBy(getHasName(ModItems.RUBBER_DUCKY), this.has(ModItems.RUBBER_DUCKY))
                        .save(output, "weed_ducky");
                surrounded(ModItems.RUBBER_DUCKY)
                        .define('#', Items.GOLD_INGOT)
                        .define('X', ModItems.SCRUM_BALL)
                        .unlockedBy(getHasName(ModItems.SCRUM_BALL), this.has(ModItems.SCRUM_BALL))
                        .save(output, "rubber_ducky");

                // Java
                surrounded(ModItems.EXCEPTION)
                        .define('#', Items.GUNPOWDER)
                        .define('X', Items.PAPER)
                        .unlockedBy(getHasName(Items.GUNPOWDER), this.has(Items.GUNPOWDER))
                        .save(output, "exception");
                surrounded(ModItems.CLASS_CAST_EXCEPTION)
                        .define('#', Items.SLIME_BALL)
                        .define('X', ModItems.EXCEPTION)
                        .unlockedBy(getHasName(ModItems.EXCEPTION), this.has(ModItems.EXCEPTION))
                        .save(output, "class_cast_exception");
                surrounded(ModItems.CUP_OF_JAVA)
                        .define('#', Items.COCOA_BEANS)
                        .define('X', Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(Items.COCOA_BEANS), this.has(Items.COCOA_BEANS))
                        .save(output, "cup_of_java");
                surrounded(ModItems.ABSTRACT_SINGLETON_PROXY_FACTORY_BEAN)
                        .define('#', Items.BOOK)
                        .define('X', Items.DIAMOND_SWORD)
                        .unlockedBy(getHasName(Items.DIAMOND_SWORD), this.has(Items.DIAMOND_SWORD))
                        .save(output, "abstract_singleton_proxy_factory_bean");

                // Tomcat
                surrounded(ModItems.WAR_FILE)
                        .define('#', Items.PAPER)
                        .define('X', Items.IRON_SWORD)
                        .unlockedBy(getHasName(Items.IRON_SWORD), this.has(Items.IRON_SWORD))
                        .save(output, "war_file");

                // Hibernate
                surrounded(ModItems.HIBERNATE_SESSION)
                        .define('#', Items.SNOWBALL)
                        .define('X', Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(Items.SNOWBALL), this.has(Items.SNOWBALL))
                        .save(output, "hibernate_session");
                shapeless(RecipeCategory.FOOD, ModItems.LAZY_LOADED_SANDWICH, 1)
                        .requires(Items.BREAD)
                        .requires(Items.COOKED_PORKCHOP)
                        .requires(Items.CLOCK)
                        .unlockedBy(getHasName(Items.BREAD), this.has(Items.BREAD))
                        .save(output, "lazy_loaded_sandwich");

                // Struts, cobwebs because it's legacy
                surrounded(ModItems.STRUTS)
                        .define('#', Items.COBWEB)
                        .define('X', Items.IRON_PICKAXE)
                        .unlockedBy(getHasName(Items.IRON_PICKAXE), this.has(Items.IRON_PICKAXE))
                        .save(output, "struts");
            }

            // 8 items (#) around 1 item (X) in the middle
            private ShapedRecipeBuilder surrounded(ItemLike result) {
                return shaped(RecipeCategory.MISC, result, 1)
                        .pattern("###")
                        .pattern("#X#")
                        .pattern("###");
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "RecipeProvider";
    }
}
