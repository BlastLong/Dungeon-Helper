package com.blastlong.dungeonhelper.gui.screen;

import com.blastlong.dungeonhelper.DungeonHelperClient;
import com.blastlong.dungeonhelper.util.ClassCategory;
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

public class SkillCooltimeSettingsScreen extends Screen {

    private Minecraft mc;
    private DungeonHelperClient client;

    public static final ResourceLocation BG_LOCATION = new ResourceLocation("dungeonhelper", "textures/gui/skill_cooltime_settings_background.png");

    private Button toggleSkillCooltimeButton;
    private Button classTypeButton;

    private int width, height;

    public SkillCooltimeSettingsScreen() {
        super(Component.literal("SkillCooltimeSettingScreen"));

        mc = Minecraft.getInstance();
        client = DungeonHelperClient.getInstance();

        width = 147;
        height = 52;
    }


    protected void init(){
        super.init();

        Component toggleSkillCooltimeButtonComponent;
        if(client.data.toggleSkillCooltime)
            toggleSkillCooltimeButtonComponent =
                    Component.translatable("gui.dungeonhelper.skill_cooltime_settings.main").append(
                    Component.translatable("gui.dungeonhelper.settings.on").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
            );
        else
            toggleSkillCooltimeButtonComponent =
                    Component.translatable("gui.dungeonhelper.skill_cooltime_settings.main").append(
                    Component.translatable("gui.dungeonhelper.settings.off").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
            );

        toggleSkillCooltimeButton = this.addRenderableWidget(new Button.Builder(toggleSkillCooltimeButtonComponent, btn -> onToggleSkillCooltimePress())
                .pos(getRegularX() + 5, getRegularY() + 5)
                .size(137, 20)
                .build());

        Component classTypeButtonComponent = Component.empty();
        if(client.data.classType == ClassCategory.ASSASSIN)
            classTypeButtonComponent =
                    Component.translatable("gui.dungeonhelper.skill_cooltime_settings.classType").append(
                    Component.translatable("gui.dungeonhelper.skill_cooltime_settings.classType.assassin").withStyle(Style.EMPTY.applyFormat(ChatFormatting.DARK_RED).withBold(true))
                    );
        else if(client.data.classType == ClassCategory.DRAGON_WARRIOR)
            classTypeButtonComponent =
                    Component.translatable("gui.dungeonhelper.skill_cooltime_settings.classType").append(
                    Component.translatable("gui.dungeonhelper.skill_cooltime_settings.classType.dragon_warrior").withStyle(Style.EMPTY.applyFormat(ChatFormatting.DARK_PURPLE).withBold(true))
                    );

        classTypeButton = this.addRenderableWidget(new Button.Builder(classTypeButtonComponent, btn -> onClassTypePress())
                .pos(getRegularX() + 5, getRegularY() + 5 + 20 + 2)
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

        guiGraphics.blit(BG_LOCATION, getRegularX(), getRegularY(), 0, 0, width, height);
    }

    private void onToggleSkillCooltimePress() {
        client.data.toggleSkillCooltime = !client.data.toggleSkillCooltime;

        if(client.data.toggleSkillCooltime) {
            toggleSkillCooltimeButton.setMessage(
                    Component.translatable("gui.dungeonhelper.skill_cooltime_settings.main").append(
                    Component.translatable("gui.dungeonhelper.settings.on").withStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true))
            ));
        }
        else {
            toggleSkillCooltimeButton.setMessage(
                    Component.translatable("gui.dungeonhelper.skill_cooltime_settings.main").append(
                    Component.translatable("gui.dungeonhelper.settings.off").withStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true))
            ));
        }

        client.settings.save();
    }

    private void onClassTypePress() {
        if(client.data.classType == ClassCategory.ASSASSIN)
            client.data.classType = ClassCategory.DRAGON_WARRIOR;
        else if(client.data.classType == ClassCategory.DRAGON_WARRIOR)
            client.data.classType = ClassCategory.ASSASSIN;

        if(client.data.classType == ClassCategory.ASSASSIN) {
            classTypeButton.setMessage(
                    Component.translatable("gui.dungeonhelper.skill_cooltime_settings.classType").append(
                    Component.translatable("gui.dungeonhelper.skill_cooltime_settings.classType.assassin").withStyle(Style.EMPTY.applyFormat(ChatFormatting.DARK_RED).withBold(true))
                    ));
        }
        else {
            classTypeButton.setMessage(
                    Component.translatable("gui.dungeonhelper.skill_cooltime_settings.classType").append(
                    Component.translatable("gui.dungeonhelper.skill_cooltime_settings.classType.dragon_warrior").withStyle(Style.EMPTY.applyFormat(ChatFormatting.DARK_PURPLE).withBold(true))
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
