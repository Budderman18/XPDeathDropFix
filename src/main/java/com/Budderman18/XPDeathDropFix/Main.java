package com.budderman18.XPDeathDropFix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class enables and disables the plugin
 * It also imports commands and handles events
 */
public class Main extends JavaPlugin implements Listener { 
    private final ConsoleCommandSender sender = getServer().getConsoleSender();
    private final String prefixMessage = ChatColor.translateAlternateColorCodes('&', "&3[&aXPDeathDropFix&3] ");
    /*
    *
    * Enables the plugin.
    * Checks if MC version isn't the latest.
    * If its not, warn the player about lacking support
    * Checks if server is running offline mode
    * If it is, disable the plugin
    * Also loads death event
    *
    */
    @Override
    public void onEnable() {
        //language variables
        final String unsupportedVersionAMessage = ChatColor.translateAlternateColorCodes('&', "&4XPDeathDropFix does not support your version!"); 
        final String unsupportedVersionBMessage = ChatColor.translateAlternateColorCodes('&', "&aOnly 1.18.2 is supported"); 
        final String unsupportedVersionCMessage = ChatColor.translateAlternateColorCodes('&', "&aThis plugin will likely not work and you will get no support for issues"); 
        final String unsecureServerAMessage = ChatColor.translateAlternateColorCodes('&', "&4NEVER EVER EVER EVER EVER EVER USE OFFLINE MODE!!!!!!!!!!!!!!!"); 
        final String unsecureServerBMessage = ChatColor.translateAlternateColorCodes('&', "&cOffline mode is a serious threat to your server and will never be supported by my plugins!"); 
        final String unsecureServerCMessage = ChatColor.translateAlternateColorCodes('&', "&cTo protect your safety and this plugin''s saftey, this plugin will now be disabled"); 
        final String pluginEnabledMessage = ChatColor.translateAlternateColorCodes('&', "&eXPDeathDropFix by Budderman18 has been enabled!"); 
        //check for correct version
        if (!(Bukkit.getVersion().contains("1.18.2"))) {
            sender.sendMessage(prefixMessage + unsupportedVersionAMessage);
            sender.sendMessage(prefixMessage + unsupportedVersionBMessage);
            sender.sendMessage(prefixMessage + unsupportedVersionCMessage);  
        }
        //check for online mode
        if (!(getServer().getOnlineMode())) {
            sender.sendMessage(prefixMessage + unsecureServerAMessage);
            sender.sendMessage(prefixMessage + unsecureServerBMessage);
            sender.sendMessage(prefixMessage + unsecureServerCMessage);
            getServer().getPluginManager().disablePlugin(this);
        }
        //events
        getServer().getPluginManager().registerEvents(this,this);
        //enable plugin
        getServer().getPluginManager().enablePlugin(this);
        sender.sendMessage(prefixMessage + pluginEnabledMessage);
    }
    /*
    *
    * This method disables the plugin
    *
    */
    @Override
    public void onDisable() {
        final String pluginDisabledMessage = ChatColor.translateAlternateColorCodes('&', "&eXPDeathDropFix by Budderman18 has been disabled!");
        //disables plugin
        getServer().getPluginManager().disablePlugin(this);
        sender.sendMessage(prefixMessage + pluginDisabledMessage);
    }
    /*
    *
    * This method hadles everything involving fixing the xp
    * It'll set the dropped xp to be half of the player's xp
    * like the vanilla game does, with no dumbass
    * limit. (Why is this not fixed in vanilla?)
    *
    */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        //set new value
        int xp = (event.getEntity().getTotalExperience()/2);
        event.setDroppedExp(xp);
    }
}   

