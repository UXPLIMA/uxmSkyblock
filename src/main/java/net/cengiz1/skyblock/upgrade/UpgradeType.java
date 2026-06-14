package net.cengiz1.skyblock.upgrade;

/**
 * Yükseltme davranış tipi.
 * VALUE     : tek sayısal değer (limit, çarpan, boyut...).
 * GENERATOR : seviyeye göre ağırlıklı blok şansları (cobble jeneratörü).
 */
public enum UpgradeType {
    VALUE,
    GENERATOR;

    public static UpgradeType fromString(String name) {
        if (name == null)
            return VALUE;
        try {
            return valueOf(name.trim().toUpperCase());
        } catch (IllegalArgumentException error) {
            return VALUE;
        }
    }
}
