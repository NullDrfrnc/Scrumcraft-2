package nl.delphinity.scrumcraft2.init;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties AYRAN = food(4, 0.3F);
    public static final FoodProperties POTION_OF_TERRORISM = food(4, 0.3F);
    public static final FoodProperties AGARTHA_POTION = food(4, 0.3F);
    public static final FoodProperties GOLDEN_FISH = food(4, 0.3F);
    public static final FoodProperties BOWL_OF_CODE = food(3, 0.4F);
    public static final FoodProperties WORSTE_BOLUS = food(4, 0.3F);
    public static final FoodProperties CUP_OF_JAVA = food(2, 0.2F);
    public static final FoodProperties HIBERNATE_SESSION = food(2, 0.2F);
    // Barely fills you up, the real food gets lazy loaded later
    public static final FoodProperties LAZY_LOADED_SANDWICH = food(1, 0.1F);

    private static FoodProperties food(int nutrition, float saturation) {
        return new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .alwaysEdible()
                .build();
    }
}
