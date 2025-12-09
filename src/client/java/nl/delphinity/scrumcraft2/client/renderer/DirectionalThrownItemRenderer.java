package nl.delphinity.scrumcraft2.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemDisplayContext;
import nl.delphinity.scrumcraft2.client.renderstate.DirectionalRenderState;

/**
 * The exact same as {@link net.minecraft.client.renderer.entity.ThrownItemRenderer} but with a locked yaw so it only points in the direction you threw it in (Usefull for 3d models)
 *
 * @param <T>
 */
public abstract class DirectionalThrownItemRenderer<T extends Entity & ItemSupplier, S extends DirectionalRenderState> extends EntityRenderer<T, S> {
    protected final ItemModelResolver itemModelResolver;
    protected final float scale;
    protected final boolean fullBright;

    public DirectionalThrownItemRenderer(EntityRendererProvider.Context context, float f, boolean bl) {
        super(context);
        this.itemModelResolver = context.getItemModelResolver();
        this.scale = f;
        this.fullBright = bl;
    }

    @Override
    protected int getBlockLightLevel(T entity, BlockPos blockPos) {
        return this.fullBright ? 15 : super.getBlockLightLevel(entity, blockPos);
    }

    public DirectionalThrownItemRenderer(EntityRendererProvider.Context context) {
        this(context, 1.0F, false);
    }

    public void submit(S directionalRendererState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.scale(this.scale, this.scale, this.scale);

        poseStack.mulPose(Axis.YP.rotationDegrees(directionalRendererState.yRot - 180.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(directionalRendererState.xRot));

        directionalRendererState.item
                .submit(poseStack, submitNodeCollector, directionalRendererState.lightCoords, OverlayTexture.NO_OVERLAY, directionalRendererState.outlineColor);
        poseStack.popPose();
        super.submit(directionalRendererState, poseStack, submitNodeCollector, cameraRenderState);
    }

    public void extractRenderState(T entity, S directionalRenderState, float f) {
        super.extractRenderState(entity, directionalRenderState, f);
        this.itemModelResolver.updateForNonLiving(directionalRenderState.item, entity.getItem(), ItemDisplayContext.GROUND, entity);
        directionalRenderState.xRot = entity.getXRot(f);
        directionalRenderState.yRot = entity.getYRot(f);

    }
}

