package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class LinkedIn extends Item {

    double value;
    double step;
    float jump;

    public LinkedIn(Properties properties, double value, double step, float jump) {
        super(properties);
        setScale(value);
        setStep(step);
        setJump(jump);
    }

    public void setStep(double step) {
        this.step = step;
    }

    public void setScale(double value) {
        this.value = value;
    }

    public void setJump(float jump) {
        this.jump = jump;
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        var scaleInstance = player.getAttribute(Attributes.SCALE);
        var stepInstance = player.getAttribute(Attributes.STEP_HEIGHT);
        var jumpInstance = player.getAttribute(Attributes.JUMP_STRENGTH);
        if (scaleInstance != null) {
            // Set absolute scale to whatever value
            scaleInstance.setBaseValue(value);
            stepInstance.setBaseValue(step);
            jumpInstance.setBaseValue(jump);
            System.out.println("Scale: " + scaleInstance.getBaseValue());
            System.out.println("Step: " + stepInstance.getBaseValue());
            return InteractionResult.PASS;
        }
        return InteractionResult.PASS;
    }

}
