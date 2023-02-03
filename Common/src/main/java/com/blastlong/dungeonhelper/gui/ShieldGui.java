package com.blastlong.dungeonhelper.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

public class ShieldGui extends GuiComponent {
    private Minecraft minecraft;
    private final int LAST_BLURING = 3;

    public ShieldGui() {
        minecraft = Minecraft.getInstance();
    }

    public void renderTick(PoseStack poseStack) {
        if(minecraft.player.hasEffect(MobEffect.byId(22))) {
            MobEffectInstance instance = minecraft.player.getEffect(MobEffect.byId(22));
            int duration = instance.getDuration();
        }
    }

    private void render(PoseStack poseStack) {

    }
}
