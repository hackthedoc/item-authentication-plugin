package com.hackthedoc.itemauthentification.economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.hackthedoc.itemauthentification.ItemAuthentificationPlugin;

import net.milkbowl.vault.economy.Economy;

public class EconomyManager {
    private final Economy economy;

    public EconomyManager(ItemAuthentificationPlugin plugin) {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        
        if (rsp == null)
            throw new IllegalStateException("VAULT isn't installed on the server!");
        
        this.economy = rsp.getProvider();
    }

    public Economy getEconomy() {
        return economy;
    }
}
