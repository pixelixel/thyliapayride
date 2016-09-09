/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.mafrans.payride;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

/**
 *
 * @author malmar03
 */
public class PlayerListener implements Listener
{
    public Sign tsign = null;
    public Block tgate = null;
    
    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e)
    {
        Player player = e.getPlayer();
        Entity entity = e.getRightClicked();
        
        if(entity == null)
        {
            return;
        }
        if(entity.getCustomName().equals(ChatColor.GREEN + "Ticket Master"))
        {
            player.closeInventory();
            player.openInventory(ChooseTicketGUI.getInventory());
            e.setCancelled(true);
        }
        else
        {
            e.setCancelled(false);
            return;
        }
    }
    
    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        Player player = e.getPlayer();
        Action action = e.getAction();
        Block block = e.getClickedBlock();
        
        if(block == null) return;
        
        //FIX ERRORS
        if(block.getType() != Material.WALL_SIGN && block.getType() != Material.FENCE_GATE && block.getType() != Material.SIGN_POST)
        {
            e.setCancelled(false);
            return;
        }
        else if(block.getType().equals(Material.SIGN_POST) && !PayRide.plugin.gates.containsKey((Sign)block.getState())||block.getType().equals(Material.WALL_SIGN) && !PayRide.plugin.gates.containsKey((Sign)block.getState()))
        {
            e.setCancelled(false);
            return;
        }
        else if(block.getType().equals(Material.FENCE_GATE) && !PayRide.plugin.gates.containsValue(block))
        {
            e.setCancelled(false);
            return;
        }
        //CONNECTOR
        if(player.getItemInHand().getItemMeta().getDisplayName().equals(Item_connector.getItem().getItemMeta().getDisplayName()))
        {
            if(action == Action.RIGHT_CLICK_BLOCK)
            {
                if(block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)
                {
                    Sign sign = (Sign) block.getState();
                    tsign = sign;
                    player.sendMessage("added sign");
                    e.setCancelled(true);
                }
            }
            if(action == Action.LEFT_CLICK_BLOCK)
            {
                if(block.getType() == Material.FENCE_GATE)
                {
                    tgate = block;
                    player.sendMessage("added gate/sign registry");
                    PayRide.plugin.gates.put(tsign, tgate);
                    e.setCancelled(true);
                }
                else
                {
                    e.setCancelled(false);
                    return;
                }
            }
        }
        
        
        //ONE WAY TICKET
        else if(player.getItemInHand().getItemMeta().getDisplayName().equals(Item_onewayticket.getItem().getItemMeta().getDisplayName()))
        {
            if(action == Action.RIGHT_CLICK_BLOCK)
            {
                if(block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)
                {
                    Sign sign = (Sign) block.getState();
                    if(PayRide.plugin.gates.containsKey(sign))
                    {
                        Block blk = PayRide.plugin.gates.get(sign);
                        byte data = blk.getData();
                        if(data > 4)
                        {
                            return;
                        }
                        else
                        {
                            blk.setData((byte) (data + 4));
                        }
                        BukkitTask task = new CloseGate(PayRide.plugin, blk).runTaskLater(PayRide.plugin, 60);
                        player.setItemInHand(new ItemStack(Material.AIR));
                    }
                    e.setCancelled(true);
                }
            }
        }
        
        
        //MULTI WAY TICKET
        else if(player.getItemInHand().getItemMeta().getDisplayName().equals(Item_multiwayticket.getItem().getItemMeta().getDisplayName()) || Item_multiwayticket.getRides(player.getItemInHand()) > 0)
        {
            if(action == Action.RIGHT_CLICK_BLOCK)
            {
                if(block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)
                {
                    Sign sign = (Sign) block.getState();
                    if(PayRide.plugin.gates.containsKey(sign))
                    {
                        Block blk = PayRide.plugin.gates.get(sign);
                        byte data = blk.getData();
                        if(data > 4)
                        {
                            return;
                        }
                        else
                        {
                            blk.setData((byte) (data + 4));
                        }
                        int rides = Item_multiwayticket.getRides(player.getItemInHand()) - 1;
                        Item_multiwayticket.setRides(e.getPlayer().getItemInHand(), rides);
                        BukkitTask task = new CloseGate(PayRide.plugin, blk).runTaskLater(PayRide.plugin, 60);
                    }
                    e.setCancelled(true);
                }
            }
        }
        else if(Item_multiwayticket.getRides(player.getItemInHand()) == 0)
        {
            player.sendMessage("Not enough rides left!");
        }
        
        if(PayRide.plugin.gates.containsValue(block))
        {
            e.setCancelled(true);
        }
        else
        {
            e.setCancelled(false);
            return;
        }
    }
    
    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e)
    {
        Player player = (Player) e.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = e.getCurrentItem(); // The item that was clicked
        Inventory inventory = e.getInventory(); // The inventory that was clicked in

        ChooseTicketGUI chooseTicketGUI = new ChooseTicketGUI();
        ChoosePaymentGUI choosePaymentGUI = new ChoosePaymentGUI();
        
        if(inventory.getName().equalsIgnoreCase(chooseTicketGUI.getName())) {chooseTicketGUI.itemInteract(e);}
        if(inventory.getName().equalsIgnoreCase(choosePaymentGUI.getName())) {choosePaymentGUI.itemInteract(e);}
    }
}
