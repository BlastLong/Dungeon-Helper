package com.blastlong.dungeonhelper.gui;

import com.blastlong.dungeonhelper.DungeonHelperClient;
import com.blastlong.dungeonhelper.util.TextUtil;
import com.blastlong.dungeonhelper.util.Timer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.resources.ResourceLocation;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class TutelaryGui extends GuiComponent {
    private Minecraft mc;
    private final DungeonHelperClient client;

    private static final ResourceLocation TUTELARY_ICON = new ResourceLocation("dungeonhelper", "textures/icon/tutelary_icon.png");
    private static final float TUTELARY_COOLTIME = 14f;
    private static final String TUTELARY_STRING = "%EC%88%98%ED%98%B8%EC%9E%90";

    private Component lastMessage;
    private long lastTutelaryTime;

    public TutelaryGui(){
        mc = Minecraft.getInstance();
        client = DungeonHelperClient.getInstance();
    }

    public void renderTick(PoseStack poseStack, Component message, Timer timer) {
        if(client.data.toggleTutelar && client.data.toggleTutelarIcon)
            render(poseStack, 1 - timer.getDifference(lastTutelaryTime) / TUTELARY_COOLTIME);

        if(message == null)
            return;

        try {
            if (message.getString().contains(URLDecoder.decode(TUTELARY_STRING, StandardCharsets.UTF_8)) && message != lastMessage && (timer.getDifference(lastTutelaryTime) > TUTELARY_COOLTIME)) {
                if (client.data.toggleTutelar) {
                    if (client.data.toggleTutelarTitle) {
                        MutableComponent newcomp = MutableComponent.create(new LiteralContents(""));
                        for (Component sibling : message.getSiblings()) {
                            newcomp.append(sibling);
                        }
                        newcomp.append(TextUtil.TextComponent(" "));
                        mc.gui.setTitle(newcomp);
                        mc.player.displayClientMessage(TextUtil.TextComponent(" "), true);
                    }

                    if (client.data.toggleTutelarSound && mc.player != null) {
                        client.getSoundManager().playSound("tutelary_sound", 0.4f);
                    }
                }

                lastTutelaryTime = timer.getCurrentTime();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        lastMessage = message;
    }

    public void render(PoseStack poseStack, float percentage) {
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        RenderSystem.setShaderTexture(0, TUTELARY_ICON);
        int xOffset = 0; // 52
        blit(poseStack, screenWidth / 2 + 95 + xOffset, screenHeight - 32 + 3, 0, 0, 24, 24);
        if(percentage < 1)
            blit(poseStack, screenWidth / 2 + 95 + xOffset, screenHeight - 32 + 3, 24, 0, 24, (int)(24 * percentage));
        if(percentage <= 0)
            blit(poseStack, screenWidth / 2 + 95 - 2 + xOffset, screenHeight - 32 + 3 - 2, 48, 0, 28, 28);
    }
}
