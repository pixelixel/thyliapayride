package me.mafrans.payride;

import java.util.List;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ChoosePaymentGUI
{
    private static Inventory inv = Bukkit.createInventory(null, getSize(), getName());

    
    public static String getName() 
    {
        return "Choose Payment";
    }

    public static void setItems() 
    {
        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("5$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("10$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("15$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("20$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("25$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("30$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("35$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("40$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("45$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("50$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("55$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("60$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("65$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("70$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("75$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("80$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("85$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("90$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("95$");item.setItemMeta(meta);inv.addItem(item);
        meta.setDisplayName("100$");item.setItemMeta(meta);inv.addItem(item);
    }

    public static int getSize() 
    {
        int size = 27;
        return size;
    }
    
    public static ItemStack[] getItems() 
    {
        return inv.getStorageContents();
    }

    public static void itemInteract(InventoryClickEvent e) 
    {
        ItemStack is = Item_multiwayticket.getItem();
        switch(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()))
        {
            case "5$": {Item_multiwayticket.setRides(is, 1);}
            case "10$": {Item_multiwayticket.setRides(is, 2);}
            case "15$": {Item_multiwayticket.setRides(is, 3);}
            case "20$": {Item_multiwayticket.setRides(is, 4);}
            case "25$": {Item_multiwayticket.setRides(is, 5);}
            case "30$": {Item_multiwayticket.setRides(is, 6);}
            case "35$": {Item_multiwayticket.setRides(is, 7);}
            case "40$": {Item_multiwayticket.setRides(is, 8);}
            case "45$": {Item_multiwayticket.setRides(is, 9);}
            case "50$": {Item_multiwayticket.setRides(is, 10);}
            case "55$": {Item_multiwayticket.setRides(is, 11);}
            case "60$": {Item_multiwayticket.setRides(is, 12);}
            case "65$": {Item_multiwayticket.setRides(is, 13);}
            case "70$": {Item_multiwayticket.setRides(is, 14);}
            case "75$": {Item_multiwayticket.setRides(is, 15);}
            case "80$": {Item_multiwayticket.setRides(is, 16);}
            case "85$": {Item_multiwayticket.setRides(is, 17);}
            case "90$": {Item_multiwayticket.setRides(is, 18);}
            case "95$": {Item_multiwayticket.setRides(is, 19);}
            case "100$": {Item_multiwayticket.setRides(is, 20);}
        }
        e.getWhoClicked().closeInventory();
        e.getWhoClicked().getInventory().addItem(is);
        e.setResult(Event.Result.DENY);
        e.setCancelled(true);
        return;
    }

    public static Inventory getInventory() 
    {
        return inv;
    }

    public static void register() 
    {
        setItems();
    }
}
