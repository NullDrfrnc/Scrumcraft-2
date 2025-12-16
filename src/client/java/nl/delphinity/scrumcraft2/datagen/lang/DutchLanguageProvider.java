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
        translationBuilder.add(ModItems.WEAK_HEART, "Verzwakt Hart");
        translationBuilder.add(ModItems.NS_TRAIN, "NS Trein");
        translationBuilder.add(ModItems.SCRUM_BALL, "Scrum Bal");
        translationBuilder.add(ModItems.ULTIMATE_SCRUM_BALL, "Ultieme Scrum Bal");
        translationBuilder.add(ModItems.SCRUM_MASTER_BALL, "Scrum Meester Bal");
        translationBuilder.add(ModItems.CATAMARAN, "Catamaran, het super agile bootje van Scrum");
        
        // ItemGroups
        // don't have to do anythink here :)
        
        // ItemTags
        
        // SoundEvents
        translationBuilder.add("sound.scrumcraft2.rubber_ducky_squeak", "Badeend kwaakt");
        translationBuilder.add("sound.scrumcraft2.rubber_ducky_throw", "Badeend vliegt");

        // death messages
        translationBuilder.add("death.attack.weak_heart", "%1$s is overleden door deceptie...");

    }
}
