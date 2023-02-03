package com.blastlong.dungeonhelper;

import com.blastlong.dungeonhelper.file.Data;
import com.blastlong.dungeonhelper.file.Settings;
import com.blastlong.dungeonhelper.gui.DungeonCooltimeGui;
import com.blastlong.dungeonhelper.gui.screen.SettingsScreen;
import com.blastlong.dungeonhelper.input.IKeyMappings;
import com.blastlong.dungeonhelper.sound.ISoundManager;
import com.blastlong.dungeonhelper.util.Timer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class DungeonHelperClient {

    private Minecraft mc;
    private static DungeonHelperClient instance;

    private ISoundManager soundManager;
    private IKeyMappings keyMappings;

    public Settings settings;
    public Data data;

    private DungeonCooltimeGui dungeonCooltimeGui;

    private SettingsScreen settingsScreen;

    private Timer timer = new Timer();

    public DungeonHelperClient(ISoundManager soundManager, IKeyMappings keyMappings) {
        this.soundManager = soundManager;
        this.keyMappings = keyMappings;

        mc = Minecraft.getInstance();
        instance = this;
        settings = new Settings();
        data = settings.load();
        if(data == null) {
            data = new Data();
            settings.save();
        }

        dungeonCooltimeGui = new DungeonCooltimeGui();
        settingsScreen = new SettingsScreen();
    }

    public void init() {

    }

    public void renderEvent(PoseStack poseStack, Component title, Component message) {
        dungeonCooltimeGui.renderTick(poseStack, title, timer);

        timer.updateTime();
    }

    public SettingsScreen getSettingsScreen() {
        return settingsScreen;
    }

    public ISoundManager getSoundManager() {
        return soundManager;
    }

    public static DungeonHelperClient getInstance(){
        return instance;
    }
}