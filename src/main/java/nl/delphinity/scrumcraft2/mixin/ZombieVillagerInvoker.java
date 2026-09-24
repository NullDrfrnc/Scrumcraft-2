package nl.delphinity.scrumcraft2.mixin;

import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.UUID;

// startConverting is private in ZombieVillager
@Mixin(ZombieVillager.class)
public interface ZombieVillagerInvoker {
    @Invoker("startConverting")
    void invokeStartConverting(@Nullable UUID player, int time);
}
