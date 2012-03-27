package me.xdrapor.dynamicban;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import me.xdrapor.dynamicban.DynamicListener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import me.xdrapor.dynamicban.commands.DynamicCheck;
import me.xdrapor.dynamicban.commands.DynamicIPBan;
import me.xdrapor.dynamicban.commands.DynamicIPBanKick;
import me.xdrapor.dynamicban.commands.DynamicIPList;

import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class DynamicBan extends JavaPlugin implements CommandExecutor{
	public DynamicListener playerListener = new DynamicListener(this);
	
	public static Logger log = Logger.getLogger("Minecraft");
	private DynamicIPBan myExecutor;
	private DynamicIPBanKick myExecutor2;
	private DynamicCheck myExecutor3;
	private DynamicIPList myExecutor4;
	
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(playerListener, this);
		myExecutor = new DynamicIPBan(this);
		myExecutor2 = new DynamicIPBanKick(this);
		myExecutor3 = new DynamicCheck(this);
		myExecutor4 = new DynamicIPList(this);

		getCommand("dynban").setExecutor(myExecutor);
		getCommand("dynbk").setExecutor(myExecutor2);
		getCommand("dyncheck").setExecutor(myExecutor3);
		getCommand("dynlist").setExecutor(myExecutor4);
		System.out.println("[" + getDescription().getName() + "] Plugin has been enabled!"); 
		this.getConfig().createSection("DynamicLogger - Player flatfile database.");
		this.getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public void onDisable() {
		saveConfig();
		System.out.println("[" + getDescription().getName() + "] Plugin has been disabled!");
		}
public void addPlayer(Player player) {;
	for (Player onlinePlayer : this.getServer().getOnlinePlayers())
	{
			String onlinePlayerName = onlinePlayer.getName();
			InetSocketAddress onlinePlayerIP = onlinePlayer.getAddress();
			
			String undelimited = onlinePlayerIP.toString(); //  /127.0.0.1:34657
			String[] delimitedOne;                          //   127.0.0.1:34657
			String[] delimitedTwo;                          //   127.0.0.1
			String delimiter = "/";
			String delimiterTwo = ":";
						
			delimitedOne = undelimited.split(delimiter);        //   127.0.0.1:34657
			delimitedTwo = delimitedOne[1].split(delimiterTwo); //   127.0.0.1
						
			String IPAddressDelimited = delimitedOne[1];		// 127.0.0.1:34657
			String IPAddressDelimitedTwo = delimitedTwo[0];     // 127.0.0.1
	if(! getConfig().contains(onlinePlayerName)) {
		getConfig().set(onlinePlayerName + ".IP-Address", delimitedOne[1]);
	}
	saveConfig();
    }
  }
}