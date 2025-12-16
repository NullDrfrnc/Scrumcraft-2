package nl.delphinity.scrumcraft2.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import nl.delphinity.scrumcraft2.Scrumcraft2;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> WEAK_HEART_OUCHIE = ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(Scrumcraft2.MOD_ID, "weak_heart"));
}
