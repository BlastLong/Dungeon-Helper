package com.blastlong.dungeonhelper.mixin;

import com.blastlong.dungeonhelper.DungeonHelperClient;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
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

    @Inject(method = "render(Lnet/minecraft/client/gui/GuiGraphics;F)V", at = {@At("RETURN")}, cancellable = false)
    private void renderMixin(GuiGraphics guiGraphics, float tickDelta, CallbackInfo info) {
        DungeonHelperClient.getInstance().renderEvent(guiGraphics, title, overlayMessageString);
    }
}
