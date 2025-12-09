package nl.delphinity.scrumcraft2.client.init;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;

public class ModEntityRenderers {
    public static void init() {
        EntityRenderers.register(ModEntityTypes.RUBBER_DUCKY_ENTITY, ThrownItemRenderer::new);
        EntityRenderers.register(ModEntityTypes.SCRUM_BALL_ENTITY, ThrownItemRenderer::new);
    }
}
