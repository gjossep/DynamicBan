package me.xdrapor.dynamicban.commands;

import java.awt.Color;
import java.net.InetSocketAddress;

import me.xdrapor.dynamicban.DynamicBan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class DynamicIPBanKick implements CommandExecutor
{
	
	private DynamicBan plugin;
	
	public DynamicIPBanKick(DynamicBan plugin) 
	{
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender cs, Command cmd, String alias, String[] args) 
	{
		Player other = (Bukkit.getServer().getPlayer(args[0]));
        if (other == null) {
           cs.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_RED + "DynamicBan" + ChatColor.DARK_PURPLE + "] : " + ChatColor.RED +args[0] + " is not online!");
           return true;
        }
		
		if(args.length == 0)
		{
			cs.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_RED + "DynamicBan" + ChatColor.DARK_PURPLE + "] : " + ChatColor.RED + " The correct command is /dynbk (name)!");
			cs.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_RED + "DynamicBan" + ChatColor.DARK_PURPLE + "] : " + ChatColor.RED + " Usage: IP Bans and kicks the player specified.");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("dynbk"))
		{
			
			Player playerToBan = plugin.getServer().getPlayer(args[0].toLowerCase());			
			InetSocketAddress IPAdressPlayer = playerToBan.getAddress();
						
			String undelimited = IPAdressPlayer.toString(); //  /127.0.0.1:34657
			String[] delimitedOne;                          //   127.0.0.1:34657
			String[] delimitedTwo;                          //   127.0.0.1
			String delimiter = "/";
			String delimiterTwo = ":";
						
			delimitedOne = undelimited.split(delimiter);        //   127.0.0.1:34657
			delimitedTwo = delimitedOne[1].split(delimiterTwo); //   127.0.0.1
						
			String IPAddressDelimited = delimitedOne[1];		// 127.0.0.1:34657
			String IPAddressDelimitedTwo = delimitedTwo[0];     // 127.0.0.1
			
			if(cs instanceof Player)
			{
				Player player = (Player)cs;
					
				if(player.hasPermission("dynamicban.ipbankick"))
				{
										
					player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_RED + "DynamicBan" + ChatColor.DARK_PURPLE + "] : " +args[0] + "'s IP-Address : " + IPAddressDelimited + " is now banned and kicked from the server!");
					plugin.getServer().banIP(IPAddressDelimitedTwo);
					playerToBan.kickPlayer("[DynamicBan] : You have been IP-Banned and kicked!");
					
				
				}
				else
				{
					player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_RED + "DynamicBan" + ChatColor.DARK_PURPLE + "] : " + ChatColor.RED + "You do not have the permissions to ban " + args[0] + "'s IP and kick him from the server");
				}
			}
			else
			{
				cs.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_RED + "DynamicBan" + ChatColor.DARK_PURPLE + "] : " + ChatColor.RED + args[0] + "'s IP-Address : " + IPAddressDelimited + " is now banned and kicked from the server!");
				plugin.getServer().banIP(IPAddressDelimitedTwo);
				playerToBan.kickPlayer("[DynamicBan] : You have been IP-banned and kicked!");
			}
		}
		return true;
	}
}