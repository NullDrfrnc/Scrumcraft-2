package nl.delphinity.scrumcraft2.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.level.Level;

public class EvilSnowGolemEntity extends SnowGolem {

    public EvilSnowGolemEntity(EntityType<? extends SnowGolem> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return SnowGolem.createAttributes();
    }
}
