package nl.delphinity.scrumcraft2.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import nl.delphinity.scrumcraft2.Scrumcraft2;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractLanguageProvider extends FabricLanguageProvider {

    public final String langCode;

    public AbstractLanguageProvider(FabricPackOutput dataOutput, String languageCode, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, languageCode, registryLookup);
        this.langCode = languageCode;
    }

    /**
     * Adds the contents of assets/scrumcraft2/lang/[langCode].existing.json to the generated language file (if it exists)
     */
    public void getExistingLangFile(TranslationBuilder builder) {
        String fileName = langCode + ".existing.json";
        try {
            Optional<Path> path = packOutput.getModContainer().findPath("assets/" + Scrumcraft2.MOD_ID + "/lang/" + fileName);
            if (path.isPresent()) {
                builder.add(path.get());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load language file! (" + fileName + ")", e);
        }
    }
}
