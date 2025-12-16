package nl.delphinity.scrumcraft2.client.renderstate;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Environment(EnvType.CLIENT)
public class SnowGolemRenderState extends LivingEntityRenderState {
    public boolean hasPumpkin;

    public SnowGolemRenderState() {
    }
}
