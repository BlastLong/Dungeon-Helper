package com.blastlong.dungeonhelper.gui.screen;

import com.blastlong.dungeonhelper.DungeonHelperClient;
import com.blastlong.dungeonhelper.util.TextUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
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
        super(TextUtil.TextComponent("DungeonCooltimeSettingScreen"));

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

        toggleDungeonCooltimeButton = (Button)this.addRenderableWidget(new Button(getRegularX() + 5, getRegularY() + 5, 137, 20, toggleDungeonCooltimeButtonComponent, (button) -> {
            onToggleDungeonCooltimePress();
        }));

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

        toggleDungeonCooltimeOptionButtons[0] = (Button)this.addRenderableWidget(new Button(getRegularX() + 5, getRegularY() + 5 + 20 + 2, 67, 20, toggleDungeonCooltimeTextButtonComponent, btn -> {
            onToggleDungeonCooltimeOptionPress(0);
        }));

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

        toggleDungeonCooltimeOptionButtons[1] = (Button)this.addRenderableWidget(new Button(getRegularX() + 5 + 67 + 2, getRegularY() + 5 + 20 + 2, 68, 20, toggleDungeonCooltimeFadeButtonComponent, btn -> {
            onToggleDungeonCooltimeOptionPress(1);
        }));
    }

    public void render(PoseStack poseStack, int a, int b, float c) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        this.renderBackground(poseStack);
        super.render(poseStack, a, b, c);
    }

    public void renderBackground(PoseStack poseStack) {
        super.renderBackground(poseStack);

        RenderSystem.setShaderTexture(0, BG_LOCATION);
        blit(poseStack, getRegularX(), getRegularY(), 0, 0, width, height);
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
                        Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.main").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
                );
            }
            else {
                toggleDungeonCooltimeOptionButtons[1].setMessage(
                        Component.translatable("gui.dungeonhelper.dungeon_cooltime_settings.main").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
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
