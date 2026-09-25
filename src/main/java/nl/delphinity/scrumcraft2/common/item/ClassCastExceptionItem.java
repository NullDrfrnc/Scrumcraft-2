package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Util;
import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import nl.delphinity.scrumcraft2.common.entity.ThrownExceptionEntity;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;

import java.util.List;

// Casts the mob it hits to a random other mob: "class Cow cannot be cast to class Pig"
public class ClassCastExceptionItem extends ExceptionItem {
    private static final List<EntityType<? extends Mob>> CASTS = List.of(
            EntityTypes.PIG,
            EntityTypes.COW,
            EntityTypes.SHEEP,
            EntityTypes.CHICKEN,
            EntityTypes.RABBIT,
            EntityTypes.FROG,
            EntityTypes.ZOMBIE,
            EntityTypes.CREEPER,
            ModEntityTypes.TOMCAT
    );

    public ClassCastExceptionItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onUncaught(ServerLevel level, ThrownExceptionEntity exception, HitResult hitResult) {
        if (!(hitResult instanceof EntityHitResult entityHit) || !(entityHit.getEntity() instanceof Mob target)) {
            return;
        }

        List<EntityType<? extends Mob>> options = CASTS.stream().filter(type -> type != target.getType()).toList();
        EntityType<? extends Mob> castTo = Util.getRandom(options, level.getRandom());
        target.convertTo(castTo, ConversionParams.single(target, false, false), mob -> {});

        if (exception.getOwner() instanceof Player player) {
            player.sendOverlayMessage(Component.translatable(
                    "message.scrumcraft2.class_cast_exception", target.getType().getDescription(), castTo.getDescription()
            ).withStyle(ChatFormatting.RED));
        }
    }
}
