package com.blastlong.dungeonhelper.mixin;

import com.blastlong.dungeonhelper.DungeonHelperClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgeGui.class)
public class GuiMixin extends Gui {

    public GuiMixin(Minecraft p_232355_, ItemRenderer p_232356_) {
        super(p_232355_, p_232356_);
    }

    @Inject(method = "render(Lnet/minecraft/client/gui/GuiGraphics;F)V", at = {@At("HEAD")}, cancellable = false)
    public void renderMixin(GuiGraphics guiGraphics, float tickDelta, CallbackInfo info) {
        DungeonHelperClient.getInstance().renderEvent(guiGraphics, title, overlayMessageString);
    }
}
