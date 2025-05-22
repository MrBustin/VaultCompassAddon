package net.bustin.compass_distance.logic;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class VaultDirectionTracker {

    private static boolean hasCapturedDirection = false;
    private static Direction vaultDirection = null;

    public static void onClientTick(Minecraft mc) {
        if (mc.player == null || mc.level == null) return;

        boolean inVault = mc.level.dimension().location().getPath().contains("vault");

        if (inVault && !hasCapturedDirection) {
            System.out.println("[DEBUG] Player is in a vault.");
            vaultDirection = mc.player.getDirection();
            hasCapturedDirection = true;
            System.out.println("[DEBUG] Captured vault direction: " + vaultDirection);
        }

        if (!inVault && hasCapturedDirection) {
            System.out.println("[DEBUG] Player left the vault. Resetting tracker.");
            hasCapturedDirection = false;
            vaultDirection = null;
        }
    }

    public static Direction getVaultDirection() {
        return vaultDirection;
    }
}
