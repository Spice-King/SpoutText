/*
 * This file is part of SpoutText.
 * 
 * SpoutWallet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 
 * SpoutWallet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with SpoutWallet.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.spiceking.plugins.spouttext;

import java.util.logging.Logger;
import java.util.Locale;

import me.spiceking.plugins.spouttext.listeners.SpoutCraftListener;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.config.Configuration;

import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutText extends JavaPlugin {
    
    public Configuration config;
    
    public String display;
    public Integer updateSpeed;
    public Integer ySetting;
    public Integer xSetting;
    
    public Integer colorRed;
    public Integer colorBlue;
    public Integer colorGreen;
    
    public Color color;
    
    public WidgetAnchor location;
    
    public void onDisable() {
        System.out.println(this + " is now disabled!");
    }

    public void onEnable() {
        config = getConfiguration();
        display = config.getString("Display", "Please change me!");
        ySetting = config.getInt("yOffset", 3);
        xSetting = config.getInt("xOffset", 3);
        
        //Color
        colorRed = config.getInt("color.red", 255);
        colorBlue = config.getInt("color.blue", 255);
        colorGreen = config.getInt("color.green", 255);
        
        if ((colorRed > 255) || (colorRed <= -1)){
            colorRed = 255;
            config.setProperty("color.red", colorRed);
        }
        if ((colorBlue > 255) || (colorBlue <= -1)){
            colorBlue = 255;
            config.setProperty("color.blue", colorBlue);
        }
        if ((colorGreen > 255) || (colorGreen <= -1)){
            colorGreen = 255;
            config.setProperty("color.green", colorGreen);
        }
        try {
            location = Enum.valueOf(WidgetAnchor.class, config.getString("location", "TOP_LEFT").toUpperCase(Locale.ENGLISH));
        }
        catch (java.lang.IllegalArgumentException e){
            System.out.print("[SpoutText] Oops, the location you want to start from is not a location Spout knows about.");
            System.out.print("[SpoutText] I'm going to change it back to TOP_LEFT");
            config.setProperty("location", "TOP_LEFT");
            try {
                    location = WidgetAnchor.TOP_LEFT;
            } catch (java.lang.IllegalArgumentException a){
                    System.err.print("[SpoutText] Uh oh, Spout broke something! Tell Spice_King that WidgetAnchor enum has changed!");
            }
        }
        config.save(); //Save the config!
        // make the colors
        color = new Color(new Float(colorRed)/255, new Float(colorGreen)/255, new Float(colorBlue)/255);
        
        Logger log = getServer().getLogger();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Type.CUSTOM_EVENT, new SpoutCraftListener(this), Priority.Low, this);
        System.out.println(this + " is now enabled!");
    }
}
