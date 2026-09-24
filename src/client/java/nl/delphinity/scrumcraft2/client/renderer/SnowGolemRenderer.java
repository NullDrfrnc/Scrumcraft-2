package nl.delphinity.scrumcraft2.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SnowGolemHeadLayer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.level.block.Blocks;
import nl.delphinity.scrumcraft2.Scrumcraft2;

@Environment(EnvType.CLIENT)
public class SnowGolemRenderer extends MobRenderer<SnowGolem, SnowGolemRenderState, SnowGolemModel> {
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
    private static final Identifier SNOW_GOLEM_LOCATION = Scrumcraft2.identifierOf("textures/entity/snowgolemtexture.png");
    private final BlockModelResolver blockModelResolver;

    public SnowGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new SnowGolemModel(context.bakeLayer(ModelLayers.SNOW_GOLEM)), 0.5F);
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new SnowGolemHeadLayer(this));
    }

    public Identifier getTextureLocation(SnowGolemRenderState snowGolemRenderState) {
        return SNOW_GOLEM_LOCATION;
    }

    public SnowGolemRenderState createRenderState() {
        return new SnowGolemRenderState();
    }

    public void extractRenderState(SnowGolem snowGolem, SnowGolemRenderState snowGolemRenderState, float f) {
        super.extractRenderState(snowGolem, snowGolemRenderState, f);
        if (snowGolem.hasPumpkin()) {
            this.blockModelResolver.update(snowGolemRenderState.headBlock, Blocks.CARVED_PUMPKIN.defaultBlockState(), BLOCK_DISPLAY_CONTEXT);
        } else {
            snowGolemRenderState.headBlock.clear();
        }
    }
}

