package nl.delphinity.scrumcraft2.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import nl.delphinity.scrumcraft2.init.ModItems;

import java.util.concurrent.CompletableFuture;

public class EnglishLanguageProvider extends AbstractLanguageProvider {


    public EnglishLanguageProvider(FabricDataOutput dataOutput,CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        getExistingLangFile(translationBuilder);
        
        // Items
        translationBuilder.add(ModItems.RUBBER_DUCKY, "Rubber ducky");
        
        // ItemGroups
        
        // ItemTags
        
        // Sounds (For subtitles)
        translationBuilder.add("sound.scrumcraft2.rubber_ducky_squeak", "Rubber ducky squeaks");
        translationBuilder.add("sound.scrumcraft2.rubber_ducky_throw", "Rubber ducky flies");
    }
}
