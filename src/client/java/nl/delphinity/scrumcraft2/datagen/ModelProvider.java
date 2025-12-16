package nl.delphinity.scrumcraft2.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import nl.delphinity.scrumcraft2.init.ModBlocks;
import nl.delphinity.scrumcraft2.init.ModItems;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/block-models">documentation</a>
     * @param blockModelGenerators blockModelGenerators
     */
    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.SCRUM_BLOCK);
    }

    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/block-models">documentation</a>
     * @param itemModelGenerators itemModelGenerators
     */
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.createFlatItemModel(ModItems.ULTIMATE_SCRUM_BALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.createFlatItemModel(ModItems.SCRUM_MASTER_BALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.createFlatItemModel(ModItems.AYRAN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.createFlatItemModel(ModItems.POTION_OF_TERRORISM, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.createFlatItemModel(ModItems.AGARTHA_POTION, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.createFlatItemModel(ModItems.NS_TRAIN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.createFlatItemModel(ModItems.WEAK_HEART, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.createFlatItemModel(ModItems.GOLDEN_FISH, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.createFlatItemModel(ModItems.VERY_WHITE_BREW, ModelTemplates.FLAT_ITEM);
    }
}
