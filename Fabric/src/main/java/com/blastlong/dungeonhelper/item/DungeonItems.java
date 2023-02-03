package com.blastlong.dungeonhelper.item;

import com.blastlong.dungeonhelper.DungeonHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class DungeonItems {
    public static final Item COMMON_BOOK = new Item(new FabricItemSettings().group(CreativeModeTab.TAB_MISC));
    public static final Item UNCOMMON_BOOK = new Item(new FabricItemSettings().group(CreativeModeTab.TAB_MISC));
    public static final Item RARE_BOOK = new Item(new FabricItemSettings().group(CreativeModeTab.TAB_MISC));
    public static final Item EPIC_BOOK = new Item(new FabricItemSettings().group(CreativeModeTab.TAB_MISC));
    public static final Item LEGENDARY_BOOK = new Item(new FabricItemSettings().group(CreativeModeTab.TAB_MISC));
    public static final Item MYTHIC_BOOK = new Item(new FabricItemSettings().group(CreativeModeTab.TAB_MISC));

    public static void register() {
        Registry.register(Registry.ITEM, new ResourceLocation(DungeonHelper.MODID, "common_book"), COMMON_BOOK);
        Registry.register(Registry.ITEM, new ResourceLocation(DungeonHelper.MODID, "uncommon_book"), UNCOMMON_BOOK);
        Registry.register(Registry.ITEM, new ResourceLocation(DungeonHelper.MODID, "rare_book"), RARE_BOOK);
        Registry.register(Registry.ITEM, new ResourceLocation(DungeonHelper.MODID, "epic_book"), EPIC_BOOK);
        Registry.register(Registry.ITEM, new ResourceLocation(DungeonHelper.MODID, "legendary_book"), LEGENDARY_BOOK);
        Registry.register(Registry.ITEM, new ResourceLocation(DungeonHelper.MODID, "mythic_book"), MYTHIC_BOOK);

    }
}
