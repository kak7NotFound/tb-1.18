package com.kak7.kak7util;

import com.kak7.kak7util.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.kak7.kak7util.Main.keyBindingTrigger;

public class TriggerInteraction {

    public TriggerInteraction() {
        registerHotkey();
        registerChecks();
    }

    Random r = new Random();

    int low = 1;
    int high = 50;

    ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    double beforeY = 0;

    MinecraftClient mc = MinecraftClient.getInstance();

    public void registerHotkey() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {


            if (mc.interactionManager != null) {
                beforeY = mc.player.getY();
            }

            while (keyBindingTrigger.wasPressed()) {

                if (!config.isTriggerbotEnabled()) {
                    config.setTriggerbotEnabled(true);
                    if (config.isDoSound()) mc.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_FLUTE, 1.0F, 1.0F);
                    if (config.isDoConsoleLog()) System.out.println("\n   ____   _   _   _____    ____   _  __     ___    _   _ \n" +
                            "  / ___| | | | | | ____|  / ___| | |/ /    / _ \\  | \\ | |\n" +
                            " | |     | |_| | |  _|   | |     | ' /    | | | | |  \\| |\n" +
                            " | |___  |  _  | | |___  | |___  | . \\    | |_| | | |\\  |\n" +
                            "  \\____| |_| |_| |_____|  \\____| |_|\\_\\    \\___/  |_| \\_|\n" +
                            "                                                         \n");
                } else {
                    config.setTriggerbotEnabled(false);
                    if (config.isDoSound()) mc.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_GUITAR, 1.0F, 1.0F);
                    if (config.isDoConsoleLog()) System.out.println("\n   ____   _   _   _____    ____   _  __     ___    _____   _____ \n" +
                            "  / ___| | | | | | ____|  / ___| | |/ /    / _ \\  |  ___| |  ___|\n" +
                            " | |     | |_| | |  _|   | |     | ' /    | | | | | |_    | |_   \n" +
                            " | |___  |  _  | | |___  | |___  | . \\    | |_| | |  _|   |  _|  \n" +
                            "  \\____| |_| |_| |_____|  \\____| |_|\\_\\    \\___/  |_|     |_|    \n" +
                            "                                                                 ");
                }
            }
        });
    }

    public void registerChecks() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {

            if (config.isTriggerbotEnabled() & client.interactionManager != null) {
                mainChecks();
            }
        });
    }


    public void mainChecks() {

        if (mc.player.getAttackCooldownProgress(0.0F) == 1.0F) {

            new Thread(() -> {

                if (config.isAvoidAntiCheat()) {
                    try {
                        int result = this.r.nextInt(this.high - this.low) + this.low;
                        TimeUnit.MILLISECONDS.sleep(result);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (config.isCriticalAttacksOnly()) {
                    if (!canDoCrit()) return;
                }


                if (mc.targetedEntity != null) {
                    attack();
                }

            }).start();

        }
    }


    public boolean canDoCrit() {

        if (mc.player.isTouchingWater() || mc.player.isInLava()) {
            return false;
        }
        if (!mc.player.isOnGround()) {
            return !(mc.player.getY() > beforeY);
        }
        return false;
    }


    public void attack() {
        try {
            mc.interactionManager.attackEntity(mc.player, mc.targetedEntity);
            mc.player.swingHand(Hand.MAIN_HAND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}