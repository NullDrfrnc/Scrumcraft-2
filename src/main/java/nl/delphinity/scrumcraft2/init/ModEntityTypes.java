package nl.delphinity.scrumcraft2.init;

import com.ibm.icu.util.ULocale;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import nl.delphinity.scrumcraft2.common.entity.RubberDuckyEntity;
import nl.delphinity.scrumcraft2.common.entity.ScrumBallEntity;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModEntityTypes {
    // Copied mojank's homework with this one ;~;
    public static final EntityType<RubberDuckyEntity> RUBBER_DUCKY_ENTITY = register(
            "rubber_ducky_entity",
            EntityType.Builder.of(RubberDuckyEntity::new, MobCategory.MISC)
    );

    public static final EntityType<ScrumBallEntity> SCRUM_BALL_ENTITY = register(
            "scrum_ball_entity",
            EntityType.Builder.of(ScrumBallEntity::new, MobCategory.MISC)
    );

    public static void init() {
    }

    private static <T extends Entity> EntityType<T> register(String string, EntityType.Builder<T> builder) {
        return register(moddedEntityId(string), builder);
    }

    private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> resourceKey, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, resourceKey, builder.build(resourceKey));
    }

    private static ResourceKey<EntityType<?>> moddedEntityId(String string) {
        return ResourceKey.create(Registries.ENTITY_TYPE, identifierOf(string));
    }
}
