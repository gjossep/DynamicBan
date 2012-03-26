package me.xdrapor.dynamicban.commands;

import java.net.InetSocketAddress;

import me.xdrapor.dynamicban.DynamicBan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class DynamicIPList implements CommandExecutor
{
	
	private DynamicBan plugin;
	
	public DynamicIPList(DynamicBan plugin) 
	{
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender cs, Command cmd, String alias, String[] args) 
	{
		if (cmd.getName().equalsIgnoreCase("dynlist"))
		{						
			if(cs instanceof Player){
				cs.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_RED + "DynamicBan" + ChatColor.DARK_PURPLE + "] : " + ChatColor.RED +ChatColor.RED + "This command can only be used by the console!");
			    return true;     
			}
		
	}
		if (cmd.getName().equalsIgnoreCase("dynlist"))
		{
			System.out.println("[DynamicBan] : List of every onlineplayer's IP-Address!");
				for (Player onlinePlayer : plugin.getServer().getOnlinePlayers())
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
									
						//String IPAddressDelimited = delimitedOne[1];		// 127.0.0.1:34657
						String IPAddressDelimitedTwo = delimitedTwo[0];     // 127.0.0.1
						
						cs.sendMessage("Name : " + onlinePlayerName + "IP : " + IPAddressDelimitedTwo);
		            }
		}
		return true;
		}
	}
