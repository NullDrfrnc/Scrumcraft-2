package nl.delphinity.scrumcraft2.client;

import net.fabricmc.api.ClientModInitializer;
import nl.delphinity.scrumcraft2.client.init.ModEntityRenderers;

public class Scrumcraft2Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModEntityRenderers.init();
    }
}
