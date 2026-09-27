package nl.delphinity.scrumcraft2.init;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.block.DispenserBlock;
import nl.delphinity.scrumcraft2.common.item.*;

import java.util.function.Function;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModItems {

    public static final Item PULLREQUEST_DECLINED = register("pullrequest_declined", PullrequestDeclined::new);
    public static final VeryWhiteBrew VERY_WHITE_BREW = register("very_white_brew", VeryWhiteBrew::new);

    public static final Item LINKED_IN = register("linked_in", props -> new LinkedIn(props, 1.05D, 0.6D, 0.42F));
    public static final Item EVIL_LINKED_IN = register("evil_linked_in", props -> new LinkedIn(props, 0.01D, 0D, 0.21F));
    public static final Item AGARTHA_LINKED_IN = register("agartha_linked_in", props -> new LinkedIn(props, 100D, 5D, 2.1F));

    public static final RubberDucky RUBBER_DUCKY = register("rubber_ducky", RubberDucky::new);
    public static final Item WEAK_HEART = register("weak_heart", WeakHeart::new);
    public static final WeedDucky WEED_DUCKY = register("weed_ducky", WeedDucky::new);

    public static final Item AYRAN = registerFood("ayran", ModFoods.AYRAN, ModConsumables.AYRAN);
    public static final Item POTION_OF_TERRORISM = registerFood("potion_of_terrorism", ModFoods.POTION_OF_TERRORISM, ModConsumables.POTION_OF_TERRORISM);
    public static final Item AGARTHA_POTION = registerFood("agartha_potion", ModFoods.AGARTHA_POTION, ModConsumables.AGARTHA_POTION);
    public static final Item GOLDEN_FISH = registerFood("golden_fish", ModFoods.GOLDEN_FISH, ModConsumables.GOLDEN_FISH);
    public static final Item WORSTE_BOLUS = registerFood("worste_bolus", ModFoods.WORSTE_BOLUS, ModConsumables.WORSTE_BOLUS);

    public static final Item SCRUM_BALL = register("scrum_ball", props -> new ScrumBall(props, 1.0D));
    public static final Item ULTIMATE_SCRUM_BALL = register(
            "ultimate_scrum_ball",
            props -> new ScrumBall(props, 5.0D),
            new Item.Properties().component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
    );
    public static final Item SCRUM_MASTER_BALL = register(
            "scrum_master_ball",
            props -> new ScrumBall(props, 14.0D),
            new Item.Properties().component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
    );

    public static final Item CATAMARAN = register("catamaran", Catamaran::new);
    public static final Item NS_TRAIN = register("ns_train", NsTrain::new);
    public static final Item BOWL_OF_CODE = registerFood("bowl_of_code", ModFoods.BOWL_OF_CODE, ModConsumables.BOWL_OF_CODE);

    public static void init() {
        DispenserBlock.registerProjectileBehavior(SCRUM_BALL);
        DispenserBlock.registerProjectileBehavior(ULTIMATE_SCRUM_BALL);
        DispenserBlock.registerProjectileBehavior(SCRUM_MASTER_BALL);
        DispenserBlock.registerProjectileBehavior(RUBBER_DUCKY);
        DispenserBlock.registerProjectileBehavior(WEED_DUCKY);
    }

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, identifierOf(name));
        return Registry.register(BuiltInRegistries.ITEM, itemKey, itemFactory.apply(settings.setId(itemKey)));
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory) {
        return register(name, itemFactory, new Item.Properties());
    }

    private static Item registerFood(String name, FoodProperties food, Consumable consumable) {
        return register(name, Item::new, new Item.Properties().food(food, consumable));
    }
}
