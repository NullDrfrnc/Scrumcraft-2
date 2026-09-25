package nl.delphinity.scrumcraft2.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModBlocks;
import nl.delphinity.scrumcraft2.init.ModItems;

import java.util.concurrent.CompletableFuture;

public class DutchLanguageProvider extends AbstractLanguageProvider {
    public DutchLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
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
        translationBuilder.add(ModItems.EXCEPTION, "gooi nieuwe Uitzondering()");
        translationBuilder.add(ModItems.CLASS_CAST_EXCEPTION, "KlasseGietUitzondering");
        translationBuilder.add(ModItems.CUP_OF_JAVA, "Kop Java");
        translationBuilder.add(ModItems.ABSTRACT_SINGLETON_PROXY_FACTORY_BEAN, "AbstracteEenlingVolmachtFabriekBoon");
        translationBuilder.add(ModItems.WAR_FILE, "scrumcraft.oorlog");
        translationBuilder.add(ModItems.TOMCAT_SPAWN_EGG, "Kater Spawn-ei");
        translationBuilder.add(ModItems.HIBERNATE_SESSION, "Winterslaap Sessie");
        translationBuilder.add(ModItems.LAZY_LOADED_SANDWICH, "Lui Geladen Broodje");
        translationBuilder.add(ModItems.N_PLUS_ONE_QUERY_SPAWN_EGG, "N+1 Query Spawn-ei");
        translationBuilder.add(ModItems.STRUTS, "Stutten");
        translationBuilder.add("item.scrumcraft2.struts.lore", "@Deprecated(since = \"2013\")");
        translationBuilder.add(ModEntityTypes.THROWN_EXCEPTION, "Uitzondering");
        translationBuilder.add(ModEntityTypes.TOMCAT, "Kater");
        translationBuilder.add(ModEntityTypes.N_PLUS_ONE_QUERY, "N+1 Query");

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
        translationBuilder.add("title.scrumcraft2.data_breach", "Je data is gelekt");
        translationBuilder.add("title.scrumcraft2.server_startup", "Server opgestart in [67] milliseconden");

        // death messages
        translationBuilder.add("death.attack.null_pointer", "%1$s verwees naar null");

        // effects
        translationBuilder.add("effect.scrumcraft2.eclipse_user", "Zonsverduistering Gebruiker");
        translationBuilder.add("effect.scrumcraft2.caffeine", "Cafeïne");
        translationBuilder.add("effect.scrumcraft2.stop_the_world", "Stop-de-wereld");
        translationBuilder.add("effect.scrumcraft2.hibernate", "Winterslaap");
        translationBuilder.add("effect.scrumcraft2.lazy_loading", "Lui Laden");

        // messages
        translationBuilder.add("message.scrumcraft2.exception_caught", "catch (Exception e) { } // jouw exception is gecatcht");
        translationBuilder.add("message.scrumcraft2.class_cast_exception", "java.lang.ClassCastException: class %s cannot be cast to class %s");
        translationBuilder.add("message.scrumcraft2.garbage_collector", "Garbage collector is bezig... stop-de-wereld pauze");
        translationBuilder.add("message.scrumcraft2.lazy_initialization_exception", "org.hibernate.LazyInitializationException: could not initialize proxy - no Session");
        translationBuilder.add("message.scrumcraft2.http_500", "HTTP Status 500 – Interne Serverfout");
        translationBuilder.add("message.scrumcraft2.http_404", "HTTP Status 404 – Niet Gevonden");
        translationBuilder.add("message.scrumcraft2.war_deployed", "Deployment van [scrumcraft.oorlog] klaar in [67] ms");

    }
}
