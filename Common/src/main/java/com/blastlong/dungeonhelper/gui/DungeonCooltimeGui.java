package com.blastlong.dungeonhelper.gui;

import com.blastlong.dungeonhelper.DungeonHelperClient;
import com.blastlong.dungeonhelper.util.Timer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.net.URLDecoder;

public class DungeonCooltimeGui extends GuiComponent {
    private Minecraft mc;
    private DungeonHelperClient client;
    private Font font;

    private static final int DUNGEON_COUNT = 6;
    private static final ResourceLocation[] DUNGEON_ICONS = new ResourceLocation[] {
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/one_dungeon_icon.png"),
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/two_dungeon_icon.png"),
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/three_dungeon_icon.png"),
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/four_dungeon_icon.png"),
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/n_one_dungeon_icon.png"),
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/n_two_dungeon_icon.png")
    };
    private static final String[] DUNGEON_NAMES = {
            "%EA%B7%B8%EB%A3%A8%ED%8A%B8%EC%9D%98+%EA%B3%A8%EC%A7%9C%EA%B8%B0",				// 그루트의 골짜기
            "%EB%A7%9D%EB%A0%B9%EC%9D%98+%EB%AC%B4%EB%8D%A4",								// 망령의 무덤
            "%ED%98%B9%ED%95%9C%EC%9D%98+%EC%84%B1%EC%97%AD",								// 혹한의 성역
            "%EA%B5%B0%EB%8B%A8%EC%9E%A5%EC%9D%98+%EC%9A%94%EC%83%88",						// 군단장의 요새
            "%EA%B3%A0%EB%B8%94%EB%A6%B0%EC%9D%98+%EC%9A%94%EC%83%88",				        // 고블린의 요새
            "%EB%A7%9D%EC%9E%90%EC%9D%98+%EB%AC%98%EC%A7%80"		                		// 망자의 묘지
    };

    private static final ResourceLocation BLACK_ICON = new ResourceLocation("dungeonhelper", "textures/icon/black.png");

    private Component lastTitle = null;
    private long lastTutelaryTime;

    public DungeonCooltimeGui(){
        mc = Minecraft.getInstance();
        client = DungeonHelperClient.getInstance();
    }

    public void renderTick(PoseStack poseStack, Component title, Timer timer) {
        if(title != null) {
            try
            {
                if (title != lastTitle) {
                    for (int i = 0; i < 6; i++) {
                        if (title.getString().contains(URLDecoder.decode(DUNGEON_NAMES[i], "UTF-8"))) {
                            client.data.lastDungeonTime[i] = timer.getCurrentTime();
                            client.settings.save();
                            break;
                        }
                    }
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            lastTitle = title;
        }

        int[] seconds = new int[6];
        for(int i = 0; i < 6; i++) {
            seconds[i] = 3600 - (int) timer.getDifference(client.data.lastDungeonTime[i]);
            if(seconds[i] < 0)
                seconds[i] = 0;
        }

        if(client.data.toggleDungeonCooltime)
            render(poseStack, seconds);
    }

    private void render(PoseStack poseStack, int[] seconds) {
        int x = 2, y = 2;

        for(int i = 0; i < 6; i++) {
            renderCooltime(i, poseStack, x, y, seconds[i]);
        }
    }

    private void renderCooltime(int id, PoseStack poseStack, int x, int y, int second) {
        poseStack.pushPose();
        poseStack.translate(x, y + (16 + 2) * id, 0);
        poseStack.scale(16f/256f, 16f/256f, 16f/256f);

        ResourceLocation texture;
        if((texture = getDungeonTexture(id)) == null)
            return;

        RenderSystem.setShaderTexture(0, texture);
        blit(poseStack, 0, 0, 0, 0, 256, 256);

        if(client.data.toggleDungeonCooltimeFade) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderTexture(0, BLACK_ICON);
            blit(poseStack, 0, 0, 0, 0, 256, (int)(256 * (float)second / 3600f));
        }

        poseStack.scale(256f/16f, 256f/16f, 256f/16f);
        poseStack.popPose();


        if(client.data.toggleDungeonCooltimeText) {
            font = mc.font;

            int minute = second / 60;
            second -= minute * 60;

            poseStack.pushPose();
            poseStack.translate(x + 16 + 2, y + 4 + (16 + 2) * id, 0);
            poseStack.scale(1f/1.1f, 1f/1.1f, 1f/1.1f);

            drawString(poseStack, font, Component.literal(String.format("%d:%d", minute, second)), 0, 0, 0xFFFFFF);

            poseStack.scale(1.1f, 1.1f, 1.1f);
            poseStack.popPose();
        }
    }

    private ResourceLocation getDungeonTexture(int dungeon_id) {
        if(dungeon_id >= DUNGEON_COUNT)
            return null;

        return DUNGEON_ICONS[dungeon_id];
    }
}
