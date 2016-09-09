/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.mafrans.payride;

import java.util.Arrays;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

/**
 *
 * @author malmar03
 */
public class Command_payride implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(args.length < 1)
        {
            return false;
        }
        switch(args[0].toLowerCase())
        {
            case "wand":
            case "connector":
            {
                ((Player)sender).getInventory().addItem(Item_connector.getItem());
                return true;
            }
            
            case "ticket":
            {
                ((Player)sender).openInventory(ChooseTicketGUI.getInventory());
                return true;
            }
            
            case "master":
            case "npc":
            case "villager":
            case "ticketmaster":
            {
                Location loc = ((Player)sender).getTargetBlock((Set<Material>)null, 50).getLocation().add(0, 1, 0);
                Villager master = (Villager)(((Player)sender).getWorld().spawn(loc, Villager.class));
                master.setCustomName(ChatColor.GREEN + "Ticket Master");
                master.setAI(false);
                master.setInvulnerable(true);
                master.setTarget((LivingEntity)((Player)sender));
            }
        }
        
        
        return true;
    }

}
