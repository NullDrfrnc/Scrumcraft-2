package nl.delphinity.scrumcraft2.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ResultSlot.class)
public class ResultSlotMixin {
    
    @Inject(
            method = "onTake",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onTake(Player player, ItemStack itemStack, CallbackInfo ci) {
        if(!(player instanceof ServerPlayer sp)) return;
        sp.sendSystemMessage(Component.literal("Taking item "+itemStack.getItem().getName()));
    }
}
