package nl.delphinity.scrumcraft2.datagen.loottables;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import nl.delphinity.scrumcraft2.init.ModBlocks;
import nl.delphinity.scrumcraft2.init.ModItems;

import java.util.concurrent.CompletableFuture;

public class BlockLootTableProvider extends FabricBlockLootTableProvider {
    public BlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        // SCRUM BLOCK - LOOT TABLE
        add(ModBlocks.SCRUM_BLOCK, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.SCRUM_BALL)
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f))) // Drops 1 to 3 items
                        )
                        .add(LootItem.lootTableItem(ModItems.CATAMARAN)
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f))) // Always drops 1
                        )
                        .add(LootItem.lootTableItem(ModItems.WEAK_HEART)
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f))) // Always drops 1
                        )
                        .add(LootItem.lootTableItem(ModItems.NS_TRAIN)
                                .setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(64.0f))) // Drops a full stack
                        )
                        .add(LootItem.lootTableItem(ModItems.AGARTHA_POTION)
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0f))) // Always drops 2
                        )
                        .add(LootItem.lootTableItem(ModItems.POTION_OF_TERRORISM)
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(64.0f))) // Always drops a stack
                        )

        ));
    }

}

