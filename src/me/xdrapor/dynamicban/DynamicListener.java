package me.xdrapor.dynamicban;

import me.xdrapor.dynamicban.DynamicBan;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DynamicListener implements Listener {

	public DynamicBan plugin;
	
	public DynamicListener(DynamicBan plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		plugin.addPlayer(event.getPlayer());
	}
	
}
