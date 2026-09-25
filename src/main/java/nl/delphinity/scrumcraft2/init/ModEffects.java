package nl.delphinity.scrumcraft2.init;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import nl.delphinity.scrumcraft2.Scrumcraft2;
import nl.delphinity.scrumcraft2.common.effect.CaffeineEffect;
import nl.delphinity.scrumcraft2.common.effect.EclipseUserEffect;
import nl.delphinity.scrumcraft2.common.effect.FreezeEffect;
import nl.delphinity.scrumcraft2.common.effect.LazyLoadingEffect;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModEffects {

    public static final Holder<MobEffect> ECLIPSE_USER = register("eclipse_user", new EclipseUserEffect());
    public static final Holder<MobEffect> CAFFEINE = register("caffeine", new CaffeineEffect());
    public static final Holder<MobEffect> STOP_THE_WORLD = register("stop_the_world",
            new FreezeEffect(MobEffectCategory.HARMFUL, 0x5382A1, identifierOf("stop_the_world")));
    public static final Holder<MobEffect> HIBERNATE = register("hibernate",
            new FreezeEffect(MobEffectCategory.NEUTRAL, 0xBCAE79, identifierOf("hibernate")));
    public static final Holder<MobEffect> LAZY_LOADING = register("lazy_loading", new LazyLoadingEffect());

    public static void init() {
        Scrumcraft2.LOGGER.info("SCRUMMING DEM Effects for " + Scrumcraft2.MOD_ID);
    }

    private static Holder<MobEffect> register(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, identifierOf(name), effect);
    }
}
