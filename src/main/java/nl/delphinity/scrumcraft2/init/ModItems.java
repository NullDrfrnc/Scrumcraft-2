package nl.delphinity.scrumcraft2.init;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.block.DispenserBlock;
import nl.delphinity.scrumcraft2.common.item.*;

import java.util.List;
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

    // Java
    public static final Item EXCEPTION = register("exception", GenericExceptionItem::new);
    public static final Item CLASS_CAST_EXCEPTION = register("class_cast_exception", ClassCastExceptionItem::new);
    public static final Item CUP_OF_JAVA = registerFood("cup_of_java", ModFoods.CUP_OF_JAVA, ModConsumables.CUP_OF_JAVA);
    // Hits hard, but is as slow as reading its name
    public static final Item ABSTRACT_SINGLETON_PROXY_FACTORY_BEAN = register(
            "abstract_singleton_proxy_factory_bean",
            Item::new,
            new Item.Properties().sword(ToolMaterial.DIAMOND, 7.0F, -3.6F)
    );

    // Tomcat
    public static final Item WAR_FILE = register("war_file", Item::new);
    public static final Item TOMCAT_SPAWN_EGG = registerSpawnEgg("tomcat_spawn_egg", ModEntityTypes.TOMCAT);

    // Hibernate
    public static final Item HIBERNATE_SESSION = registerFood("hibernate_session", ModFoods.HIBERNATE_SESSION, ModConsumables.HIBERNATE_SESSION);
    public static final Item LAZY_LOADED_SANDWICH = registerFood("lazy_loaded_sandwich", ModFoods.LAZY_LOADED_SANDWICH, ModConsumables.LAZY_LOADED_SANDWICH);
    public static final Item N_PLUS_ONE_QUERY_SPAWN_EGG = registerSpawnEgg("n_plus_one_query_spawn_egg", ModEntityTypes.N_PLUS_ONE_QUERY);

    // Struts (end of life since 2013, so it only lasts 13 uses)
    public static final Item STRUTS = register(
            "struts",
            Struts::new,
            new Item.Properties()
                    .pickaxe(ToolMaterial.IRON, 1.0F, -2.8F)
                    .durability(13)
                    .component(DataComponents.LORE, new ItemLore(List.of(Component.translatable("item.scrumcraft2.struts.lore"))))
    );

    public static void init() {
        DispenserBlock.registerProjectileBehavior(SCRUM_BALL);
        DispenserBlock.registerProjectileBehavior(ULTIMATE_SCRUM_BALL);
        DispenserBlock.registerProjectileBehavior(SCRUM_MASTER_BALL);
        DispenserBlock.registerProjectileBehavior(RUBBER_DUCKY);
        DispenserBlock.registerProjectileBehavior(WEED_DUCKY);
        DispenserBlock.registerProjectileBehavior(EXCEPTION);
        DispenserBlock.registerProjectileBehavior(CLASS_CAST_EXCEPTION);
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

    private static Item registerSpawnEgg(String name, EntityType<?> entityType) {
        return register(name, SpawnEggItem::new, new Item.Properties().spawnEgg(entityType));
    }
}
