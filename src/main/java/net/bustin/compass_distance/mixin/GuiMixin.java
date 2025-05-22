package net.bustin.compass_distance.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.bustin.compass_distance.logic.VaultDirectionTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Gui.class)
public class GuiMixin {

    @Inject(method = "renderHotbar", at = @At("TAIL"))
    private void compassDistanceOverlay(float partialTicks, PoseStack poseStack, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null || mc.level == null) return;

        Direction direction = VaultDirectionTracker.getVaultDirection();
        if (direction == null) return;

        String text = "Vault Direction: " + direction.getName().toUpperCase();

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        Font font = mc.font;
        int x = 10;
        int y = screenHeight - 70;

        font.draw(poseStack, text, x, y, 0xFFFFFF); // white text
    }
}