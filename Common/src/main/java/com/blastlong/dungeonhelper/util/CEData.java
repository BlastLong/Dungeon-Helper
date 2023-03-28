package com.blastlong.dungeonhelper.util;

import com.blastlong.dungeonhelper.DungeonHelperClient;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

// CustonEnchantData
public class CEData {
    private static String[] COMMON_ENCHANT_NAMES = {
            "치명타",
            "헤드샷",
            "불굴",
            "재련"
    };

    private static String[] UNCOMMON_ENCHANT_NAMES = {
            "포식",
            "베놈",
            "스프링",
            "수중호흡",
            "아이기스"
    };

    private static String[] RARE_ENCHANT_NAMES = {
            "연격",
            "방어막",
            "용상비",
            "가속",
            "야광경",
            "과식",
            "인듀어",
            "화로",
            "경험",
            "글래스해머"
    };

    private static String[] EPIC_ENCHANT_NAMES = {
            "공복",
            "수호자",
            "경공",
            "명궁",
            "명사수",
            "검객",
            "용장",
            "흡혈",
            "광맥",
            "바다의 가호",
            "방화"
    };

    private static String[] LEGENDARY_ENCHANT_NAMES = {
            "수확",
            "풍년",
            "과부하",
            "검성",
            "야차",
            "헤이스트",
            "증식",
            "소닉붐",
            "서리갑주"
    };

    private static String[] MYTHIC_ENCHANT_NAMES = {
            "굴삭기",
            "구세주",
            "괴력"
    };

   public static CustomEnchantType getType(ItemStack itemStack) {
       String name = itemStack.getDisplayName().getString();

       if(!DungeonHelperClient.getInstance().data.toggleCustomEnchantRender)
           return null;

       if(!itemStack.getItem().toString().equals("paper"))
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
