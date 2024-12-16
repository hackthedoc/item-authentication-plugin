package com.hackthedoc.itemauthentification.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.PrepareAnvilEvent;

import com.hackthedoc.itemauthentification.utils.ItemUtils;

public class AnvilListener implements Listener {
    @EventHandler
    public void onAnvilPrepare(PrepareAnvilEvent event) {
        if (ItemUtils.isItemAuthentified(event.getInventory().getItem(0)))
            event.setResult(null);
    }
}
