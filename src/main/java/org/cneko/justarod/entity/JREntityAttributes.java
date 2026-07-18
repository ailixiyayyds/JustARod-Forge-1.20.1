package org.cneko.justarod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;

/**
 * Platform-neutral attribute suppliers used by loader-specific lifecycle
 * hooks. Keeping the entity subclasses behind this boundary prevents the
 * Forge source set from needing a second, remapped toNeko compile artifact.
 */
public final class JREntityAttributes {
    private JREntityAttributes() {
    }

    public static EntityType<?> seeeeexNekoType() {
        return JREntities.SEEEEEX_NEKO;
    }

    public static EntityType<?> loliNekoType() {
        return JREntities.LOLI_NEKO;
    }

    public static EntityType<?> rodType() {
        return JREntities.ROD;
    }

    public static DefaultAttributeContainer createSeeeeexNeko() {
        return SeeeeexNekoEntity.createNekoAttributes().build();
    }

    public static DefaultAttributeContainer createLoliNeko() {
        return LoliNekoEntity.createNekoAttributes().build();
    }

    public static DefaultAttributeContainer createRod() {
        return RodEntity.Companion.createRodAttribute().build();
    }
}
