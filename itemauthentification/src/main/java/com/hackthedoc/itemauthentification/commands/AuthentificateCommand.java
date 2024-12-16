package com.hackthedoc.itemauthentification.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.hackthedoc.itemauthentification.ItemAuthentificationPlugin;
import com.hackthedoc.itemauthentification.utils.ItemUtils;

import net.md_5.bungee.api.ChatColor;

public class AuthentificateCommand implements CommandExecutor {
    private  final ItemAuthentificationPlugin plugin;

    public AuthentificateCommand(ItemAuthentificationPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+plugin.getConfig().getString("player-only-cmd"));
            return true;
        }

        Player player = (Player)sender;

        // parse item
        
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            player.sendMessage(ChatColor.RED+plugin.getConfig().getString("player-must-hold-an-item"));
            return true;
        }
        if (ItemUtils.isItemAuthentified(item)) {
            player.sendMessage(ChatColor.RED+plugin.getConfig().getString("item-already-authentified"));
            return true;
        }

        // parse cost

        double cost = plugin.getConfig().getDouble("identification-cost");
        if (!plugin.getEconomyManager().getEconomy().has(player, cost)) {
            player.sendMessage(ChatColor.RED+plugin.getConfig().getString("not-enough-money")+" ("+cost+")");
            return true;
        }

        // apply authentification

        plugin.getEconomyManager().getEconomy().withdrawPlayer(player, cost);
        ItemUtils.authentificateItem(item, player);
        player.sendMessage(ChatColor.GREEN+plugin.getConfig().getString("authentification-success"));

        return true;
    }
}
