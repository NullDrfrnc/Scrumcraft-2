package nl.delphinity.scrumcraft2.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import nl.delphinity.scrumcraft2.datagen.lang.DutchLanguageProvider;
import nl.delphinity.scrumcraft2.datagen.lang.EnglishLanguageProvider;
import nl.delphinity.scrumcraft2.datagen.loottables.BlockLootTableProvider;

public class Scrumcraft2DataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        
        // Languages
        pack.addProvider(EnglishLanguageProvider::new);
        pack.addProvider(DutchLanguageProvider::new);
        
        // Loottables
        pack.addProvider(BlockLootTableProvider::new);
        
        // Advancements
        pack.addProvider(AdvancementProvider::new);
    
        // Models
        pack.addProvider(ModelProvider::new);
        
        // Recipes
        pack.addProvider(RecipeProvider::new);
    }
}
