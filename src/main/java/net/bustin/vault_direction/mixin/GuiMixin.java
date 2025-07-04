package net.bustin.vault_direction.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.bustin.vault_direction.logic.OverlayConfig;
import net.bustin.vault_direction.logic.VaultDirectionTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Gui.class)
public class GuiMixin {


    @Inject(method = "renderHotbar", at = @At("TAIL"))
    private void vaultDistanceOverlay(float partialTicks, PoseStack poseStack, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null || mc.level == null) return;

        Direction direction = VaultDirectionTracker.getVaultDirection();
        if (direction == null) return;

        String text = "Vault Direction: " + direction.getName().toUpperCase();
        Font font = mc.font;

        font.draw(poseStack, text, OverlayConfig.x, OverlayConfig.y, 0xFFFFFF);


    }
}