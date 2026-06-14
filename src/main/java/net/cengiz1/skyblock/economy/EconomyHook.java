package net.cengiz1.skyblock.economy;

import org.bukkit.entity.Player;

/**
 * Ekonomi soyutlaması. Vault yoksa {@link NoEconomyHook} kullanılır; böylece
 * Vault sınıfları hiç yüklenmez ve eklenti çökmeden çalışır.
 */
public interface EconomyHook {

    boolean isEnabled();

    boolean has(Player player, double amount);

    boolean withdraw(Player player, double amount);

    double balance(Player player);
}
