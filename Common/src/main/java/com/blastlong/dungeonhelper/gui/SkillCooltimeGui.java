package com.blastlong.dungeonhelper.gui;

import com.blastlong.dungeonhelper.DungeonHelperClient;
import com.blastlong.dungeonhelper.util.ClassCategory;
import com.blastlong.dungeonhelper.util.Timer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class SkillCooltimeGui {

    private Minecraft mc;
    private DungeonHelperClient client;

    private static final ResourceLocation WIDGETS = new ResourceLocation("textures/gui/widgets.png");
    private static final ResourceLocation BLACK_ICON = new ResourceLocation("dungeonhelper", "textures/icon/black.png");
    private static final ResourceLocation DRAGON_DASH_TEXTURE = new ResourceLocation("dungeonhelper", "textures/icon/skill/dragon_dash.png");
    private static final ResourceLocation DRAGON_SMASH_TEXTURE = new ResourceLocation("dungeonhelper", "textures/icon/skill/dragon_smash.png");
    private static final ResourceLocation BLADE_DASH_TEXTURE = new ResourceLocation("dungeonhelper", "textures/icon/skill/blade_dash.png");
    private static final ResourceLocation BLADE_DANCE_TEXTURE = new ResourceLocation("dungeonhelper", "textures/icon/skill/blade_dance.png");
    private static final ResourceLocation AGILE_STRIKE_TEXTURE = new ResourceLocation("dungeonhelper", "textures/icon/skill/agile_strike.png");
    private static final ResourceLocation MULTIPLE_BLOW_TEXTURE = new ResourceLocation("dungeonhelper", "textures/icon/skill/multiple_blow.png");

    private static final int SKILL_GUI_SIZE = 16;

    private static final int BLADE_DASH_COOLTIME = 3;
    private static final float BLADE_DANCE_COOLTIME = 30f;
    private static final int DRAGON_DASH_COOLTIME = 4;
    private static final float DRAGON_SMASH_COOLTIME = 27.5f;
    private static final int AGILE_STRIKE_COOLTIME = 2;
    private static final float MULTIPLE_BLOW_COOLTIME = 30f;

    private long lastDashTime = 0;
    private long lastUltimateTime = 0;

    private float leftDashTime;
    private float leftUltimateTime;

    public SkillCooltimeGui() {
        mc = Minecraft.getInstance();
        client = DungeonHelperClient.getInstance();
    }

    public void updateLastDashTime(Timer timer) {
        lastDashTime = timer.getCurrentTime();
    }

    public void updateLastUltimateTime(Timer timer) {
        lastUltimateTime = timer.getCurrentTime();
    }

    public void renderTick(GuiGraphics guiGraphics, Timer timer) {
        if(!client.data.toggleSkillCooltime)
            return;

        if(client.data.classType == ClassCategory.ASSASSIN) {
            leftDashTime = BLADE_DASH_COOLTIME - timer.getDifference(lastDashTime);
            leftUltimateTime = BLADE_DANCE_COOLTIME - timer.getDifference(lastUltimateTime);
        }
        else if(client.data.classType == ClassCategory.DRAGON_WARRIOR) {
            leftDashTime = DRAGON_DASH_COOLTIME - timer.getDifference(lastDashTime);
            leftUltimateTime = DRAGON_SMASH_COOLTIME - timer.getDifference(lastUltimateTime);
        }
        else if(client.data.classType == ClassCategory.MARTIAL_ARTIST) {
            leftDashTime = AGILE_STRIKE_COOLTIME - timer.getDifference(lastDashTime);
            leftUltimateTime = MULTIPLE_BLOW_COOLTIME - timer.getDifference(lastUltimateTime);
        }

        leftDashTime = Math.max(leftDashTime, 0);
        leftUltimateTime = Math.max(leftUltimateTime, 0);

        render(guiGraphics);
    }

    private void render(GuiGraphics guiGraphics) {
        int xOffset = 98;
        renderSkillTexture(guiGraphics, xOffset);
        renderSkillCooltime(guiGraphics, xOffset);
    }

    private void renderSkillTexture(GuiGraphics guiGraphics, int xOffset) {
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        guiGraphics.blit(WIDGETS, screenWidth / 2 + xOffset, screenHeight - 22, 24, 23, 22, 22);
        guiGraphics.blit(WIDGETS, screenWidth / 2 + xOffset + 22 + 2, screenHeight - 22, 24, 23, 22, 22);

        ResourceLocation texture = null;
        if(client.data.classType == ClassCategory.ASSASSIN)
            texture = BLADE_DASH_TEXTURE;
        else if(client.data.classType == ClassCategory.DRAGON_WARRIOR)
            texture = DRAGON_DASH_TEXTURE;
        else if(client.data.classType == ClassCategory.MARTIAL_ARTIST)
            texture = AGILE_STRIKE_TEXTURE;
        drawSkillTexture(guiGraphics, texture, screenWidth / 2 + xOffset + 3, screenHeight - 22 + 3);

        if(client.data.classType == ClassCategory.ASSASSIN)
            texture = BLADE_DANCE_TEXTURE;
        else if(client.data.classType == ClassCategory.DRAGON_WARRIOR)
            texture = DRAGON_SMASH_TEXTURE;
        else if(client.data.classType == ClassCategory.MARTIAL_ARTIST)
            texture = MULTIPLE_BLOW_TEXTURE;
        drawSkillTexture(guiGraphics, texture,screenWidth / 2 + xOffset + 22 + 2 + 3, screenHeight - 22 + 3);
    }

    private void renderSkillCooltime(GuiGraphics guiGraphics, int xOffset) {
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        int x = screenWidth / 2 + xOffset + 3;
        int y = screenHeight - 22 + 4;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        // RenderSystem.setShaderTexture(0, BLACK_ICON);

        float dashCooltime = 10, ultimateCooltime = 10;
        if(client.data.classType == ClassCategory.ASSASSIN) {
            dashCooltime = BLADE_DASH_COOLTIME;
            ultimateCooltime = BLADE_DANCE_COOLTIME;
        }
        else if(client.data.classType == ClassCategory.DRAGON_WARRIOR) {
            dashCooltime = DRAGON_DASH_COOLTIME;
            ultimateCooltime = DRAGON_SMASH_COOLTIME;
        }
        else if(client.data.classType == ClassCategory.MARTIAL_ARTIST) {
            dashCooltime = AGILE_STRIKE_COOLTIME;
            ultimateCooltime = MULTIPLE_BLOW_COOLTIME;
        }
        guiGraphics.blit(BLACK_ICON, x, y + (int) (SKILL_GUI_SIZE * (1 - leftDashTime / dashCooltime)), 0, 0, SKILL_GUI_SIZE, (int) (SKILL_GUI_SIZE * (leftDashTime / dashCooltime)));
        guiGraphics.blit(BLACK_ICON, x + 22 + 2, y + (int) (SKILL_GUI_SIZE * (1 - leftUltimateTime / ultimateCooltime)), 0, 0, SKILL_GUI_SIZE, (int) (SKILL_GUI_SIZE * (leftUltimateTime / ultimateCooltime)));

        PoseStack poseStack = guiGraphics.pose();
        if(isDashCooltime()) {
            poseStack.pushPose();
            poseStack.translate(x + 8, y + 4, 0);
            poseStack.scale(1f/1.1f, 1f/1.1f, 1f/1.1f);

            guiGraphics.drawCenteredString(mc.font, Component.literal(String.valueOf((int)(leftDashTime * 10) / 10f)), 0, 0, 0xFFFFFF);

            poseStack.popPose();
        }

        if(isUltimateCooltime()) {
            poseStack.pushPose();
            poseStack.translate(x + 2 + 22 + 8, y + 4, 0);
            poseStack.scale(1f/1.1f, 1f/1.1f, 1f/1.1f);

            guiGraphics.drawCenteredString(mc.font, Component.literal(String.valueOf((int) leftUltimateTime)), 0, 0, 0xFFFFFF);

            poseStack.popPose();
        }
    }

    private void drawSkillTexture(GuiGraphics guiGraphics, ResourceLocation texture, int x, int y) {
        float scaleRatio = SKILL_GUI_SIZE / 256f;

        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.translate(x, y, 0);
        poseStack.scale(scaleRatio, scaleRatio, 1);

        guiGraphics.blit(texture, 0, 0, 0, 0, 256, 256);

        poseStack.popPose();
    }

    public boolean isDashCooltime() {
        return leftDashTime > 0;
    }

    public boolean isUltimateCooltime() {
        return leftUltimateTime > 0;
    }
}
