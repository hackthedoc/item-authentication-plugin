package com.hackthedoc.itemauthentification.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
    public static void authentificateItem(ItemStack itemStack, Player player) {
        if (itemStack == null || itemStack.getItemMeta() == null) return;

        // parse the item to authentificate from the stack
        ItemStack item = itemStack.clone();
        item.setAmount(1);
        itemStack.setAmount(itemStack.getAmount()-1);

        // authentificate it
        ItemMeta meta = item.getItemMeta();

        List<String> lore = meta.getLore();
        if (lore == null) lore = new ArrayList<>();

        lore.add(ChatColor.GOLD+"Authentified by " + player.getName());
        
        meta.setLore(lore);
        item.setItemMeta(meta);

        // place it back in the inventory
        if (!player.getInventory().addItem(item).isEmpty())
            player.getWorld().dropItem(player.getLocation(), item);
    }
}
