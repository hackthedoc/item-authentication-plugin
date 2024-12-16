package com.hackthedoc.itemauthentification.lands;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.hackthedoc.itemauthentification.ItemAuthentificationPlugin;

import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;

public class LandsManager {
    private final LandsIntegration apiIntegration;

    public LandsManager(ItemAuthentificationPlugin plugin) {
        this.apiIntegration = LandsIntegration.of(plugin);
    }

    public boolean playerOwnsLand(UUID playerUUID) {
        return apiIntegration.getLandPlayer(playerUUID).ownsLand();
    }

    public String getPlayerOwningLandName(UUID playerUUID) {
        return apiIntegration.getLandPlayer(playerUUID).getOwningLand().getName();
    }

    public boolean playerOwnsNation(UUID playerUUID) {
        return playerOwnsLand(playerUUID) && apiIntegration.getLandPlayer(playerUUID).getOwningLand().getNation() != null;
    }

    public String getPlayerOwningNationName(UUID playerUUID) {
        return apiIntegration.getLandPlayer(playerUUID).getOwningLand().getNation().getName();
    }
};
