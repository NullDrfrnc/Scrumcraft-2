package nl.delphinity.scrumcraft2.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CatRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.feline.Cat;
import nl.delphinity.scrumcraft2.Scrumcraft2;

@Environment(EnvType.CLIENT)
public class TomcatRenderer extends CatRenderer {
    private static final Identifier TOMCAT_LOCATION = Scrumcraft2.identifierOf("textures/entity/tomcat.png");

    public TomcatRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void extractRenderState(Cat cat, CatRenderState state, float partialTicks) {
        super.extractRenderState(cat, state, partialTicks);
        // Kittens use a different model layout, so they keep the normal cat texture
        if (!state.isBaby) {
            state.texture = TOMCAT_LOCATION;
        }
    }
}
