package me.mafrans.payride;

import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

public class PayRide extends JavaPlugin
{
    public static PayRide                               plugin;
    public FileConfiguration                            config;
    public String                                       pluginVersion;
    public List<String>                                 pluginAuthors;
    public PluginDescriptionFile                        info;
    public String                                       pluginName;
    public HashMap<Sign, Block>                         gates;

    @Override
    public void onDisable()
    {
    }

    @Override
    public void onEnable()
    {
        plugin        = this;
        config        = getConfig();
        info          = getDescription();
        pluginName    = info.getFullName();
        pluginVersion = info.getVersion();
        pluginAuthors = info.getAuthors();
        gates         = new HashMap();
        

        // Initialize Commands
        getCommand("payride").setExecutor(new Command_payride());

        // Listeners / Handlers
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);      

        // GUIs
        ChooseTicketGUI.register();
        ChoosePaymentGUI.register();
        
        // Create configs
        this.saveDefaultConfig();

        // Send Message
        System.out.println(pluginName + " version " + pluginVersion + " has been enabled!");
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
