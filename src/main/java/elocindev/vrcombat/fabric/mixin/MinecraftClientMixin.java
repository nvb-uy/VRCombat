package elocindev.vrcombat.fabric.mixin;

import net.bettercombat.client.BetterCombatClient;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import elocindev.vrcombat.fabric.VRCombat;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	@Inject(at = @At("HEAD"), method = "tick")
	// This is a hacky solution, but according to Daedelus, the only way to do it is by overriding the boolean every tick
	private void state(CallbackInfo info) {
		if (VRCombat.isVR) {
			BetterCombatClient.ENABLED = false;
		}
	}
}