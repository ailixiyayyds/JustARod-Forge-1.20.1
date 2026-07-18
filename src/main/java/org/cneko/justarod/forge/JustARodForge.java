package org.cneko.justarod.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.cneko.justarod.Justarod;
import org.cneko.justarod.client.JustarodClient;

/**
 * Forge entrypoint. Registration is moved here as each Fabric callback is
 * replaced with its Forge event-bus equivalent.
 */
@Mod(JustARodForge.MOD_ID)
public final class JustARodForge {
    public static final String MOD_ID = "justarod";

    public JustARodForge() {
        new Justarod().onInitialize();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> new JustarodClient().onInitializeClient());
    }
}
