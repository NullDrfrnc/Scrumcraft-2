package nl.delphinity.scrumcraft2.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import nl.delphinity.scrumcraft2.common.entity.*;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class ModEntityTypes {
    // Copied mojank's homework with this one ;~;
    public static final EntityType<RubberDuckyEntity> RUBBER_DUCKY_ENTITY = register(
            "rubber_ducky_entity",
            EntityType.Builder.of(RubberDuckyEntity::new, MobCategory.MISC)
    );

    public static final EntityType<WeedDuckyEntity> WEED_DUCKY_ENTITY = register(
            "weed_ducky_entity",
            EntityType.Builder.of(WeedDuckyEntity::new, MobCategory.MISC)
    );

    public static final EntityType<ScrumBallEntity> SCRUM_BALL_ENTITY = register(
            "scrum_ball_entity",
            EntityType.Builder.of(ScrumBallEntity::new, MobCategory.MISC)
    );

    public static final EntityType<EvilSnowGolemEntity> EVIL_SNOW_GOLEM = register(
            "evil_snow_golem_entity",
            EntityType.Builder.of(EvilSnowGolemEntity::new, MobCategory.MONSTER)
    );

    public static final EntityType<EvilSquidEntity> EVIL_SQUID = register(
            "evil_squid_entity",
            EntityType.Builder.of(EvilSquidEntity::new, MobCategory.CREATURE)
    );

    public static final EntityType<ThrownExceptionEntity> THROWN_EXCEPTION = register(
            "thrown_exception_entity",
            EntityType.Builder.<ThrownExceptionEntity>of(ThrownExceptionEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
    );

    public static final EntityType<TomcatEntity> TOMCAT = register(
            "tomcat_entity",
            EntityType.Builder.of(TomcatEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.7F)
                    .eyeHeight(0.35F)
                    .clientTrackingRange(8)
    );

    public static final EntityType<NPlusOneQueryEntity> N_PLUS_ONE_QUERY = register(
            "n_plus_one_query_entity",
            EntityType.Builder.of(NPlusOneQueryEntity::new, MobCategory.MONSTER)
                    .sized(0.4F, 0.3F)
                    .eyeHeight(0.13F)
                    .clientTrackingRange(8)
    );

    public static void init() {
        FabricDefaultAttributeRegistry.register(EVIL_SNOW_GOLEM, EvilSnowGolemEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(EVIL_SQUID, EvilSquidEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(TOMCAT, TomcatEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(N_PLUS_ONE_QUERY, NPlusOneQueryEntity.createAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, identifierOf(name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }
}
