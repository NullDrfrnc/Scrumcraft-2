package nl.delphinity.scrumcraft2.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SilverfishRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import nl.delphinity.scrumcraft2.Scrumcraft2;

@Environment(EnvType.CLIENT)
public class NPlusOneQueryRenderer extends SilverfishRenderer {
    private static final Identifier N_PLUS_ONE_QUERY_LOCATION = Scrumcraft2.identifierOf("textures/entity/n_plus_one_query.png");

    public NPlusOneQueryRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return N_PLUS_ONE_QUERY_LOCATION;
    }
}
