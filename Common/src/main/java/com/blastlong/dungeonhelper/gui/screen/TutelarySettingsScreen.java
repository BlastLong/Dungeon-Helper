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

public class TutelarySettingsScreen extends Screen {
    private Minecraft mc;
    private final DungeonHelperClient client;

    private static final ResourceLocation BACKGROUND_LOCATION = new ResourceLocation("dungeonhelper", "textures/gui/tutelary_settings_background.png");

    private Button toggleTutelarButton;
    private Button[] toggleTutelarOptionButtons = new Button[3];	// sound, title, icon

    private final int width;
    private final int height;

    public TutelarySettingsScreen() {
        super(TextUtil.TextComponent("TutelarySettingScreen"));

        mc = Minecraft.getInstance();
        client = DungeonHelperClient.getInstance();

        width = 147;
        height = 52;
    }

    protected void init(){
        super.init();

        Component toggleTutelarButtonComponent;
        if(client.data.toggleTutelar)
            toggleTutelarButtonComponent = 
                    Component.translatable("gui.dungeonhelper.tutelary_settings.main").append(
                    Component.translatable("gui.dungeonhelper.settings.on").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
            );
        else
            toggleTutelarButtonComponent = 
                    Component.translatable("gui.dungeonhelper.tutelary_settings.main").append(
                    Component.translatable("gui.dungeonhelper.settings.off").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
            );

        toggleTutelarButton = (Button)this.addRenderableWidget(new Button(getRegularX() + 5, getRegularY() + 5, 137, 20, toggleTutelarButtonComponent, btn -> {
            onToggleTutelarPress();
        }));

        // Icon
        Component toggleTutelarIconButtonComponent;
        if(client.data.toggleTutelarIcon)
            toggleTutelarIconButtonComponent = 
                    Component.translatable("gui.dungeonhelper.tutelary_settings.icon").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
                    ;
        else
            toggleTutelarIconButtonComponent = 
                    Component.translatable("gui.dungeonhelper.tutelary_settings.icon").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
                    ;

        toggleTutelarOptionButtons[0] = (Button)this.addRenderableWidget(new Button(getRegularX() + 5, getRegularY() + 5 + 20 + 2, 44, 20, toggleTutelarIconButtonComponent, btn -> {
            onToggleTutelarOptionPress(0);
        }));

        // Title
        Component toggleTutelarTitleButtonComponent;
        if(client.data.toggleTutelarTitle)
            toggleTutelarTitleButtonComponent = 
                    Component.translatable("gui.dungeonhelper.tutelary_settings.title").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
            ;
        else
            toggleTutelarTitleButtonComponent = 
                    Component.translatable("gui.dungeonhelper.tutelary_settings.title").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
            ;

        toggleTutelarOptionButtons[1] = (Button)this.addRenderableWidget(new Button(getRegularX() + 5 + 44 + 2, getRegularY() + 5 + 20 + 2, 45, 20, toggleTutelarTitleButtonComponent, btn -> {
            onToggleTutelarOptionPress(1);
        }));

        // Sound
        Component toggleTutelarSoundButtonComponent;
        if(client.data.toggleTutelarSound)
            toggleTutelarSoundButtonComponent = 
                    Component.translatable("gui.dungeonhelper.tutelary_settings.sound").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
            ;
        else
            toggleTutelarSoundButtonComponent = 
                    Component.translatable("gui.dungeonhelper.tutelary_settings.sound").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
            ;

        toggleTutelarOptionButtons[2] = (Button)this.addRenderableWidget(new Button(getRegularX() + 5 + 44 + 2 + 45 + 2, getRegularY() + 5 + 20 + 2, 44, 20, toggleTutelarSoundButtonComponent, btn -> {
            onToggleTutelarOptionPress(2);
        }));
    }

    public void render(PoseStack poseStack, int a, int b, float c) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        this.renderBackground(poseStack);
        super.render(poseStack, a, b, c);
    }

    public void renderBackground(PoseStack poseStack) {
        super.renderBackground(poseStack);

        RenderSystem.setShaderTexture(0, BACKGROUND_LOCATION);
        blit(poseStack, getRegularX(), getRegularY(), 0, 0, width, height);
    }

    private void onToggleTutelarPress() {
        client.data.toggleTutelar = !client.data.toggleTutelar;

        if(client.data.toggleTutelar) {
            toggleTutelarButton.setMessage(
                    Component.translatable("gui.dungeonhelper.tutelary_settings.main").append(
                    Component.translatable("gui.dungeonhelper.settings.on").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
            ));
        }
        else {
            toggleTutelarButton.setMessage(
                    Component.translatable("gui.dungeonhelper.tutelary_settings.main").append(
                    Component.translatable("gui.dungeonhelper.settings.off").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
            ));
        }

        client.settings.save();
    }

    private void onToggleTutelarOptionPress(int id) {
        if(id == 0) {
            client.data.toggleTutelarIcon = !client.data.toggleTutelarIcon;

            if(client.data.toggleTutelarIcon) {
                toggleTutelarOptionButtons[0].setMessage(
                        Component.translatable("gui.dungeonhelper.tutelary_settings.icon").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
                );
            }
            else {
                toggleTutelarOptionButtons[0].setMessage(
                        Component.translatable("gui.dungeonhelper.tutelary_settings.icon").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
                );
            }
        }
        else if(id == 1) {
            client.data.toggleTutelarTitle = !client.data.toggleTutelarTitle;

            if(client.data.toggleTutelarTitle) {
                toggleTutelarOptionButtons[1].setMessage(
                        Component.translatable("gui.dungeonhelper.tutelary_settings.title").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
                );
            }
            else {
                toggleTutelarOptionButtons[1].setMessage(
                        Component.translatable("gui.dungeonhelper.tutelary_settings.title").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
                );
            }
        }
        else if(id == 2) {
            client.data.toggleTutelarSound = !client.data.toggleTutelarSound;

            if(client.data.toggleTutelarSound) {
                toggleTutelarOptionButtons[2].setMessage(
                        Component.translatable("gui.dungeonhelper.tutelary_settings.sound").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
                );
            }
            else {
                toggleTutelarOptionButtons[2].setMessage(
                        Component.translatable("gui.dungeonhelper.tutelary_settings.sound").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
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