package nl.delphinity.scrumcraft2.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.mixin.registry.sync.RegistriesMixin;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import nl.delphinity.scrumcraft2.Scrumcraft2;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModItemGroups {
    private static Component displayName = Component.translatable("Scrumcraft2");
    public static final CreativeModeTab SCRUMMING_DEM = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            identifierOf("scrumcraft2"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SCRUM_BALL))
                    .title(displayName)
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
            }).build());

    public static void registerItemGroups() {
        Scrumcraft2.LOGGER.info("SCRUMMING DEM Item Groups for " + Scrumcraft2.MOD_ID);
    }
}

