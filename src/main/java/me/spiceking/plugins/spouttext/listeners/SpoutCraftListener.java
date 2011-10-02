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

package me.spiceking.plugins.spouttext.listeners;

import me.spiceking.plugins.spouttext.SpoutText;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.event.spout.SpoutListener;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author Kyle
 */
public class SpoutCraftListener extends SpoutListener {
    
    SpoutText plugin;
    
    public SpoutCraftListener(SpoutText plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onSpoutCraftEnable(SpoutCraftEnableEvent event) {
        
        // Should not happen
        if(!event.getPlayer().isSpoutCraftEnabled()) {
            //event.getPlayer().sendMessage("This server uses SpoutCraft to display your balance.");
            //event.getPlayer().sendMessage("Install SpoutCraft from http://goo.gl/UbjS1 to see it.");
            return;
        }
        drawGUI(event.getPlayer());
    }

    private void drawGUI(SpoutPlayer sp) {
        //This is the code to start the lable
        GenericLabel display = new GenericLabel(plugin.display);
        // Todo: fundsLable: config the location and colour
        display.setTextColor(plugin.color).setAnchor(plugin.location);
        display.setAlign(plugin.location).setX(plugin.xSetting).setY(plugin.ySetting);
        sp.getMainScreen().attachWidget(plugin, display);
    }
}
