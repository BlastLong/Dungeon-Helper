package com.blastlong.dungeonhelper;

import com.blastlong.dungeonhelper.file.Data;
import com.blastlong.dungeonhelper.file.Settings;
import com.blastlong.dungeonhelper.gui.DungeonCooltimeGui;
import com.blastlong.dungeonhelper.gui.SkillCooltimeGui;
import com.blastlong.dungeonhelper.gui.screen.SettingsScreen;
import com.blastlong.dungeonhelper.input.IKeyMappings;
import com.blastlong.dungeonhelper.sound.ISoundManager;
import com.blastlong.dungeonhelper.util.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class DungeonHelperClient {

    private Minecraft mc;
    private static DungeonHelperClient instance;

    private ISoundManager soundManager;
    private IKeyMappings keyMappings;

    public Settings settings;
    public Data data;

    private DungeonCooltimeGui dungeonCooltimeGui;
    private SkillCooltimeGui skillCooltimeGui;

    private SettingsScreen settingsScreen;

    private Timer timer = new Timer();

    public DungeonHelperClient(ISoundManager soundManager, IKeyMappings keyMappings) {
        this.soundManager = soundManager;
        this.keyMappings = keyMappings;

        mc = Minecraft.getInstance();

        instance = this;
        settings = new Settings();
        data = settings.load();
        if(data == null || data.lastDungeonTime.length != DungeonCooltimeGui.DUNGEON_COUNT) {
            data = new Data();
            settings.save();
        }

        dungeonCooltimeGui = new DungeonCooltimeGui();
        skillCooltimeGui = new SkillCooltimeGui();
        settingsScreen = new SettingsScreen();
    }

    public void init() {

    }

    public void renderEvent(GuiGraphics guiGraphics, Component title, Component message) {
        dungeonCooltimeGui.renderTick(guiGraphics, title, timer);
        skillCooltimeGui.renderTick(guiGraphics, timer);

        timer.updateTime();
    }

    public void updateLastDashTime() {
        skillCooltimeGui.updateLastDashTime(timer);
    }

    public void updateLastUltimateTime() {
        skillCooltimeGui.updateLastUltimateTime(timer);
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