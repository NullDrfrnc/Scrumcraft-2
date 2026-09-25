package nl.delphinity.scrumcraft2.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModBlocks;
import nl.delphinity.scrumcraft2.init.ModItems;

import java.util.concurrent.CompletableFuture;

public class EnglishLanguageProvider extends AbstractLanguageProvider {
    public EnglishLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
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
        translationBuilder.add(ModItems.BOWL_OF_CODE, "Bowl of code");
        translationBuilder.add(ModBlocks.CHRISTMASTREE, "Christmas Tree");
        translationBuilder.add(ModItems.GOLDEN_FISH, "Golden Fish");
        translationBuilder.add(ModItems.VERY_WHITE_BREW, "Very White Brew");
        translationBuilder.add(ModItems.WORSTE_BOLUS, "Sausage Bolus");
        translationBuilder.add(ModEntityTypes.EVIL_SNOW_GOLEM, "Evil Snow Golem");
        translationBuilder.add(ModEntityTypes.EVIL_SQUID, "Evil Squid");
        translationBuilder.add(ModItems.EVIL_LINKED_IN, "Evil LinkedIn");
        translationBuilder.add(ModItems.LINKED_IN, "LinkedIn");
        translationBuilder.add(ModItems.AGARTHA_LINKED_IN, "Agartha LinkedIn");
        translationBuilder.add(ModItems.PULLREQUEST_DECLINED, "Pull Request, DECLINED");
        translationBuilder.add(ModItems.EXCEPTION, "throw new Exception()");
        translationBuilder.add(ModItems.CLASS_CAST_EXCEPTION, "ClassCastException");
        translationBuilder.add(ModItems.CUP_OF_JAVA, "Cup of Java");
        translationBuilder.add(ModItems.ABSTRACT_SINGLETON_PROXY_FACTORY_BEAN, "AbstractSingletonProxyFactoryBean");
        translationBuilder.add(ModItems.WAR_FILE, "scrumcraft.war");
        translationBuilder.add(ModItems.TOMCAT_SPAWN_EGG, "Tomcat Spawn Egg");
        translationBuilder.add(ModItems.HIBERNATE_SESSION, "Hibernate Session");
        translationBuilder.add(ModItems.LAZY_LOADED_SANDWICH, "Lazy Loaded Sandwich");
        translationBuilder.add(ModItems.N_PLUS_ONE_QUERY_SPAWN_EGG, "N+1 Query Spawn Egg");
        translationBuilder.add(ModItems.STRUTS, "Struts");
        translationBuilder.add("item.scrumcraft2.struts.lore", "@Deprecated(since = \"2013\")");
        translationBuilder.add(ModEntityTypes.THROWN_EXCEPTION, "Exception");
        translationBuilder.add(ModEntityTypes.TOMCAT, "Tomcat");
        translationBuilder.add(ModEntityTypes.N_PLUS_ONE_QUERY, "N+1 Query");


        // ItemGroups
        
        // ItemTags
        
        // Sounds (For subtitles)
        translationBuilder.add("sound.scrumcraft2.sickseven", "SIIIX SEVEEEEN");
        translationBuilder.add("sound.scrumcraft2.rubber_ducky_squeak", "Rubber ducky squeaks");
        translationBuilder.add("sound.scrumcraft2.rubber_ducky_throw", "Rubber ducky flies");
        translationBuilder.add("sound.scrumcraft2.pullrequest", "Pullrequest, declined!");


        // death messages
        translationBuilder.add("death.attack.weak_heart", "%1$s died from deception...");
        translationBuilder.add("death.attack.null_pointer", "%1$s dereferenced null");

        // effects
        translationBuilder.add("effect.scrumcraft2.eclipse_user", "Eclipse User");
        translationBuilder.add("effect.scrumcraft2.caffeine", "Caffeine");
        translationBuilder.add("effect.scrumcraft2.stop_the_world", "Stop-the-world");
        translationBuilder.add("effect.scrumcraft2.hibernate", "Hibernating");
        translationBuilder.add("effect.scrumcraft2.lazy_loading", "Lazy Loading");

        // messages
        translationBuilder.add("message.scrumcraft2.exception_caught", "catch (Exception e) { } // your exception got caught");
        translationBuilder.add("message.scrumcraft2.class_cast_exception", "java.lang.ClassCastException: class %s cannot be cast to class %s");
        translationBuilder.add("message.scrumcraft2.garbage_collector", "Garbage collector running... stop-the-world pause");
        translationBuilder.add("message.scrumcraft2.lazy_initialization_exception", "org.hibernate.LazyInitializationException: could not initialize proxy - no Session");
        translationBuilder.add("message.scrumcraft2.http_500", "HTTP Status 500 – Internal Server Error");
        translationBuilder.add("message.scrumcraft2.http_404", "HTTP Status 404 – Not Found");
        translationBuilder.add("message.scrumcraft2.war_deployed", "Deployment of web application archive [scrumcraft.war] has finished in [67] ms");


        translationBuilder.add("title.scrumcraft2.pullrequest", "Crafting recipe, ");
        translationBuilder.add("title.scrumcraft2.denied", "DENIED");
        translationBuilder.add("title.scrumcraft2.data_breach", "Your data has been leaked");
        translationBuilder.add("title.scrumcraft2.server_startup", "Server startup in [67] milliseconds");
    }
}
