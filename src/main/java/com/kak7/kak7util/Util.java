package com.kak7.kak7util;

import com.kak7.kak7util.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class Util implements ModInitializer {
    @Override
    public void onInitialize() {

        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);

        ClientTickEvents.START_CLIENT_TICK.register(event -> {
            if (event.interactionManager == null) {
                return;
            }

        });
    }

    public Screen getModConfigScreenFactory() {
        ConfigBuilder builder = ConfigBuilder.create()
                .setTitle(Text.of("abcdef"));
        Screen screen = builder.build();
        return screen;
    };

    public void createConfig(){
        ConfigBuilder builder = ConfigBuilder.create()
                .setTitle(Text.of("abcdef"));
        Screen screen = builder.build();

    };

}
