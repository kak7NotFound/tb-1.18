package com.kak7.kak7util;

import com.kak7.kak7util.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;

import java.util.stream.Stream;

import static com.kak7.kak7util.Main.keyBindingAutoParkour;
import static com.kak7.kak7util.Main.keyBindingTrigger;

public class AutoParkour {

    ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    public AutoParkour(){
        ClientTickEvents.START_CLIENT_TICK.register(client -> {

            if (mc.world == null) return;

            if (config.isUseHotKeyMode()) {
                if (keyBindingAutoParkour.isPressed()) {
                    onUpdate();
                }
            } else {
                onUpdate();
            }
        });
    }

    MinecraftClient mc = MinecraftClient.getInstance();

    public void onUpdate() {
        var jk = mc.options.jumpKey;
        if (!mc.player.isOnGround() || jk.isPressed())
            return;

        if (mc.player.isSneaking() || jk.isPressed())
            return;

        Box box = mc.player.getBoundingBox();
        Box adjustedBox = box.offset(0, -0.5, 0).expand(-0.001, 0, -0.001);

        Iterable<VoxelShape> blockCollisions =
                mc.world.getBlockCollisions(mc.player, adjustedBox);

        if (blockCollisions.iterator().hasNext())
            return;

        mc.player.jump();
    }

}
