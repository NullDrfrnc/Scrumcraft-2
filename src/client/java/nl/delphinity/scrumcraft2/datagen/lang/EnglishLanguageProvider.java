package nl.delphinity.scrumcraft2.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModBlocks;
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
        translationBuilder.add(ModItems.WEED_DUCKY, "Weed ducky");
        translationBuilder.add(ModItems.SCRUM_BALL, "Scrum Ball");
        translationBuilder.add(ModItems.WEAK_HEART, "Weak Heart");
        translationBuilder.add(ModItems.NS_TRAIN, "NS Train");
        translationBuilder.add(ModItems.ULTIMATE_SCRUM_BALL, "Ultimate Scrum Ball");
        translationBuilder.add(ModItems.SCRUM_MASTER_BALL, "Scrum Master Ball");
        translationBuilder.add(ModItems.CATAMARAN, "Catamaran");
        translationBuilder.add(ModItems.AYRAN, "Ayran");
        translationBuilder.add(ModItems.POTION_OF_TERRORISM, "Potion of Terrorism");
        translationBuilder.add(ModItems.AGARTHA_POTION, "Agartha Potion");
        translationBuilder.add(ModBlocks.SCRUM_BLOCK, "Scrum Block");
        translationBuilder.add(ModItems.GOLDEN_FISH, "Golden Fish");
        translationBuilder.add(ModItems.VERY_WHITE_BREW, "Very White Brew");
        translationBuilder.add(ModItems.WORSTE_BOLUS, "Sausage Bolus");
        translationBuilder.add(ModEntityTypes.EVIL_SNOW_GOLEM, "Evil Snow Golem");
        translationBuilder.add(ModItems.EVIL_LINKED_IN, "Evil LinkedIn");
        translationBuilder.add(ModItems.LINKED_IN, "LinkedIn");
        translationBuilder.add(ModItems.AGARTHA_LINKED_IN, "Agartha LinkedIn");

        // ItemGroups
        
        // ItemTags
        
        // Sounds (For subtitles)
        translationBuilder.add("sound.scrumcraft2.sickseven", "SIIIX SEVEEEEN");
        translationBuilder.add("sound.scrumcraft2.rubber_ducky_squeak", "Rubber ducky squeaks");
        translationBuilder.add("sound.scrumcraft2.rubber_ducky_throw", "Rubber ducky flies");

        // death messages
        translationBuilder.add("death.attack.weak_heart", "%1$s died from deception...");

        // effects
        translationBuilder.add("effect.scrumcraft2.eclipse_user", "Eclipse User");

    }
}
