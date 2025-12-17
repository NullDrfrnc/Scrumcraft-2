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

    public LinkedIn(Properties properties, double value) {
        super(properties);
        setScale(value);
    }

    public void setScale(double value) {
        this.value = value;
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        var scaleInstance = player.getAttribute(Attributes.SCALE);
        if (scaleInstance != null) {
            // Set absolute scale to 0.1x
            scaleInstance.setBaseValue(value);
            return InteractionResult.PASS;
        }
        return InteractionResult.PASS;
    }

}
