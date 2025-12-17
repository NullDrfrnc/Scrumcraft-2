package nl.delphinity.scrumcraft2.init;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import nl.delphinity.scrumcraft2.Scrumcraft2;
import nl.delphinity.scrumcraft2.common.effect.EclipseUserEffect;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModEffects {

    public static final Holder<MobEffect> ECLIPSE_USER =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, identifierOf("eclipse_user"), new EclipseUserEffect());

    public static void registerEffects() {
        Scrumcraft2.LOGGER.info("SCRUMMING DEM Effects for " + Scrumcraft2.MOD_ID);
    }

}
