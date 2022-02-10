package com.kak7.kak7util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;

public class TriggerInteraction {


    boolean Enabled = false;
    MinecraftClient mc = MinecraftClient.getInstance();
    
    private boolean forceCrits = false;
    public double beforeY;

    public void onEnable() {
        Enabled = true;
        mc.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_FLUTE, 1.0F, 1.0F);
        beforeY = mc.player.getY();
    }

    public void onDisable() {
        mc.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_GUITAR, 1.0F, 1.0F);
        Enabled = false;
    }

    public boolean mainChecks() {
        if (mc.targetedEntity != null) {

            if (mc.player.getAttackCooldownProgress(0.0F) < 1.0F) {
                return false;
            }

        }
        return false;
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