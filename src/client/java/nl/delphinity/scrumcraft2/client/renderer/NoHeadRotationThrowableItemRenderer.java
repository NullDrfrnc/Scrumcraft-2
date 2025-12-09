package nl.delphinity.scrumcraft2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ItemSupplier;

public class NoHeadRotationThrowableItemRenderer<T extends Entity & ItemSupplier> extends ThrownItemRenderer<T> {
    public NoHeadRotationThrowableItemRenderer(EntityRendererProvider.Context context, float f, boolean bl) {
        super(context, f, bl);
    }

    public NoHeadRotationThrowableItemRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
}
