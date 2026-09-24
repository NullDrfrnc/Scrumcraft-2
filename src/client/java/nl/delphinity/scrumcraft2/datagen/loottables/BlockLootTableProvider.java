package nl.delphinity.scrumcraft2.datagen.loottables;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import nl.delphinity.scrumcraft2.init.ModBlocks;
import nl.delphinity.scrumcraft2.init.ModItems;

import java.util.concurrent.CompletableFuture;

public class BlockLootTableProvider extends FabricBlockLootSubProvider {
    public BlockLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        // SCRUM BLOCK - LOOT TABLE
        add(ModBlocks.SCRUM_BLOCK, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.SCRUM_BALL)
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))) // Drops 1 to 3 items
                        )
                        .add(LootItem.lootTableItem(ModItems.CATAMARAN)
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))) // Always drops 1
                        )
                        .add(LootItem.lootTableItem(ModItems.WEAK_HEART)
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))) // Always drops 1
                        )
                        .add(LootItem.lootTableItem(ModItems.NS_TRAIN)
                                .setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(64))) // Drops a full stack
                        )
                        .add(LootItem.lootTableItem(ModItems.AGARTHA_POTION)
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(2))) // Always drops 2
                        )
                        .add(LootItem.lootTableItem(ModItems.POTION_OF_TERRORISM)
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(64))) // Always drops a stack
                        )

        ));
    }

}

