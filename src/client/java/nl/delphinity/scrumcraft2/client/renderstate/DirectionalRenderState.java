package nl.delphinity.scrumcraft2.client.renderstate;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public abstract class DirectionalRenderState extends EntityRenderState {
    public float xRot;
    public float yRot;
    public final ItemStackRenderState item = new ItemStackRenderState();
}
