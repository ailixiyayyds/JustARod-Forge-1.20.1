package org.cneko.justarod.forge;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.cneko.justarod.Justarod;
import org.cneko.justarod.client.JustarodClient;
import org.cneko.justarod.entity.JREntityAttributes;

/**
 * Forge entrypoint. Registration is moved here as each Fabric callback is
 * replaced with its Forge event-bus equivalent.
 */
@Mod(JustARodForge.MOD_ID)
public final class JustARodForge {
    public static final String MOD_ID = "justarod";

    public JustARodForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(JustARodForge::registerEntityAttributes);

        new Justarod().onInitialize();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> new JustarodClient().onInitializeClient());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put((EntityType) JREntityAttributes.seeeeexNekoType(), JREntityAttributes.createSeeeeexNeko());
        event.put((EntityType) JREntityAttributes.loliNekoType(), JREntityAttributes.createLoliNeko());
        event.put((EntityType) JREntityAttributes.rodType(), JREntityAttributes.createRod());
    }
}
