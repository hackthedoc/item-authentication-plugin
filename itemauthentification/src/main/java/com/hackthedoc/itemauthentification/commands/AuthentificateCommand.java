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
            sender.sendMessage(ChatColor.RED+"This is a player only command!");
            return true;
        }

        Player player = (Player)sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            player.sendMessage(ChatColor.RED+"You must hold an item to authentificate it!");
            return true;
        }

        double cost = plugin.getConfig().getDouble("identification-cost");
        if (!plugin.getEconomyManager().getEconomy().has(player, cost)) {
            player.sendMessage(ChatColor.RED+"You do not have enough money ("+cost+")");
            return true;
        }

        plugin.getEconomyManager().getEconomy().withdrawPlayer(player, cost);
        ItemUtils.authentificateItem(item, player);
        player.sendMessage(ChatColor.GREEN+"Item successfully authentificate!");

        return true;
    }
}
