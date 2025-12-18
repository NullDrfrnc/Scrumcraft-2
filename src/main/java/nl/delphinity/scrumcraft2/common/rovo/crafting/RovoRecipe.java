package nl.delphinity.scrumcraft2.common.rovo.crafting;

import com.mojang.serialization.Codec;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipePattern;

import java.util.List;
import java.util.Map;

public record RovoRecipe(
        Map<Character, Ingredient> key,
        List<String> pattern
) {
    public static final Codec<RovoRecipe> CODEC =
            ShapedRecipePattern.Data.MAP_CODEC.codec()
                    .xmap(
                            data -> new RovoRecipe(data.key(), data.pattern()),
                            recipe -> new ShapedRecipePattern.Data(recipe.key(), recipe.pattern())
                    );

    public ShapedRecipePattern getRecipePattern() {
        return ShapedRecipePattern.of(key, pattern);
    }

    public boolean matches(CraftingInput input) {
        return getRecipePattern().matches(input);
    }

    public boolean exactMatches(CraftingInput input) {
        if (input.width() != getRecipePattern().width() || input.height() != getRecipePattern().height())
            return false;

        var ingredients = getRecipePattern().ingredients();

        for (int y = 0; y < getRecipePattern().height(); y++) {
            for (int x = 0; x < getRecipePattern().width(); x++) {
                int index = x + y * getRecipePattern().width();
                var optionalIngredient = ingredients.get(index);
                if (!Ingredient.testOptionalIngredient(optionalIngredient, input.getItem(x, y))) {
                    return false;
                }
            }
        }

        return true;
    }

}



