package com.blastlong.dungeonhelper.gui.screen;

import com.blastlong.dungeonhelper.DungeonHelperClient;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;

public class CustomEnchantRenderSetingsScreen extends Screen {

    private Minecraft mc;
    private DungeonHelperClient client;

    public static final ResourceLocation BG_LOCATION = new ResourceLocation("dungeonhelper", "textures/gui/custom_enchant_render_settings_background.png");

    private Button toggleCustomEnchantRenderButton;

    private int width, height;

    protected CustomEnchantRenderSetingsScreen() {
        super(Component.literal("CustomEnchantRenderSetingsScreen"));

        mc = Minecraft.getInstance();
        client = DungeonHelperClient.getInstance();

        width = 147;
        height = 30;
    }

    protected void init() {
        super.init();

        Component toggleCustomEnchantRenderButtonComponent;
        if (client.data.toggleCustomEnchantRender)
            toggleCustomEnchantRenderButtonComponent = 
                     Component.translatable("gui.dungeonhelper.custom_enchant_render_settings.main").append(
                     Component.translatable("gui.dungeonhelper.settings.on").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
            );
        else
            toggleCustomEnchantRenderButtonComponent = 
                     Component.translatable("gui.dungeonhelper.custom_enchant_render_settings.main").append(
                     Component.translatable("gui.dungeonhelper.settings.off").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
            );

        toggleCustomEnchantRenderButton = this.addRenderableWidget(new Button.Builder(toggleCustomEnchantRenderButtonComponent, btn -> onToggleCustomEnchantRenderPress())
                .pos(getRegularX() + 5, getRegularY() + 5)
                .size(137, 20)
                .build());
    }

    public void render(GuiGraphics guiGraphics, int a, int b, float c) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        this.renderBackground(guiGraphics);
        super.render(guiGraphics, a, b, c);
    }

    public void renderBackground(GuiGraphics guiGraphics) {
        super.renderBackground(guiGraphics);

        // RenderSystem.setShaderTexture(0, );
        guiGraphics.blit(BG_LOCATION, getRegularX(), getRegularY(), 0, 0, width, height);
        
    }

    private void onToggleCustomEnchantRenderPress() {
        client.data.toggleCustomEnchantRender = !client.data.toggleCustomEnchantRender;

        if(client.data.toggleCustomEnchantRender) {
            toggleCustomEnchantRenderButton.setMessage(
                     Component.translatable("gui.dungeonhelper.custom_enchant_render_settings.main").append(
                     Component.translatable("gui.dungeonhelper.settings.on").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
            ));
        }
        else {
            toggleCustomEnchantRenderButton.setMessage(
                     Component.translatable("gui.dungeonhelper.custom_enchant_render_settings.main").append(
                     Component.translatable("gui.dungeonhelper.settings.off").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
            ));
        }

        client.settings.save();
    }

    int getRegularX() {
        return  mc.getWindow().getGuiScaledWidth() / 2 - width / 2;
    }

    int getRegularY() {
        return mc.getWindow().getGuiScaledHeight() / 2 - height / 2;
    }
}
