/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.mafrans.payride;

import me.mafrans.payride.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author malmar03
 */
public class Item_connector {
    public static ItemStack getItem()
    {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        
        meta.setDisplayName(ChatColor.RED + "Connector");
        item.setItemMeta(meta);
        return item;
    }
}
