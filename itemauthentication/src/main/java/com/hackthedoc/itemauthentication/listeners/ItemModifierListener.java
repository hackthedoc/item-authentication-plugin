package com.hackthedoc.itemauthentication.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;

import com.hackthedoc.itemauthentication.utils.ItemUtils;

public class ItemModifierListener implements Listener {
    // prevent use of authenticated items in anvils
    
    @EventHandler
    public void onAnvilPrepare(PrepareAnvilEvent event) {
        if (ItemUtils.isItemAuthenticated(event.getInventory().getItem(0)))
            event.setResult(null);
    }

    // prevent use of grindstone on authenticated items

    @EventHandler
    public void onGrindstonePrepare(PrepareGrindstoneEvent event) {
        if (ItemUtils.isItemAuthenticated(event.getInventory().getItem(0)) || ItemUtils.isItemAuthenticated(event.getInventory().getItem(1)))
            event.setResult(null);
    }

    // prevent echantement of authenticated items

    @EventHandler
    public void onItemEnchantPrepare(PrepareItemEnchantEvent event) {
        if (ItemUtils.isItemAuthenticated(event.getInventory().getItem(0)))
            event.setCancelled(true);
    }

    // prevent use of authenticated items in craft

    @EventHandler
    public void onCraftItem(PrepareItemCraftEvent event) {
        for (ItemStack item : event.getInventory().getMatrix()) {
            if (ItemUtils.isItemAuthenticated(item)) {
                event.getInventory().setResult(null);
                return;
            }
        }
    }

    // prevent use of athenticated items in smithing

    @EventHandler
    public void onSmithingPrepare(PrepareSmithingEvent event) {
        if (ItemUtils.isItemAuthenticated(event.getInventory().getItem(0)) ||
            ItemUtils.isItemAuthenticated(event.getInventory().getItem(1)) ||
            ItemUtils.isItemAuthenticated(event.getInventory().getItem(2))
            )
            event.setResult(null);
    }

    // prevent placement of authenticated blocks

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (ItemUtils.isItemAuthenticated(event.getItemInHand()))
            event.setCancelled(true);
    }
}
