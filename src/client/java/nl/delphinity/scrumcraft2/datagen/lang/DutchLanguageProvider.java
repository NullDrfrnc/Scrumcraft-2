package nl.delphinity.scrumcraft2.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import nl.delphinity.scrumcraft2.init.ModItems;

import java.util.concurrent.CompletableFuture;

public class DutchLanguageProvider extends AbstractLanguageProvider {
    public DutchLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "nl_nl", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        getExistingLangFile(translationBuilder);
        
        // Items
        translationBuilder.add(ModItems.RUBBER_DUCKY, "Badeend");
        translationBuilder.add(ModItems.WEED_DUCKY, "Wieteend");
        
        // ItemGroups
        
        // ItemTags
        
        // SoundEvents
        translationBuilder.add("sound.scrumcraft2.rubber_ducky_squeak", "Badeend kwaakt");
        translationBuilder.add("sound.scrumcraft2.rubber_ducky_throw", "Badeend vliegt");
    }
}
