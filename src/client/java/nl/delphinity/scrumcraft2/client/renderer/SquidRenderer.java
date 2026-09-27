package nl.delphinity.scrumcraft2.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.animal.squid.SquidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SquidRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.squid.Squid;
import nl.delphinity.scrumcraft2.Scrumcraft2;

@Environment(EnvType.CLIENT)
public class SquidRenderer<T extends Squid> extends AgeableMobRenderer<T, SquidRenderState, SquidModel> {
    private static final Identifier EVIL_SQUID_LOCATION = Scrumcraft2.identifierOf("textures/entity/evilsquid.png");

    public SquidRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new SquidModel(context.bakeLayer(ModelLayers.SQUID)),
                new SquidModel(context.bakeLayer(ModelLayers.SQUID)),
                0.7F
        );
    }

    public Identifier getTextureLocation(SquidRenderState squidRenderState) {
        return EVIL_SQUID_LOCATION;
    }

    public SquidRenderState createRenderState() {
        return new SquidRenderState();
    }

    public void extractRenderState(T squid, SquidRenderState squidRenderState, float f) {
        super.extractRenderState(squid, squidRenderState, f);
        squidRenderState.tentacleAngle = Mth.lerp(f, squid.oldTentacleAngle, squid.tentacleAngle);
        squidRenderState.xBodyRot = Mth.lerp(f, squid.xBodyRotO, squid.xBodyRot);
        squidRenderState.zBodyRot = Mth.lerp(f, squid.zBodyRotO, squid.zBodyRot);
    }

    protected void setupRotations(SquidRenderState squidRenderState, PoseStack poseStack, float f, float g) {
        poseStack.translate(0.0F, squidRenderState.isBaby ? 0.25F : 0.5F, 0.0F);
        poseStack.rotateDegrees(Axis.YP, 180.0F - f);
        poseStack.rotateDegrees(Axis.XP, squidRenderState.xBodyRot);
        poseStack.rotateDegrees(Axis.YP, squidRenderState.zBodyRot);
        poseStack.translate(0.0F, squidRenderState.isBaby ? -0.6F : -1.2F, 0.0F);
    }
}

