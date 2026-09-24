package nl.delphinity.scrumcraft2.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> WEAK_HEART_OUCHIE = ResourceKey.create(Registries.DAMAGE_TYPE, identifierOf("weak_heart"));
}
