package com.blastlong.dungeonhelper.file;

import com.blastlong.dungeonhelper.gui.DungeonCooltimeGui;
import com.blastlong.dungeonhelper.util.ClassCategory;

import java.io.Serializable;

public class Data implements Serializable {

    public boolean toggleDungeonCooltime = true;
    public boolean toggleDungeonCooltimeText = true;
    public boolean toggleDungeonCooltimeFade = true;

    public boolean toggleCustomEnchantRender = true;

    public boolean toggleSkillCooltime = true;
    public ClassCategory classType = ClassCategory.ASSASSIN;

    public long[] lastDungeonTime = new long[DungeonCooltimeGui.DUNGEON_COUNT];
}

