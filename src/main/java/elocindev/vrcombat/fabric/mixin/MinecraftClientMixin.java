package elocindev.vrcombat.fabric.mixin;

import net.bettercombat.client.BetterCombatClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import org.vivecraft.api_beta.client.VivecraftClientAPI;
import org.vivecraft.client_vr.ClientDataHolderVR;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
	@Shadow @Nullable public ClientWorld world;

	@Shadow @Nullable public Entity cameraEntity;


	@Inject(at = @At("HEAD"), method = "tick")
	// This is a hacky solution, but according to Daedelus, the only way to do it is by overriding the boolean every tick
	private void state(CallbackInfo info) {
		//A check if the VR is running could make a world of a difference.
		//VR hot-swapping is really tedious to work with, this solution checks if VR is enabled,
		//And if the player is also currently IN VR, so that the mod comes back when a hotswap to desktop occurs.
		if (ClientDataHolderVR.getInstance().vrSettings.vrEnabled &&
				VivecraftClientAPI.getInstance() != null &&
				VivecraftClientAPI.getInstance().isVrActive()) {
			BetterCombatClient.ENABLED = false;
		}
		else {
			// RE ENABLE WHEN VR IS OFF. SUPER IMPORTANT!!
			BetterCombatClient.ENABLED = true;
		}
	}
}