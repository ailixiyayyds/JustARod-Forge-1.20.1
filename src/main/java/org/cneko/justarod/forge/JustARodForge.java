package org.cneko.justarod.forge;

import net.minecraftforge.fml.common.Mod;

/**
 * Forge entrypoint. Registration is moved here as each Fabric callback is
 * replaced with its Forge event-bus equivalent.
 */
@Mod(JustARodForge.MOD_ID)
public final class JustARodForge {
    public static final String MOD_ID = "justarod";

    public JustARodForge() {
    }
}
