package nl.delphinity.scrumcraft2.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementProvider extends FabricAdvancementProvider {
    protected AdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/advancements">documentation</a>
     * @param registryLookup registryLookup
     * @param consumer consumer
     */
    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        
    }
}
