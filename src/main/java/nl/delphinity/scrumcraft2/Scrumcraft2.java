package nl.delphinity.scrumcraft2;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import nl.delphinity.scrumcraft2.init.ModBlocks;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModItems;
import nl.delphinity.scrumcraft2.init.ModSounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Scrumcraft2 implements ModInitializer {

    public static final String MOD_ID = "scrumcraft2";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID.toUpperCase());
    
    @Override
    public void onInitialize() {
        LOGGER.info("Scrumming Scrumcraft2");

        ModSounds.init();
        ModItems.init();
        ModEntityTypes.init();
        ModBlocks.init();
        
        LOGGER.info("Scrumcraft2 successfully Scrummed");
    }

    public static Identifier identifierOf(String path) {
        return Identifier.fromNamespaceAndPath(Scrumcraft2.MOD_ID, path);
    }
}
