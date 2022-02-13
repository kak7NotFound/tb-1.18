package com.kak7.kak7util;

import com.kak7.kak7util.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class Main implements ModInitializer {

    public static KeyBinding keyBindingTrigger;
    public static KeyBinding keyBindingAutoParkour;

    @Override
    public void onInitialize() {

        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        new TriggerInteraction();
        new AutoParkour();
        initializeKeybinds();

    }

    public void initializeKeybinds(){

        keyBindingTrigger = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.kak7Util.triggerbotkey", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_R, // The keycode of the key
                "category.kak7util.kak7util" // The translation key of the keybinding's category.
        ));

        keyBindingAutoParkour = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.kak7Util.autoparkourkey", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_LEFT_ALT, // The keycode of the key
                "category.kak7util.kak7util" // The translation key of the keybinding's category.
        ));
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
