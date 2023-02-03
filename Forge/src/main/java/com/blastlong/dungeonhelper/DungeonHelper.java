package com.blastlong.dungeonhelper;

import com.blastlong.dungeonhelper.sound.SoundManager;
import com.blastlong.dungeonhelper.input.KeyMappings;
import com.blastlong.dungeonhelper.item.DungeonItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(DungeonHelper.MODID)
public class DungeonHelper {
    public static final String MODID = "dungeonhelper";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DungeonHelperClient client;

    public DungeonHelper(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        DungeonItems.ITEMS.register(eventBus);

        SoundManager soundManager = new SoundManager();
        soundManager.register();

        KeyMappings keyMapping = new KeyMappings();
        keyMapping.register();

        client = new DungeonHelperClient(soundManager, keyMapping);
        client.init();
    }
}
