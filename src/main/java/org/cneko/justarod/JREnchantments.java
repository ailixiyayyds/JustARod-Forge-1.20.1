package org.cneko.justarod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static org.cneko.justarod.Justarod.MODID;

public class JREnchantments {
    public static final Identifier HYSTERECTOMY_ID = Identifier.of(MODID,"hysterectomy");
    public static final RegistryKey<Enchantment> HYSTERECTOMY = of(HYSTERECTOMY_ID);
    public static final Identifier UTERUS_INSTALLATION_ID = Identifier.of(MODID,"uterus_installation");
    public static final RegistryKey<Enchantment> UTERUS_INSTALLATION = of(UTERUS_INSTALLATION_ID);
    public static final Identifier ARTIFICIAL_ABORTION_ID = Identifier.of(MODID,"artificial_abortion");
    public static final RegistryKey<Enchantment> ARTIFICIAL_ABORTION = of(ARTIFICIAL_ABORTION_ID);
    public static final Identifier MASTECTOMY_ID = Identifier.of(MODID,"mastectomy");
    public static final RegistryKey<Enchantment> MASTECTOMY = of(MASTECTOMY_ID);
    public static final Identifier ORCHIECTOMY_ID = Identifier.of(MODID,"orchiectomy");
    public static final RegistryKey<Enchantment> ORCHIECTOMY = of(ORCHIECTOMY_ID);
    public static final Identifier AMPUTATING_ID = Identifier.of(MODID,"amputating");
    public static final RegistryKey<Enchantment> AMPUTATING = of(AMPUTATING_ID);
    public static final Identifier PRECISION_ID = Identifier.of(MODID,"precision");
    public static final RegistryKey<Enchantment> PRECISION = of(PRECISION_ID);
    public static final Identifier BEHEADING_ID = Identifier.of(MODID,"beheading");
    public static final RegistryKey<Enchantment> BEHEADING = of(BEHEADING_ID);
    public static final Identifier HEMORRHOIDECTOMY_ID = Identifier.of(MODID,"hemorrhoidectomy");
    public static final RegistryKey<Enchantment> HEMORRHOIDECTOMY = of(HEMORRHOIDECTOMY_ID);
    public static final Identifier MEIOSIS_ID = Identifier.of(MODID,"meiosis");
    public static final RegistryKey<Enchantment> MEIOSIS = of(MEIOSIS_ID);
    public static final Identifier HYMENOTOMY_ID = Identifier.of(MODID,"hymenotomy");
    public static final RegistryKey<Enchantment> HYMENOTOMY = of(HYMENOTOMY_ID);
    public static final Identifier LAPAROSCOPY_ID = Identifier.of(MODID,"laparoscopy");
    public static final RegistryKey<Enchantment> LAPAROSCOPY = of(LAPAROSCOPY_ID);

    private static final Enchantment HYSTERECTOMY_VALUE = scalpelEnchantment();
    private static final Enchantment UTERUS_INSTALLATION_VALUE = scalpelEnchantment();
    private static final Enchantment ARTIFICIAL_ABORTION_VALUE = scalpelEnchantment();
    private static final Enchantment MASTECTOMY_VALUE = scalpelEnchantment();
    private static final Enchantment ORCHIECTOMY_VALUE = scalpelEnchantment();
    private static final Enchantment AMPUTATING_VALUE = scalpelEnchantment();
    private static final Enchantment BEHEADING_VALUE = scalpelEnchantment();
    private static final Enchantment HEMORRHOIDECTOMY_VALUE = scalpelEnchantment();
    private static final Enchantment HYMENOTOMY_VALUE = scalpelEnchantment();
    private static final Enchantment LAPAROSCOPY_VALUE = scalpelEnchantment();
    private static final Enchantment PRECISION_VALUE = new ExactItemEnchantment("cloner_device", 5, null);
    private static final Enchantment MEIOSIS_VALUE = new ExactItemEnchantment("parthenogenesis_catalyst", 3, null);

    private static Enchantment scalpelEnchantment() {
        return new ExactItemEnchantment("scalpel", 1, "scalpel_surgery");
    }

    /** Registers the data-driven 1.21 enchantments as normal 1.20.1 enchantments. */
    public static void init() {
        register(HYSTERECTOMY_ID, HYSTERECTOMY_VALUE);
        register(UTERUS_INSTALLATION_ID, UTERUS_INSTALLATION_VALUE);
        register(ARTIFICIAL_ABORTION_ID, ARTIFICIAL_ABORTION_VALUE);
        register(MASTECTOMY_ID, MASTECTOMY_VALUE);
        register(ORCHIECTOMY_ID, ORCHIECTOMY_VALUE);
        register(AMPUTATING_ID, AMPUTATING_VALUE);
        register(PRECISION_ID, PRECISION_VALUE);
        register(BEHEADING_ID, BEHEADING_VALUE);
        register(HEMORRHOIDECTOMY_ID, HEMORRHOIDECTOMY_VALUE);
        register(MEIOSIS_ID, MEIOSIS_VALUE);
        register(HYMENOTOMY_ID, HYMENOTOMY_VALUE);
        register(LAPAROSCOPY_ID, LAPAROSCOPY_VALUE);
    }

    private static void register(Identifier id, Enchantment enchantment) {
        Registry.register(Registries.ENCHANTMENT, id, enchantment);
    }

    private static final class ExactItemEnchantment extends Enchantment {
        private final Identifier itemId;
        private final int maxLevel;
        private final String exclusiveGroup;

        private ExactItemEnchantment(String itemPath, int maxLevel, String exclusiveGroup) {
            super(Rarity.COMMON, EnchantmentTarget.BREAKABLE,
                    new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
            this.itemId = Identifier.of(MODID, itemPath);
            this.maxLevel = maxLevel;
            this.exclusiveGroup = exclusiveGroup;
        }

        @Override
        public int getMaxLevel() {
            return maxLevel;
        }

        @Override
        public int getMinPower(int level) {
            return 1 + Math.max(0, level - 1);
        }

        @Override
        public int getMaxPower(int level) {
            return getMinPower(level) + 10;
        }

        @Override
        public boolean isAcceptableItem(ItemStack stack) {
            return itemId.equals(Registries.ITEM.getId(stack.getItem()));
        }

        @Override
        protected boolean canAccept(Enchantment other) {
            if (exclusiveGroup != null && other instanceof ExactItemEnchantment exact
                    && exclusiveGroup.equals(exact.exclusiveGroup)) {
                return false;
            }
            return super.canAccept(other);
        }
    }

    public static RegistryKey<Enchantment> of(Identifier id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
    }
}
