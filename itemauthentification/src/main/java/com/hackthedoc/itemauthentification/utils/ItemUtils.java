package com.hackthedoc.itemauthentification.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
    public static void authentificateItem(ItemStack item, Player player) {
        if (item == null || item.getItemMeta() == null) return;

        ItemMeta meta = item.getItemMeta();

        List<String> lore = meta.getLore();
        if (lore == null) lore = new ArrayList<>();

        lore.add(ChatColor.GOLD+"Authentifié par " + player.getName());
        
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
}
