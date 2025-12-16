package nl.delphinity.scrumcraft2.init;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.impl.resource.ResourceLoaderImpl;
import net.minecraft.core.HolderLookup;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.PackType;
import nl.delphinity.scrumcraft2.common.rovo.crafting.RovoRecipeManager;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModRovo {
    public static void init() {
        ServerLifecycleEvents.SERVER_STARTING.register((MinecraftServer server) -> {
            HolderLookup.Provider provider = server.registryAccess();
            RovoRecipeManager manager = new RovoRecipeManager(provider);
            ResourceLoaderImpl.get(PackType.SERVER_DATA).registerReloader(
                    identifierOf("rovo"),
                    manager
            );
        });
    }
}
