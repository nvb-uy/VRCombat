package elocindev.vrcombat.fabric;

import net.fabricmc.api.ClientModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vivecraft.VRState;

import elocindev.vrcombat.fabric.config.ConfigBuilder;
import elocindev.vrcombat.fabric.config.ConfigEntries;

public class VRCombat implements ClientModInitializer {
    public static final String MODID = "vr-combat";
	public static final Logger LOGGER = LoggerFactory.getLogger("vr-combat");
	public static ConfigEntries CONFIG;
	public static boolean isVR = false;

	@Override
	public void onInitializeClient() {
		CONFIG = ConfigBuilder.loadConfig();
		LOGGER.info("VRCombat Config Initialized!");

		isVR = VRState.checkVR();
		if (isVR) {
			LOGGER.info("VRCombat detected Vivecraft VR Mode!");
		} else {
			LOGGER.info("VRCombat detected Vivecraft NONVR Mode!");
		}
	}
}