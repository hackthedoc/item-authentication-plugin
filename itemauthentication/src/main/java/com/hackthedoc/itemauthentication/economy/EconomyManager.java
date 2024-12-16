package com.hackthedoc.itemauthentication.economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.hackthedoc.itemauthentication.ItemAuthenticationPlugin;

import net.milkbowl.vault.economy.Economy;

public class EconomyManager {
    private final Economy economy;

    public EconomyManager(ItemAuthenticationPlugin plugin) {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        
        if (rsp == null)
            throw new IllegalStateException("VAULT isn't installed on the server!");
        
        this.economy = rsp.getProvider();
    }

    public Economy getEconomy() {
        return economy;
    }
}
