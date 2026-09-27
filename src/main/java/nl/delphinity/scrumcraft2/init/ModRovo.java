package nl.delphinity.scrumcraft2.init;

import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import nl.delphinity.scrumcraft2.common.rovo.crafting.RovoRecipeManager;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModRovo {
    public static final Identifier ROVO_RECIPE = identifierOf("rovo");

    public static void init() {
        ResourceLoader.get(PackType.SERVER_DATA).registerReloadListener(ROVO_RECIPE, new RovoRecipeManager());
    }
}
