package com.kak7.kak7util.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

        // todo return a screen
        return parent -> AutoConfig.getConfigScreen(ModConfig.class, parent).get();

    }
}
