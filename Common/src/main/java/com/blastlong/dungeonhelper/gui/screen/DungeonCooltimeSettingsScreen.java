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

public class DungeonCooltimeSettingsScreen extends Screen {

    private Minecraft mc;
    private DungeonHelperClient client;
    
    public static final ResourceLocation BG_LOCATION = new ResourceLocation("dungeonhelper", "textures/gui/dungeon_cooltime_settings_background.png");

    private Button toggleDungeonCooltimeButton;
    private Button[] toggleDungeonCooltimeOptionButtons = new Button[2];	// text, fade

    private int width, height;

    public DungeonCooltimeSettingsScreen() {
        super(Component.literal("DungeonCooltimeSettingScreen"));

        mc = Minecraft.getInstance();
        client = DungeonHelperClient.getInstance();

        width = 147;
        height = 52;
    }


    protected void init(){
        super.init();

        Component toggleDungeonCooltimeButtonComponent;
        if(client.data.toggleDungeonCooltime)
            toggleDungeonCooltimeButtonComponent = 
                    Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.main").append(
                    Component.translatable("gui.dungeonhelper.settings.on").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
            );
        else
            toggleDungeonCooltimeButtonComponent = 
                    Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.main").append(
                    Component.translatable("gui.dungeonhelper.settings.off").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
            );

        toggleDungeonCooltimeButton = this.addRenderableWidget(new Button.Builder(toggleDungeonCooltimeButtonComponent, btn -> onToggleDungeonCooltimePress())
                .pos(getRegularX() + 5, getRegularY() + 5)
                .size(137, 20)
                .build());

        // Text
        Component toggleDungeonCooltimeTextButtonComponent;
        if(client.data.toggleDungeonCooltimeText)
            toggleDungeonCooltimeTextButtonComponent = 
                    Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.text").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
            ;
        else
            toggleDungeonCooltimeTextButtonComponent = 
                    Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.text").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
            ;

        toggleDungeonCooltimeOptionButtons[0] = this.addRenderableWidget(new Button.Builder(toggleDungeonCooltimeTextButtonComponent, btn -> onToggleDungeonCooltimeOptionPress(0))
                .pos(getRegularX() + 5, getRegularY() + 5 + 20 + 2)
                .size(67, 20)
                .build());

        // Fade
        Component toggleDungeonCooltimeFadeButtonComponent;
        if(client.data.toggleDungeonCooltimeFade)
            toggleDungeonCooltimeFadeButtonComponent = 
                    Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.fade").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
            ;
        else
            toggleDungeonCooltimeFadeButtonComponent = 
                    Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.fade").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
            ;

        toggleDungeonCooltimeOptionButtons[1] = this.addRenderableWidget(new Button.Builder(toggleDungeonCooltimeFadeButtonComponent, btn -> onToggleDungeonCooltimeOptionPress(1))
                .pos(getRegularX() + 5 + 67 + 2, getRegularY() + 5 + 20 + 2)
                .size(68, 20)
                .build());
    }

    public void render(GuiGraphics guiGraphics, int a, int b, float c) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        this.renderBackground(guiGraphics);
        super.render(guiGraphics, a, b, c);
    }

    public void renderBackground(GuiGraphics guiGraphics) {
        super.renderBackground(guiGraphics);

        guiGraphics.blit(BG_LOCATION, getRegularX(), getRegularY(), 0, 0, width, height);
    }

    private void onToggleDungeonCooltimePress() {
        client.data.toggleDungeonCooltime = !client.data.toggleDungeonCooltime;

        if(client.data.toggleDungeonCooltime) {
            toggleDungeonCooltimeButton.setMessage(
                    Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.main").append(
                    Component.translatable("gui.dungeonhelper.settings.on").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
            ));
        }
        else {
            toggleDungeonCooltimeButton.setMessage(
                    Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.main").append(
                    Component.translatable("gui.dungeonhelper.settings.off").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
            ));
        }

        client.settings.save();
    }

    private void onToggleDungeonCooltimeOptionPress(int id) {
        if(id == 0) {
            client.data.toggleDungeonCooltimeText = !client.data.toggleDungeonCooltimeText;

            if(client.data.toggleDungeonCooltimeText) {
                toggleDungeonCooltimeOptionButtons[0].setMessage(
                        Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.text").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
                );
            }
            else {
                toggleDungeonCooltimeOptionButtons[0].setMessage(
                        Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.text").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
                );
            }
        }
        else if(id == 1) {
            client.data.toggleDungeonCooltimeFade = !client.data.toggleDungeonCooltimeFade;

            if(client.data.toggleDungeonCooltimeFade) {
                toggleDungeonCooltimeOptionButtons[1].setMessage(
                        Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.fade").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
                );
            }
            else {
                toggleDungeonCooltimeOptionButtons[1].setMessage(
                        Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.fade").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
                );
            }
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
