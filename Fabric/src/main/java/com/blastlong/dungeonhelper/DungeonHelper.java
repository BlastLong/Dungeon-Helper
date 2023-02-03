package com.blastlong.dungeonhelper;

import com.blastlong.dungeonhelper.input.KeyMappings;
import com.blastlong.dungeonhelper.sound.SoundManager;
import com.blastlong.dungeonhelper.item.DungeonItems;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

public class DungeonHelper implements ModInitializer {

    public static final String MODID = "dungeonhelper";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DungeonHelperClient client;

    @Override
    public void onInitialize() {
        SoundManager soundManager = new SoundManager();
        KeyMappings keyMappings = new KeyMappings();

        client = new DungeonHelperClient(soundManager, keyMappings);
        client.init();

        soundManager.register();
        keyMappings.register();
        DungeonItems.register();
    }
}
