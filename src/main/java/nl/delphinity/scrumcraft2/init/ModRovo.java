package nl.delphinity.scrumcraft2.init;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
import net.minecraft.core.HolderLookup;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.PackType;
import nl.delphinity.scrumcraft2.common.rovo.crafting.RovoRecipeManager;

public class ModRovo {
    public static void init() {
        ServerLifecycleEvents.SERVER_STARTED.register((MinecraftServer server) -> {
            HolderLookup.Provider provider = server.registryAccess();
            RovoRecipeManager manager = new RovoRecipeManager(provider);
            ResourceManagerHelperImpl.get(PackType.SERVER_DATA).registerReloadListener(manager);
        });
    }
}
