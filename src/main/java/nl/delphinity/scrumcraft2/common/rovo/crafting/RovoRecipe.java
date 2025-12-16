package nl.delphinity.scrumcraft2.common.rovo.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;
import java.util.Map;

public record RovoRecipe(
        List<String> pattern,
        Map<String, String> key
) {
    public static final Codec<RovoRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.listOf().fieldOf("pattern").forGetter(RovoRecipe::pattern),
            Codec.unboundedMap(Codec.STRING, Codec.STRING).fieldOf("key").forGetter(RovoRecipe::key)
            ).apply(instance, RovoRecipe::new)
    );
}
