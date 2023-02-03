package com.blastlong.dungeonhelper.util;

import com.blastlong.dungeonhelper.DungeonHelperClient;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

// CustonEnchantData
public class CEData {
    private static String[] COMMON_ENCHANT_NAMES = {
            "%EA%B8%B0%EC%82%AC",           // 기사
            "%EB%AA%85%EA%B6%81",           // 명궁
            "%EB%B6%88%EA%B5%B4",           // 불굴
            "%EC%9A%A9%EC%82%AC",           // 용사
            "%EC%B0%B8%EA%B2%A9",           // 참격
            "%EC%B9%98%EB%AA%85%ED%83%80",  // 치명타
            "%ED%99%94%EB%A1%9C"            // 화로
    };

    private static String[] UNCOMMON_ENCHANT_NAMES = {
            "%EA%B2%BD%EA%B3%B5",
            "%EB%AA%85%EC%82%AC%EC%88%98",
            "%EB%B2%A0%EB%86%88",
            "%EC%8A%A4%ED%94%84%EB%A7%81",
            "%EC%95%84%EC%9D%B4%EA%B8%B0%EC%8A%A4",
            "%EC%95%BC%EB%A7%8C",
            "%EC%97%B0%EA%B2%A9",
            "%ED%8F%AC%EC%8B%9D"
    };

    private static String[] RARE_ENCHANT_NAMES = {
            "%EA%B0%80%EC%86%8D",
            "%EA%B2%BD%ED%97%98",
            "%EB%B0%A9%EC%96%B4%EB%A7%89",
            "%EC%8C%8D%EB%8F%84",
            "%EC%97%B0%EB%A7%88",
            "%ED%8F%AD%EB%B0%9C%EB%A9%B4%EC%97%AD",
            "%ED%94%8C%EB%9E%9C%ED%84%B0+%5B%EA%B0%90%EC%9E%90%5D",     // 플랜터 [감자]
            "%ED%94%8C%EB%9E%9C%ED%84%B0+%5B%EB%8B%B9%EA%B7%BC%5D",     // 플랜터 [당근]
            "%ED%94%8C%EB%9E%9C%ED%84%B0+%5B%EB%B0%80%5D",              // 플랜터 [밀]
            "%ED%9D%A1%ED%98%88",
            "%ED%97%A4%EB%93%9C%EC%83%B7"
    };

    private static String[] EPIC_ENCHANT_NAMES = {
            "%EA%B2%80%EA%B0%9D",
            "%EA%B3%B5%EB%B3%B5",
            "%EA%B4%91%EB%A7%A5",
            "%EC%88%98%EC%A4%91%ED%98%B8%ED%9D%A1",
            "%EC%88%98%ED%98%B8%EC%9E%90",
            "%EC%95%88%ED%8B%B0+%EA%B7%B8%EB%9E%98%EB%B9%84%ED%8B%B0",
            "%EC%95%BC%EA%B4%91%EA%B2%BD",
            "%EC%9A%A9%EC%83%81%EB%B9%84",
            "%ED%94%8C%EB%9E%9C%ED%84%B0+%5B%EB%84%A4%EB%8D%94+%EC%82%AC%EB%A7%88%EA%B7%80%5D", // 플랜터 [네더 사마귀]
            "%ED%97%A4%EC%9D%B4%EC%8A%A4%ED%8A%B8"
    };

    private static String[] LEGENDARY_ENCHANT_NAMES = {
            "%EA%B2%80%EC%84%B1",
            "%EA%B3%BC%EB%B6%80%ED%95%98",
            "%EC%88%98%ED%99%95",
            "%EC%95%BC%EC%B0%A8",
            "%EC%9A%A9%EC%9E%A5",
            "%EC%9E%AC%EB%A0%A8",
            "%EC%A6%9D%EC%8B%9D",
            "%ED%92%8D%EB%85%84"
    };

    private static String[] MYTHIC_ENCHANT_NAMES = {
            "%EA%B5%AC%EC%84%B8%EC%A3%BC",
            "%EA%B5%B4%EC%82%AD%EA%B8%B0",
            "%EC%A7%80%EB%8F%84%EC%9E%90"
    };

   public static void convert() {
       for(int i = 0; i < COMMON_ENCHANT_NAMES.length; i++) {
           COMMON_ENCHANT_NAMES[i] = URLDecoder.decode(COMMON_ENCHANT_NAMES[i], StandardCharsets.UTF_8);
       }

       for(int i = 0; i < UNCOMMON_ENCHANT_NAMES.length; i++) {
           UNCOMMON_ENCHANT_NAMES[i] = URLDecoder.decode(UNCOMMON_ENCHANT_NAMES[i], StandardCharsets.UTF_8);
       }

       for(int i = 0; i < RARE_ENCHANT_NAMES.length; i++) {
           RARE_ENCHANT_NAMES[i] = URLDecoder.decode(RARE_ENCHANT_NAMES[i], StandardCharsets.UTF_8);
       }

       for(int i = 0; i < EPIC_ENCHANT_NAMES.length; i++) {
           EPIC_ENCHANT_NAMES[i] = URLDecoder.decode(EPIC_ENCHANT_NAMES[i], StandardCharsets.UTF_8);
       }

       for(int i = 0; i < LEGENDARY_ENCHANT_NAMES.length; i++) {
           LEGENDARY_ENCHANT_NAMES[i] = URLDecoder.decode(LEGENDARY_ENCHANT_NAMES[i], StandardCharsets.UTF_8);
       }

       for(int i = 0; i < MYTHIC_ENCHANT_NAMES.length; i++) {
           MYTHIC_ENCHANT_NAMES[i] = URLDecoder.decode(MYTHIC_ENCHANT_NAMES[i], StandardCharsets.UTF_8);
       }
   }

   public static CustomEnchantType getType(ItemStack itemStack) {
       String name = itemStack.getDisplayName().getString();

       if(!DungeonHelperClient.getInstance().data.toggleCustomEnchantRender)
           return null;

       if(Item.getId(itemStack.getItem()) != 829)
           return null;

       for(String s : COMMON_ENCHANT_NAMES) {
           if(name.contains(s))
               return CustomEnchantType.COMMON;
       }

       for(String s : UNCOMMON_ENCHANT_NAMES) {
           if(name.contains(s))
               return CustomEnchantType.UNCOMMON;
       }

       for(String s : RARE_ENCHANT_NAMES) {
           if(name.contains(s))
               return CustomEnchantType.RARE;
       }

       for(String s : EPIC_ENCHANT_NAMES) {
           if(name.contains(s))
               return CustomEnchantType.EPIC;
       }

       for(String s : LEGENDARY_ENCHANT_NAMES) {
           if(name.contains(s))
               return CustomEnchantType.LEGENDARY;
       }

       for(String s : MYTHIC_ENCHANT_NAMES) {
           if(name.contains(s))
               return CustomEnchantType.MYTHIC;
       }

       return null;
   }
}
