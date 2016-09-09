/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.mafrans.payride;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author malmar03
 */
public class Item_onewayticket 
{
    public static ItemStack getItem()
    {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        
        meta.setDisplayName(ChatColor.AQUA + "One Way Ticket");
        item.setItemMeta(meta);
        return item;
    }
}
