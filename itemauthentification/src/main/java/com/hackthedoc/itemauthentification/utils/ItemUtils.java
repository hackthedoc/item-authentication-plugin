package com.hackthedoc.itemauthentification.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ItemUtils {
    private static final String AUTH_KEY = "authentified";
    private static final String NAMESPACE = "itemauthentification";
    private static final NamespacedKey NAMESPACED_KEY = new NamespacedKey(NAMESPACE, AUTH_KEY);

    public static void authentificateItem(ItemStack itemStack, Player player, String ownerString) {
        if (isItemAuthentified(itemStack)) return;

        // parse the item to authentificate from the stack
        ItemStack item = itemStack.clone();
        item.setAmount(1);
        itemStack.setAmount(itemStack.getAmount()-1);

        // authentificate it
        ItemMeta meta = item.getItemMeta();

        // tag of authentification
        meta.getPersistentDataContainer().set(NAMESPACED_KEY, PersistentDataType.BOOLEAN, true);

        List<String> lore = meta.getLore();
        if (lore == null) lore = new ArrayList<>();

        lore.add(ChatColor.GOLD+"Authentified by " + ownerString);
        
        meta.setLore(lore);
        item.setItemMeta(meta);

        // place it back in the inventory
        if (!player.getInventory().addItem(item).isEmpty())
            player.getWorld().dropItem(player.getLocation(), item);
    }

    public static boolean isItemAuthentified(ItemStack item) {
        return item != null &&
            item.getItemMeta() != null &&
            item.getItemMeta().getPersistentDataContainer().has(NAMESPACED_KEY, PersistentDataType.BOOLEAN);
    }
}
