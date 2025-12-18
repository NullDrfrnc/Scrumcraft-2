package nl.delphinity.scrumcraft2.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModBlocks;
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
        translationBuilder.add(ModItems.WEED_DUCKY, "Wieteend");
        translationBuilder.add(ModItems.GOLDEN_FISH, "Gouden Vis");
        translationBuilder.add(ModItems.AYRAN, "Heerlijke Zoute Kwark");
        translationBuilder.add(ModItems.POTION_OF_TERRORISM, "Brouwsel Van Terrorisme");
        translationBuilder.add(ModItems.AGARTHA_POTION, "Agartha Brouwsel");
        translationBuilder.add(ModItems.WORSTE_BOLUS, "worste Bolus");
        translationBuilder.add(ModEntityTypes.EVIL_SNOW_GOLEM, "Slechtaardige sneeuwman");
        translationBuilder.add(ModEntityTypes.EVIL_SQUID, "Slechtaardige inktvis");
        translationBuilder.add(ModBlocks.SCRUM_BLOCK, "Scrum Blok");
        translationBuilder.add(ModBlocks.CHRISTMASTREE, "Kerstboom");
        translationBuilder.add(ModItems.VERY_WHITE_BREW, "Zeer Wit Brouwsel");
        translationBuilder.add(ModItems.EVIL_LINKED_IN, "Kwaadaardige GekoppeldIn");
        translationBuilder.add(ModItems.LINKED_IN, "GekoppeldIn");
        translationBuilder.add(ModItems.AGARTHA_LINKED_IN, "Agartha GekoppeldIn");
        translationBuilder.add(ModItems.BOWL_OF_CODE, "Kom van Code");
        translationBuilder.add(ModItems.PULLREQUEST_DECLINED, "Trekverzoek, Afgekeurd");




        // ItemGroups
        // don't have to do anythink here :)

        // ItemTags

        // SoundEvents
        translationBuilder.add("sound.scrumcraft2.sickseven", "ZES ZEVEEEEN");
        translationBuilder.add("sound.scrumcraft2.rubber_ducky_squeak", "Badeend kwaakt");
        translationBuilder.add("sound.scrumcraft2.rubber_ducky_throw", "Badeend vliegt");
        translationBuilder.add("sound.scrumcraft2.pullrequest", "De almachtige verspreidt zijn wijsheid onder zijn onderdanen");

        translationBuilder.add("title.scrumcraft2.pullrequest", "Bouwrecept, ");
        translationBuilder.add("title.scrumcraft2.denied", "AFGEWEZEN");

        // effects
        translationBuilder.add("effect.scrumcraft2.eclipse_user", "Zonsverduistering Gebruiker");

    }
}
