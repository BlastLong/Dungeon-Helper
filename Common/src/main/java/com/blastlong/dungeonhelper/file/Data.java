package com.blastlong.dungeonhelper.file;

import com.blastlong.dungeonhelper.gui.DungeonCooltimeGui;

import java.io.Serializable;

public class Data implements Serializable {

    public boolean toggleDungeonCooltime = true;
    public boolean toggleDungeonCooltimeText = true;
    public boolean toggleDungeonCooltimeFade = true;


    public boolean toggleCustomEnchantRender = true;

    public long[] lastDungeonTime = new long[DungeonCooltimeGui.DUNGEON_COUNT];
}
