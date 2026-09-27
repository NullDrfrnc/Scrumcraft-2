package nl.delphinity.scrumcraft2.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import nl.delphinity.scrumcraft2.common.block.ChristmasTreeBlock;
import nl.delphinity.scrumcraft2.common.block.ScrumBlock;

import java.util.function.Function;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModBlocks {

    public static final Block SCRUM_BLOCK = register(
            "scrum_block",
            ScrumBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.ANVIL)
    );

    public static final Block CHRISTMASTREE = register(
            "christmastree",
            ChristmasTreeBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.LEAF_LITTER)
    );

    public static void init() {
    }

    // Registers the block together with its block item
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, identifierOf(name));
        Block block = blockFactory.apply(settings.setId(blockKey));

        ModItems.register(name, props -> new BlockItem(block, props), new Item.Properties().useBlockDescriptionPrefix());

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }
}
