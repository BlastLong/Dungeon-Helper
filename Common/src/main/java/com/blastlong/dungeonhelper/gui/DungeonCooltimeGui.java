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

public class DungeonCooltimeGui extends GuiComponent {
    private Minecraft mc;
    private DungeonHelperClient client;
    private Font font;

    public static final int DUNGEON_COUNT = 7;
    private static final ResourceLocation[] DUNGEON_ICONS = new ResourceLocation[] {
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/one_dungeon_icon.png"),
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/two_dungeon_icon.png"),
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/three_dungeon_icon.png"),
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/four_dungeon_icon.png"),
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/n_one_dungeon_icon.png"),
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/n_two_dungeon_icon.png"),
            new ResourceLocation("dungeonhelper", "textures/icon/dungeon/n_three_dungeon_icon.png")
    };
    private static final String[] DUNGEON_NAMES = {
            "그루트의 골짜기",
            "망령의 무덤",
            "혹한의 성역",
            "군단장의 요새",
            "고블린의 요새",
            "망자의 묘지",
            "마천루 : 네온시티"
    };

    private static final ResourceLocation BLACK_ICON = new ResourceLocation("dungeonhelper", "textures/icon/black.png");

    private Component lastTitle = null;

    public DungeonCooltimeGui(){
        mc = Minecraft.getInstance();
        client = DungeonHelperClient.getInstance();
    }

    public void renderTick(PoseStack poseStack, Component title, Timer timer) {
        if(title != null) {
            try
            {
                if (title != lastTitle) {
                    for (int i = 0; i < DUNGEON_COUNT; i++) {
                        if (title.getString().contains(DUNGEON_NAMES[i])) {
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

        int[] seconds = new int[DUNGEON_COUNT];
        for(int i = 0; i < seconds.length; i++) {
            seconds[i] = 3600 - (int) timer.getDifference(client.data.lastDungeonTime[i]);
            if(seconds[i] < 0)
                seconds[i] = 0;
        }

        if(client.data.toggleDungeonCooltime)
            render(poseStack, seconds);
    }

    private void render(PoseStack poseStack, int[] seconds) {
        int x = 2, y = 2;

        for(int i = 0; i < DUNGEON_COUNT; i++) {
            renderDungeonsAndCooltime(i, poseStack, x, y, seconds[i]);
        }
    }

    private void renderDungeonsAndCooltime(int id, PoseStack poseStack, int x, int y, int second) {
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
