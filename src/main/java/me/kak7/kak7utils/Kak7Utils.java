package me.kak7.kak7utils;


import me.kak7.kak7utils.autoparkour.AutoParkour;
import me.kak7.kak7utils.config.ModConfig;
import me.kak7.kak7utils.trigger.TriggerBot;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class Kak7Utils implements ModInitializer {

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        Hotkeys.initialize();
        AutoParkour.initialize();
        TriggerBot.initialize();
    }
}
