package elocindev.vrcombat.fabric;

import net.fabricmc.api.ClientModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.vrcombat.fabric.config.ConfigBuilder;
import elocindev.vrcombat.fabric.config.ConfigEntries;
import org.vivecraft.client.VivecraftVRMod;
import org.vivecraft.client_vr.VRState;
import org.vivecraft.fabric.VivecraftMod;

public class VRCombat implements ClientModInitializer {
    public static final String MODID = "vr-combat";
	public static final Logger LOGGER = LoggerFactory.getLogger("vr-combat");
	public static ConfigEntries CONFIG;

	//Removed the boolean, since this version has hotswapping.

	@Override
	public void onInitializeClient() {
		CONFIG = ConfigBuilder.loadConfig();
		LOGGER.info("VRCombat Config Initialized!");

		if (VRState.vrInitialized) {
			LOGGER.info("VRCombat detected Vivecraft VR Mode, May change later!");
		} else {
			LOGGER.info("VRCombat detected Vivecraft NONVR Mode, May change later!");
		}
	}
}