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
import org.bukkit.inventory.meta.SkullMeta;

public class ChooseTicketGUI
{
    private static Inventory inv = Bukkit.createInventory(null, getSize(), getName());

    
    public static String getName() 
    {
        return "Choose Ticket";
    }

    public static void setItems() 
    {
        inv.addItem(Item_onewayticket.getItem());
        inv.addItem(Item_multiwayticket.getItem());
    }

    public static int getSize() 
    {
        int size = 9;
        return size;
    }
    
    public static ItemStack[] getItems() 
    {
        return inv.getStorageContents();
    }

    public static void itemInteract(InventoryClickEvent e) 
    {
        if(e.getCurrentItem().equals(Item_multiwayticket.getItem()))
        {
            e.getWhoClicked().closeInventory();
            e.getWhoClicked().openInventory(ChoosePaymentGUI.getInventory());
        }
        else
        {
            e.getWhoClicked().closeInventory();
            e.getWhoClicked().getInventory().addItem(Item_onewayticket.getItem());
        }
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
