package com.blastlong.dungeonhelper.mixin;

import com.blastlong.dungeonhelper.DungeonHelperClient;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Shadow
    private Component title;

    @Shadow
    private Component overlayMessageString;

    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;F)V", at = {@At("RETURN")}, cancellable = false)
    private void renderMixin(PoseStack poseStack, float tickDelta, CallbackInfo info) {
        DungeonHelperClient.getInstance().renderEvent(poseStack, title, overlayMessageString);
    }
}
