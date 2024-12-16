package com.hackthedoc.itemauthentication;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import com.hackthedoc.itemauthentication.commands.AuthenticateCommand;
import com.hackthedoc.itemauthentication.economy.EconomyManager;
import com.hackthedoc.itemauthentication.listeners.ItemModifierListener;

public class ItemAuthenticationPlugin extends JavaPlugin {
  private static final Logger LOGGER=Logger.getLogger("itemauthentification");
  private static ItemAuthenticationPlugin instance;
  private EconomyManager economyManager;

  @Override
  public void onEnable() {
    instance = this;
    saveDefaultConfig();
    economyManager = new EconomyManager(instance);

    // register commands
    getCommand("authenticate").setExecutor(new AuthenticateCommand(instance));

    // register listeners
    getServer().getPluginManager().registerEvents(new ItemModifierListener(), instance);

    LOGGER.info("itemauthentication enabled");
  }

  @Override
  public void onDisable() {
    LOGGER.info("itemauthentication disabled");
  }

  public static ItemAuthenticationPlugin getInstance() {
    return instance;
  }

  public EconomyManager getEconomyManager() {
    return economyManager;
  }
}
