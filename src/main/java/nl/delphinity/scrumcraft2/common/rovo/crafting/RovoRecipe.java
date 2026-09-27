package nl.delphinity.scrumcraft2.common.rovo.crafting;

import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipePattern;

import java.util.List;
import java.util.Map;

public record RovoRecipe(
        Map<Character, Ingredient> key,
        List<String> pattern
) {
    public ShapedRecipePattern getRecipePattern() {
        return ShapedRecipePattern.of(key, pattern);
    }

    // Same as the normal shaped recipe check, but the mirrored version doesn't count
    public boolean exactMatches(CraftingInput input) {
        ShapedRecipePattern recipePattern = getRecipePattern();
        if (input.width() != recipePattern.width() || input.height() != recipePattern.height())
            return false;

        var ingredients = recipePattern.ingredients();

        for (int y = 0; y < recipePattern.height(); y++) {
            for (int x = 0; x < recipePattern.width(); x++) {
                int index = x + y * recipePattern.width();
                if (!Ingredient.testOptionalIngredient(ingredients.get(index), input.getItem(x, y))) {
                    return false;
                }
            }
        }

        return true;
    }
}
