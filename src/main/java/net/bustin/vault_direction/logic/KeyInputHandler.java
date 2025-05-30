package net.bustin.vault_direction.logic;

import com.mojang.blaze3d.platform.InputConstants;
import net.bustin.vault_direction.VaultDirection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = VaultDirection.MOD_ID, value = Dist.CLIENT)
public class KeyInputHandler {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.screen != null || !mc.isWindowActive()) return;
        if (mc.player == null) return;


        if (Screen.hasControlDown()) {
            if (InputConstants.isKeyDown(mc.getWindow().getWindow(), GLFW.GLFW_KEY_UP)) {
                OverlayConfig.y -= 2;
                OverlayConfig.save();
            } else if (InputConstants.isKeyDown(mc.getWindow().getWindow(), GLFW.GLFW_KEY_DOWN)) {
                OverlayConfig.y += 2;
                OverlayConfig.save();
            } else if (InputConstants.isKeyDown(mc.getWindow().getWindow(), GLFW.GLFW_KEY_LEFT)) {
                OverlayConfig.x -= 2;
                OverlayConfig.save();
            } else if (InputConstants.isKeyDown(mc.getWindow().getWindow(), GLFW.GLFW_KEY_RIGHT)) {
                OverlayConfig.x += 2;
                OverlayConfig.save();
            } else if (InputConstants.isKeyDown(mc.getWindow().getWindow(), InputConstants.KEY_HOME)) {
                OverlayConfig.x = 5;
                OverlayConfig.y = 100;
                OverlayConfig.save();
            }


        }
    }

}
