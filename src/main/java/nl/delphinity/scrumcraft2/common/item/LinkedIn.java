package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LinkedIn extends Item {
    private final double scale;
    private final double step;
    private final float jump;

    public LinkedIn(Properties properties, double scale, double step, float jump) {
        super(properties);
        this.scale = scale;
        this.step = step;
        this.jump = jump;
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        AttributeInstance scaleInstance = player.getAttribute(Attributes.SCALE);
        AttributeInstance stepInstance = player.getAttribute(Attributes.STEP_HEIGHT);
        AttributeInstance jumpInstance = player.getAttribute(Attributes.JUMP_STRENGTH);
        if (scaleInstance != null && stepInstance != null && jumpInstance != null) {
            scaleInstance.setBaseValue(scale);
            stepInstance.setBaseValue(step);
            jumpInstance.setBaseValue(jump);
        }
        return InteractionResult.PASS;
    }
}
