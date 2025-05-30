package net.bustin.vault_direction.logic;


import net.bustin.vault_direction.VaultDirection;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VaultDirection.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            VaultDirectionTracker.onClientTick(Minecraft.getInstance());
        }
    }
    @SubscribeEvent
    public static void onClientJoin(ClientPlayerNetworkEvent.LoggedInEvent event) {
        OverlayConfig.load();
    }
}
