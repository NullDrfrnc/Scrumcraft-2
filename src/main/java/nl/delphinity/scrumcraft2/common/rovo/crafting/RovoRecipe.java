package nl.delphinity.scrumcraft2.common.rovo.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;

public record RovoRecipe(
        Map<String, Ingredient> key,
        List<String> pattern
) {
    public static final Codec<RovoRecipe> CODEC = RecordCodecBuilder.create(instance -> 
            instance.group(
                    Codec.unboundedMap(Codec.STRING, Ingredient.CODEC).fieldOf("key").forGetter(RovoRecipe::key),
                    Codec.STRING.listOf().fieldOf("pattern").forGetter(RovoRecipe::pattern)
            ).apply(instance, RovoRecipe::new)
    );
}
