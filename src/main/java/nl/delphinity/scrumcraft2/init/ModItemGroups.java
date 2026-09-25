package nl.delphinity.scrumcraft2.init;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import nl.delphinity.scrumcraft2.Scrumcraft2;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModItemGroups {
    public static final CreativeModeTab SCRUMMING_DEM = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            identifierOf("scrumcraft2"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SCRUM_BALL))
                    .title(Component.translatable("Scrumcraft2"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.SCRUM_BLOCK.asItem());
                        output.accept(ModItems.SCRUM_BALL);
                        output.accept(ModItems.ULTIMATE_SCRUM_BALL);
                        output.accept(ModItems.SCRUM_MASTER_BALL);
                        output.accept(ModItems.RUBBER_DUCKY);
                        output.accept(ModItems.CATAMARAN);
                        output.accept(ModItems.NS_TRAIN);
                        output.accept(ModItems.WEAK_HEART);
                        output.accept(ModItems.WEED_DUCKY);
                        output.accept(ModItems.AYRAN);
                        output.accept(ModItems.POTION_OF_TERRORISM);
                        output.accept(ModItems.AGARTHA_POTION);
                        output.accept(ModItems.GOLDEN_FISH);
                        output.accept(ModItems.WORSTE_BOLUS);
                        output.accept(ModItems.VERY_WHITE_BREW);
                        output.accept(ModItems.LINKED_IN);
                        output.accept(ModItems.EVIL_LINKED_IN);
                        output.accept(ModItems.AGARTHA_LINKED_IN);
                        output.accept(ModItems.BOWL_OF_CODE);
                        output.accept(ModItems.PULLREQUEST_DECLINED);
                        output.accept(ModItems.EXCEPTION);
                        output.accept(ModItems.CLASS_CAST_EXCEPTION);
                        output.accept(ModItems.CUP_OF_JAVA);
                        output.accept(ModItems.ABSTRACT_SINGLETON_PROXY_FACTORY_BEAN);
                        output.accept(ModItems.WAR_FILE);
                        output.accept(ModItems.TOMCAT_SPAWN_EGG);
                        output.accept(ModItems.HIBERNATE_SESSION);
                        output.accept(ModItems.LAZY_LOADED_SANDWICH);
                        output.accept(ModItems.N_PLUS_ONE_QUERY_SPAWN_EGG);
                        output.accept(ModItems.STRUTS);
                    }).build());

    public static void init() {
        Scrumcraft2.LOGGER.info("SCRUMMING DEM Item Groups for " + Scrumcraft2.MOD_ID);
    }
}

