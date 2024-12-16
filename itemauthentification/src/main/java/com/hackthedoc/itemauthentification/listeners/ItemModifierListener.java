package com.hackthedoc.itemauthentification.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import com.hackthedoc.itemauthentification.utils.ItemUtils;

public class ItemModifierListener implements Listener {
    @EventHandler
    public void onAnvilPrepare(PrepareAnvilEvent event) {
        if (ItemUtils.isItemAuthentified(event.getInventory().getItem(0)))
            event.setResult(null);
    }

    @EventHandler
    public void onGrindstonePrepare(PrepareGrindstoneEvent event) {
        if (ItemUtils.isItemAuthentified(event.getInventory().getItem(0)) || ItemUtils.isItemAuthentified(event.getInventory().getItem(1)))
            event.setResult(null);
    }

    @EventHandler
    public void onItemEnchantPrepare(PrepareItemEnchantEvent event) {
        if (ItemUtils.isItemAuthentified(event.getInventory().getItem(0)))
            event.setCancelled(true);
    }

    @EventHandler
    public void onCraftItem(PrepareItemCraftEvent event) {
        for (ItemStack item : event.getInventory().getMatrix()) {
            if (ItemUtils.isItemAuthentified(item)) {
                event.getInventory().setResult(null);
                return;
            }
        }
    }
}
