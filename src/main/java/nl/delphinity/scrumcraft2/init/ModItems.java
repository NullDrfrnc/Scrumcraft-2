package nl.delphinity.scrumcraft2.init;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DispenserBlock;

import nl.delphinity.scrumcraft2.common.item.*;
import nl.delphinity.scrumcraft2.common.item.Catamaran;
import nl.delphinity.scrumcraft2.common.item.NsTrain;
import nl.delphinity.scrumcraft2.common.item.RubberDucky;
import nl.delphinity.scrumcraft2.common.item.ScrumBall;
import nl.delphinity.scrumcraft2.common.item.WeedDucky;

import java.util.function.Function;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModItems {

    public static final VeryWhiteBrew VERY_WHITE_BREW = (VeryWhiteBrew) register(
            "very_white_brew",
            VeryWhiteBrew::new,
            new Item.Properties()
    );

    public static final Item LINKED_IN = register(
            "linked_in",
            props -> new LinkedIn(props, 1.05D),
            new Item.Properties()
    );

    public static final Item EVIL_LINKED_IN = register(
            "evil_linked_in",
            props -> new LinkedIn(props, 0.01D),
            new Item.Properties()
    );

    public static final Item AGARTHA_LINKED_IN = register(
            "agartha_linked_in",
            props -> new LinkedIn(props, 100D),
            new Item.Properties()
    );

    public static final RubberDucky RUBBER_DUCKY = (RubberDucky) register(
            "rubber_ducky",
            RubberDucky::new,
            new Item.Properties()
    );

    public static final Item WEAK_HEART = register(
            "weak_heart",
            WeakHeart::new,
            new Item.Properties()
    );

    public static final WeedDucky WEED_DUCKY = (WeedDucky) register(
            "weed_ducky",
            WeedDucky::new,
            new Item.Properties()
    );

    public static final Item AYRAN = register(
            "ayran",
            Item::new,
            new Item.Properties().food(ModFoods.AYRAN, ModConsumables.AYRAN)
    );

    public static final Item POTION_OF_TERRORISM = register(
            "potion_of_terrorism",
            Item::new,
            new Item.Properties().food(ModFoods.POTION_OF_TERRORISM, ModConsumables.POTION_OF_TERRORISM)
    );

    public static final Item AGARTHA_POTION = register(
            "agartha_potion",
            Item::new,
            new Item.Properties().food(ModFoods.AGARTHA_POTION, ModConsumables.AGARTHA_POTION)
    );

    public static final Item GOLDEN_FISH = register(
            "golden_fish",
            Item::new,
            new Item.Properties().food(ModFoods.GOLDEN_FISH, ModConsumables.GOLDEN_FISH)
    );

    public static final Item WORSTE_BOLUS = register(
            "worste_bolus",
            Item::new,
            new Item.Properties().food(ModFoods.WORSTE_BOLUS, ModConsumables.WORSTE_BOLUS)
    );

    public static final Item SCRUM_BALL = register(
            "scrum_ball",
            props -> new ScrumBall(props, 1.0D),
            new Item.Properties()
    );

    public static final Item ULTIMATE_SCRUM_BALL = register(
            "ultimate_scrum_ball",
            props -> new ScrumBall(props, 5.0D),
            new Item.Properties()
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
    );
    public static final Item SCRUM_MASTER_BALL = register(
            "scrum_master_ball",
            props -> new ScrumBall(props, 14.0D),
            new Item.Properties()
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
    );

    public static final Item CATAMARAN = register(
            "catamaran",
            Catamaran::new,
            new Item.Properties()
    );

    public static final Item NS_TRAIN = register(
            "ns_train",
            NsTrain::new,
            new Item.Properties()
    );

    public static void init() {
        DispenserBlock.registerProjectileBehavior(SCRUM_BALL);
        DispenserBlock.registerProjectileBehavior(ULTIMATE_SCRUM_BALL);
        DispenserBlock.registerProjectileBehavior(SCRUM_MASTER_BALL);
        DispenserBlock.registerProjectileBehavior(RUBBER_DUCKY);
        DispenserBlock.registerProjectileBehavior(WEED_DUCKY);
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
