package nl.delphinity.scrumcraft2.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import nl.delphinity.scrumcraft2.common.rovo.crafting.RovoRecipe;
import nl.delphinity.scrumcraft2.common.rovo.crafting.RovoRecipeManager;
import nl.delphinity.scrumcraft2.init.ModSounds;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(CraftingMenu.class)
public class CraftingMenuMixin {

    @ModifyExpressionValue(
            method = "slotChangedCraftingGrid",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Optional;isPresent()Z"
            )
    )
    private static boolean onRecipePresent(
            boolean original,
            AbstractContainerMenu menu,
            ServerLevel level,
            Player player,
            CraftingContainer craftingContainer,
            ResultContainer resultContainer,
            @Nullable RecipeHolder<CraftingRecipe> recipeHint
    ) {
        if (player instanceof ServerPlayer sp) {
            if (original) {
                CraftingInput input = craftingContainer.asCraftInput();

                Optional<RecipeHolder<CraftingRecipe>> recipe =
                        level.getServer().getRecipeManager()
                                .getRecipeFor(RecipeType.CRAFTING, input, level, recipeHint);

                if (recipe.isPresent()) {
                    RecipeHolder<CraftingRecipe> recipeHolder2 = recipe.get();
                    CraftingRecipe craftingRecipe = recipeHolder2.value();
                    RovoRecipe foundRovo = RovoRecipeManager.getRecipeWithName(recipe.get().id().identifier().toShortString());
                    // Only decline if found and doesnt match
                    if (foundRovo != null && !foundRovo.exactMatches(input)) {
                        RovoRecipeManager.sendRovoMessage(sp);
//                        level.getServer().execute(craftingContainer::clearContent);
                        level.getServer().execute(sp::closeContainer);
                        level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.PULLREQUEST, SoundSource.PLAYERS, 0.5F, 1F);

                        return false;
                    }
                }
            }
        }
        return original;
    }
}
