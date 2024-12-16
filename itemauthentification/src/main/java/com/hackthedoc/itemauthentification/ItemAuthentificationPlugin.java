package com.hackthedoc.itemauthentification;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import com.hackthedoc.itemauthentification.commands.AuthentificateCommand;
import com.hackthedoc.itemauthentification.economy.EconomyManager;

public class ItemAuthentificationPlugin extends JavaPlugin {
  private static final Logger LOGGER=Logger.getLogger("itemauthentification");
  private static ItemAuthentificationPlugin instance;
  private EconomyManager economyManager;

  @Override
  public void onEnable() {
    instance = this;
    saveDefaultConfig();
    economyManager = new EconomyManager(instance);

    getCommand("authentificate").setExecutor(new AuthentificateCommand(instance));

    LOGGER.info("itemauthentification enabled");
  }

  @Override
  public void onDisable() {
    LOGGER.info("itemauthentification disabled");
  }

  public static ItemAuthentificationPlugin getInstance() {
    return instance;
  }

  public EconomyManager getEconomyManager() {
    return economyManager;
  }
}
