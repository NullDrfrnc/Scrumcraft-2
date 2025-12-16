package nl.delphinity.scrumcraft2.common.rovo.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record RovoRecipe(
        Map<String, Ingredient> key,
        ShapedRecipePattern pattern
) {
    public static final Codec<RovoRecipe> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.unboundedMap(Codec.STRING, Ingredient.CODEC).fieldOf("key").forGetter(RovoRecipe::key),
                    ShapedRecipePattern.MAP_CODEC.forGetter(shapedRecipe -> shapedRecipe.pattern)
            ).apply(instance, RovoRecipe::new)
    );

    public boolean matches(CraftingInput craftingInput) {
        return this.pattern.matches(craftingInput);
    }
}
