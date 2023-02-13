package com.panicsoda.experimental.buildbattle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

/*
 * Entry point for the build battle plugin.
 *
 * @author jcedeno
 */
public class BuildBattle extends JavaPlugin{
    private @Getter Logger _logger = LogManager.getLogger();

    @Override
    public void onEnable(){
        
    }
}