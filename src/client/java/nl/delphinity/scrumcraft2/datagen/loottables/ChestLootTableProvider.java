package nl.delphinity.scrumcraft2.datagen.loottables;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ChestLootTableProvider extends SimpleFabricLootTableProvider {

    public ChestLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.CHEST);
    }

    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/loot-tables">documentation</a>
     * @param biConsumer biConsumer (very gay)
     */
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
            
    }
}
