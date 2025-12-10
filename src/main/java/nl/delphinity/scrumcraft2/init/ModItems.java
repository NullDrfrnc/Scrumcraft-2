package nl.delphinity.scrumcraft2.init;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DispenserBlock;
import nl.delphinity.scrumcraft2.common.item.Catamaran;
import nl.delphinity.scrumcraft2.common.item.RubberDucky;
import nl.delphinity.scrumcraft2.common.item.ScrumBall;

import java.util.function.Function;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModItems {
    public static final RubberDucky RUBBER_DUCKY = (RubberDucky) register(
            "rubber_ducky",
            RubberDucky::new,
            new Item.Properties()
    );

    public static final Item SCRUM_BALL = register(
            "scrum_ball",
            ScrumBall::new,
            new Item.Properties()
    );

    public static final Item ULTIMATE_SCRUM_BALL = register(
            "ultimate_scrum_ball",
            ScrumBall::new,
            new Item.Properties()
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
    );

    public static final Item SCRUM_MASTER_BALL = register(
            "scrum_master_ball",
            ScrumBall::new,
            new Item.Properties()
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
    );

    public static final Item CATAMARAN = register(
            "catamaran",
            Catamaran::new,
            new Item.Properties()
    );

    public static void init() {
        DispenserBlock.registerProjectileBehavior(SCRUM_BALL);
        DispenserBlock.registerProjectileBehavior(ULTIMATE_SCRUM_BALL);
        DispenserBlock.registerProjectileBehavior(SCRUM_MASTER_BALL);
        DispenserBlock.registerProjectileBehavior(RUBBER_DUCKY);
    }

    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, identifierOf(name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

}
