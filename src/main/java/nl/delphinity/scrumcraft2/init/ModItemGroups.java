package nl.delphinity.scrumcraft2.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import nl.delphinity.scrumcraft2.Scrumcraft2;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModItemGroups {
    private static Component displayName = Component.translatable("itemgroup.scrumcraft2");

    public static final CreativeModeTab SCRUMMING_DEM = Registry.register(Registries.CREATIVE_MODE_TAB.registry().,
            identifierOf("scrumcraft2"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SCRUM_BALL))
                    .title(displayName)
                    .displayItems((itemDisplayParameters, output) -> {
                    output.accept(ModItems.SCRUM_BALL);
                    output.accept(ModItems.ULTIMATE_SCRUM_BALL);
                    output.accept(ModItems.SCRUM_MASTER_BALL);
                    output.accept(ModItems.RUBBER_DUCKY);
                    output.accept(ModItems.CATAMARAN);
            }).build());

    public static void registerItemGroups() {
        Scrumcraft2.LOGGER.info("SCRUMMING DEM Item Groups for " + Scrumcraft2.MOD_ID);
    }
}

