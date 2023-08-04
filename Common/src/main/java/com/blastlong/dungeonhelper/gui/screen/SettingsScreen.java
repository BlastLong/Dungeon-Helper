package com.blastlong.dungeonhelper.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class SettingsScreen extends Screen {
    private Minecraft mc;

    private static final ResourceLocation BACKGROUND_LOCATION = new ResourceLocation("dungeonhelper", "textures/gui/dungeonhelper_settings_background.png");

    private final int width;
    private final int height;

    public SettingsScreen() {
        super(Component.literal("DungeonHelperSettingScreen"));

        mc = Minecraft.getInstance();

        width = 147;
        height = 74;
    }

    protected void init(){
        super.init();

        this.addRenderableWidget(new Button.Builder(Component.translatable("gui.dungeonhelper.settings.dungeon_cooltime_settings"), btn -> onDungeonCooltimeSettingsPress())
                .pos(getRegularX() + 5, getRegularY() + 5)
                .size(137, 20)
                .build());

        this.addRenderableWidget(new Button.Builder(Component.translatable("gui.dungeonhelper.settings.custom_enchant_render_settings"), btn -> onCustomEnchantRenderSettingsPress())
                .pos(getRegularX() + 5, getRegularY() + 5 + 20 + 2)
                .size(137, 20)
                .build());

        this.addRenderableWidget(new Button.Builder(Component.translatable("gui.dungeonhelper.settings.skill_cooltime_settings"), btn -> onSkillCooltimeSettingsPress())
                .pos(getRegularX() + 5, getRegularY() + 5 + (20 + 2) * 2)
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

        guiGraphics.blit(BACKGROUND_LOCATION, getRegularX(), getRegularY(), 0, 0, width, height);
    }

    private void onDungeonCooltimeSettingsPress() {
        mc.setScreen(new DungeonCooltimeSettingsScreen());
    }

    private void onCustomEnchantRenderSettingsPress() {
        mc.setScreen(new CustomEnchantRenderSetingsScreen());
    }

    private void onSkillCooltimeSettingsPress() {
        mc.setScreen(new SkillCooltimeSettingsScreen());
    }

    int getRegularX() {
        return  mc.getWindow().getGuiScaledWidth() / 2 - width / 2;
    }

    int getRegularY() {
        return mc.getWindow().getGuiScaledHeight() / 2 - height / 2;
    }
}
