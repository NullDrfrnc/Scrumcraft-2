package nl.delphinity.scrumcraft2.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class EnglishLanguageProvider extends AbstractLanguageProvider {


    public EnglishLanguageProvider(FabricDataOutput dataOutput,CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        getExistingLangFile(translationBuilder);
        
        // Items
        
        // ItemGroups
        
        // ItemTags
    }
}
