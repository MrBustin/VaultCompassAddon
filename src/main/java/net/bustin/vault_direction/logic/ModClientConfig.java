package net.bustin.vault_direction.logic;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModClientConfig {
    public static ForgeConfigSpec CLIENT;
    public static ForgeConfigSpec.IntValue OVERLAY_X;
    public static ForgeConfigSpec.IntValue OVERLAY_Y;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("Vault HUD");
        OVERLAY_X = builder.defineInRange("overlayX", 5, 0, 10000);
        OVERLAY_Y = builder.defineInRange("overlayY", 100, 0, 10000);
        builder.pop();

        CLIENT = builder.build();
    }
}
