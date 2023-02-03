package com.blastlong.dungeonhelper.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;

public class TextUtil {
    public static Component TextComponent(String string) {
        return MutableComponent.create(new LiteralContents(string));
    }
}
