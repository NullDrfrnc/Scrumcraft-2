package nl.delphinity.scrumcraft2.datagen.tagproviders;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/tags">documentation</a>
     * @param wrapperLookup wrapperLookup
     */
    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        
    }
}
