package com.blastlong.dungeonhelper.item;

import com.blastlong.dungeonhelper.DungeonHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.impl.itemgroup.MinecraftItemGroups;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class DungeonItems {
    public static final Item COMMON_BOOK = new Item(new Item.Properties());
    public static final Item UNCOMMON_BOOK = new Item(new Item.Properties());
    public static final Item RARE_BOOK = new Item(new Item.Properties());
    public static final Item EPIC_BOOK = new Item(new Item.Properties());
    public static final Item LEGENDARY_BOOK = new Item(new Item.Properties());
    public static final Item MYTHIC_BOOK = new Item(new Item.Properties());

    public static void register() {
        /*
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(DungeonHelper.MODID, "common_book"), COMMON_BOOK);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(DungeonHelper.MODID, "uncommon_book"), UNCOMMON_BOOK);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(DungeonHelper.MODID, "rare_book"), RARE_BOOK);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(DungeonHelper.MODID, "epic_book"), EPIC_BOOK);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(DungeonHelper.MODID, "legendary_book"), LEGENDARY_BOOK);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(DungeonHelper.MODID, "mythic_book"), MYTHIC_BOOK);
        */

        ItemGroupEvents.modifyEntriesEvent(MinecraftItemGroups.TOOLS_ID).register(entries -> entries.addAfter(Items.MUSIC_DISC_PIGSTEP, COMMON_BOOK));
        ItemGroupEvents.modifyEntriesEvent(MinecraftItemGroups.TOOLS_ID).register(entries -> entries.addAfter(COMMON_BOOK, UNCOMMON_BOOK));
        ItemGroupEvents.modifyEntriesEvent(MinecraftItemGroups.TOOLS_ID).register(entries -> entries.addAfter(UNCOMMON_BOOK, RARE_BOOK));
        ItemGroupEvents.modifyEntriesEvent(MinecraftItemGroups.TOOLS_ID).register(entries -> entries.addAfter(RARE_BOOK, EPIC_BOOK));
        ItemGroupEvents.modifyEntriesEvent(MinecraftItemGroups.TOOLS_ID).register(entries -> entries.addAfter(EPIC_BOOK, LEGENDARY_BOOK));
        ItemGroupEvents.modifyEntriesEvent(MinecraftItemGroups.TOOLS_ID).register(entries -> entries.addAfter(LEGENDARY_BOOK, MYTHIC_BOOK));
    }
}
