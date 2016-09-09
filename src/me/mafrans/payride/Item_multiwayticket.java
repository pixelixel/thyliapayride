/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.mafrans.payride;

import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author malmar03
 */
public class Item_multiwayticket 
{
    public static ItemStack getItem()
    {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        
        meta.setLore(Arrays.asList(ChatColor.YELLOW + "10 Rides Left"));
        meta.setDisplayName(ChatColor.GREEN + "Multi Way Ticket");
        item.setItemMeta(meta);
        return item;
    }
    
    public static int getRides(ItemStack stack)
    {
        String lore = ChatColor.stripColor(stack.getItemMeta().getLore().get(0));
        return Integer.parseInt(lore.replace(" Rides Left", ""));
    }
    
    public static void setRides(ItemStack stack, int amount)
    {
        ItemMeta meta = stack.getItemMeta();
        meta.setLore(Arrays.asList(ChatColor.YELLOW + String.valueOf(amount) + " Rides Left"));
        stack.setItemMeta(meta);
    }
}
