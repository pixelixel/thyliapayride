package me.mafrans.payride;

import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class CloseGate extends BukkitRunnable {

    private final PayRide plugin;
    private final Block gate;

    public CloseGate(PayRide plugin, Block gate) 
    {
        this.plugin = plugin;
        this.gate = gate;
    }

    @Override
    public void run() 
    {
        byte data = gate.getData();
        
        if(data < 4)
        {
            cancel();
            return;
        }
        else if(data < 8)
        {
            switch(data)
            {
                case 4:{gate.setData((byte)0);cancel();return;}
                case 5:{gate.setData((byte)1);cancel();return;}
                case 6:{gate.setData((byte)1);cancel();return;}
                case 7:{gate.setData((byte)1);cancel();return;}
            }
        }
        else
        {
            cancel();
            return;
        }
    }

}
