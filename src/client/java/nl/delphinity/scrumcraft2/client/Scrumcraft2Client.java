package nl.delphinity.scrumcraft2.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import nl.delphinity.scrumcraft2.client.init.ModEntityRenderers;
import nl.delphinity.scrumcraft2.common.entity.EvilSnowGolemEntity;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;

public class Scrumcraft2Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModEntityRenderers.init();
    }
}
