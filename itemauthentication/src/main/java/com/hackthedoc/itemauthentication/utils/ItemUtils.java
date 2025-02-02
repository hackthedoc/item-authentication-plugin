package com.hackthedoc.itemauthentication.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.hackthedoc.itemauthentication.ItemAuthenticationPlugin;

public class ItemUtils {
    private static final String AUTH_KEY = "authenticated";
    private static final String NAMESPACE = "itemauthentication";
    private static final NamespacedKey NAMESPACED_KEY = new NamespacedKey(NAMESPACE, AUTH_KEY);

    public static void authenticateItem(ItemStack itemStack, Player player) {
        if (isItemAuthenticated(itemStack)) return;

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

        lore.add(ChatColor.GOLD+ItemAuthenticationPlugin.getInstance().getConfig().getString("authenticated-by") + player.getName());
        
        meta.setLore(lore);
        item.setItemMeta(meta);

        // place it back in the inventory
        if (!player.getInventory().addItem(item).isEmpty())
            player.getWorld().dropItem(player.getLocation(), item);
    }

    public static boolean isItemAuthenticated(ItemStack item) {
        return item != null &&
            item.getItemMeta() != null &&
            item.getItemMeta().getPersistentDataContainer().has(NAMESPACED_KEY, PersistentDataType.BOOLEAN);
    }
}
