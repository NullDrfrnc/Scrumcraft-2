package nl.delphinity.scrumcraft2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import nl.delphinity.scrumcraft2.client.renderstate.RubberDuckyRenderState;
import nl.delphinity.scrumcraft2.common.entity.RubberDuckyEntity;
import org.jetbrains.annotations.NotNull;

public class RubberDuckyThrownItemRenderer extends DirectionalThrownItemRenderer<RubberDuckyEntity, RubberDuckyRenderState> {
    public RubberDuckyThrownItemRenderer(EntityRendererProvider.Context context, float f, boolean bl) {
        super(context, f, bl);
    }

    public RubberDuckyThrownItemRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull RubberDuckyRenderState createRenderState() {
        return new RubberDuckyRenderState();
    }
}
