package net.bustin.vault_direction.logic;

import com.mojang.blaze3d.platform.InputConstants;
import iskallia.vault.gear.attribute.type.VaultGearAttributeTypeMerger;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.init.ModGearAttributes;
import iskallia.vault.init.ModItems;
import iskallia.vault.item.tool.HammerManager;
import iskallia.vault.item.tool.IHammer;
import net.bustin.vault_direction.VaultDirection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
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
        LocalPlayer player = mc.player;
        if (player == null) return;

        ItemStack stack = mc.player.getMainHandItem();

        if (stack.getItem() == ModItems.TOOL) {
            VaultGearData data = VaultGearData.read(stack);
            boolean hasHammer = data.get(ModGearAttributes.HAMMERING, VaultGearAttributeTypeMerger.anyTrue());

            if (hasHammer) {
                // Player is holding a hammer-enabled Vault tool don't do keybind actions
                return;
            }
        }

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
