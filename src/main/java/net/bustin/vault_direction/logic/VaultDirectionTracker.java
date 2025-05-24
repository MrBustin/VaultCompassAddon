package net.bustin.vault_direction.logic;

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
            vaultDirection = mc.player.getDirection();
            hasCapturedDirection = true;
        }

        if (!inVault && hasCapturedDirection) {
            hasCapturedDirection = false;
            vaultDirection = null;
        }
    }

    public static Direction getVaultDirection() {
        return vaultDirection;
    }
}
