package com.blastlong.dungeonhelper.sound;

public interface ISoundManager {

    void register();

    void playSound(String soundName, float volume);
}
