package nl.delphinity.scrumcraft2.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;
import nl.delphinity.scrumcraft2.init.ModBlocks;
import nl.delphinity.scrumcraft2.init.ModItems;

import java.util.List;

public class ModelProvider extends FabricModelProvider {
    // Items with a plain 2D texture (textures/item/<name>.png)
    private static final List<Item> FLAT_ITEMS = List.of(
            ModItems.ULTIMATE_SCRUM_BALL,
            ModItems.SCRUM_MASTER_BALL,
            ModItems.AYRAN,
            ModItems.POTION_OF_TERRORISM,
            ModItems.AGARTHA_POTION,
            ModItems.NS_TRAIN,
            ModItems.WEAK_HEART,
            ModItems.GOLDEN_FISH,
            ModItems.BOWL_OF_CODE,
            ModItems.VERY_WHITE_BREW,
            ModItems.WORSTE_BOLUS,
            ModItems.EVIL_LINKED_IN,
            ModItems.LINKED_IN,
            ModItems.AGARTHA_LINKED_IN,
            ModItems.PULLREQUEST_DECLINED,
            ModItems.EXCEPTION,
            ModItems.CLASS_CAST_EXCEPTION,
            ModItems.CUP_OF_JAVA,
            ModItems.WAR_FILE,
            ModItems.TOMCAT_SPAWN_EGG,
            ModItems.HIBERNATE_SESSION,
            ModItems.LAZY_LOADED_SANDWICH,
            ModItems.N_PLUS_ONE_QUERY_SPAWN_EGG
    );

    // Tools and weapons, held in hand like a sword
    private static final List<Item> HANDHELD_ITEMS = List.of(
            ModItems.ABSTRACT_SINGLETON_PROXY_FACTORY_BEAN,
            ModItems.STRUTS
    );

    public ModelProvider(FabricPackOutput output) {
        super(output);
    }

    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/block-models">documentation</a>
     * @param blockModelGenerators blockModelGenerators
     */
    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.SCRUM_BLOCK);
        // Uses the hand made model in resources/models/block/christmastree.json
        blockModelGenerators.createNonTemplateModelBlock(ModBlocks.CHRISTMASTREE);
    }

    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/block-models">documentation</a>
     * @param itemModelGenerators itemModelGenerators
     */
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        FLAT_ITEMS.forEach(item -> itemModelGenerators.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
        HANDHELD_ITEMS.forEach(item -> itemModelGenerators.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM));
    }
}
