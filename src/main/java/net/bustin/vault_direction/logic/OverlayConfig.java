package net.bustin.vault_direction.logic;

import net.minecraft.world.entity.player.Player;

public class OverlayConfig {
    public static int x = 5;
    public static int y = 100;


    public static void load() {
        x = ModClientConfig.OVERLAY_X.get();
        y = ModClientConfig.OVERLAY_Y.get();
    }

    public static void save() {
        ModClientConfig.OVERLAY_X.set(x);
        ModClientConfig.OVERLAY_Y.set(y);
        ModClientConfig.CLIENT.save();
    }
}
