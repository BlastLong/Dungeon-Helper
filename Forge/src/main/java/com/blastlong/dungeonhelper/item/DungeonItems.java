package com.blastlong.dungeonhelper.item;

import com.blastlong.dungeonhelper.DungeonHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DungeonItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DungeonHelper.MODID);

    public static final RegistryObject<Item> COMMON_BOOK = ITEMS.register("common_book", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> UNCOMMON_BOOK = ITEMS.register("uncommon_book", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> RARE_BOOK = ITEMS.register("rare_book", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> EPIC_BOOK = ITEMS.register("epic_book", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> LEGENDARY_BOOK = ITEMS.register("legendary_book", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> MYTHIC_BOOK = ITEMS.register("mythic_book", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
