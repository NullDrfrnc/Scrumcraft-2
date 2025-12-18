package nl.delphinity.scrumcraft2.client.init;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.WardenRenderer;
import nl.delphinity.scrumcraft2.client.renderer.RubberDuckyThrownItemRenderer;
import nl.delphinity.scrumcraft2.client.renderer.SnowGolemRenderer;
import nl.delphinity.scrumcraft2.client.renderer.SquidRenderer;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;

public class ModEntityRenderers {
    public static void init() {
        EntityRenderers.register(ModEntityTypes.SCRUM_BALL_ENTITY, ThrownItemRenderer::new);
        EntityRenderers.register(ModEntityTypes.RUBBER_DUCKY_ENTITY, RubberDuckyThrownItemRenderer::new);
        EntityRenderers.register(ModEntityTypes.EVIL_SNOW_GOLEM, ctx -> new SnowGolemRenderer(ctx));
        EntityRenderers.register(ModEntityTypes.WEED_DUCKY_ENTITY, RubberDuckyThrownItemRenderer::new);
        EntityRenderers.register(ModEntityTypes.EVIL_SQUID, SquidRenderer::new);
        EntityRenderers.register(ModEntityTypes.CHRISTMAS_WARDEN, WardenRenderer::new);
    }
}
