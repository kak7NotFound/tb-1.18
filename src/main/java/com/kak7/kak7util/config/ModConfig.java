package com.kak7.kak7util.config;

import lombok.Getter;
import lombok.Setter;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "kak7Util")
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.PrefixText
    @Getter
    @Setter
    boolean triggerbotEnabled = false;
    @Getter
    boolean criticalAttacksOnly = false;
    @Getter
    boolean doSound = true;
    @Getter
    boolean doConsoleLog = false;
    @Getter
    boolean avoidAntiCheat = true;

    @ConfigEntry.Gui.PrefixText
            @Getter
            @Setter
    boolean autoParkourEnabled = false;
    @Getter
    boolean useHotKeyMode = true;


}