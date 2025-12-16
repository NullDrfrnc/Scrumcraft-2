package nl.delphinity.scrumcraft2.common.rovo.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.ShapedRecipePattern;


public class RovoRecipe {
    private ShapedRecipePattern pattern;
    public static final Codec<RovoRecipe> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ShapedRecipePattern.MAP_CODEC.fieldOf("pattern").forGetter(RovoRecipe::getPattern)
            ).apply(instance, RovoRecipe::new)
    );

    public RovoRecipe() {
    }

    public RovoRecipe(ShapedRecipePattern pattern) {
        this.pattern = pattern;
    }

    public boolean matches(CraftingInput input) {
        return this.pattern.matches(input);
    }

    public ShapedRecipePattern getPattern() {
        return pattern;
    }

    public RovoRecipe setPattern(ShapedRecipePattern pattern) {
        this.pattern = pattern;
        return this;
    }
}


