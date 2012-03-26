package me.xdrapor.dynamicban.commands;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.logging.Logger;
import me.xdrapor.dynamicban.DynamicBan;
import me.xdrapor.dynamicban.commands.DynamicCheck;
import me.xdrapor.dynamicban.commands.DynamicIPBan;
import me.xdrapor.dynamicban.commands.DynamicIPBanKick;
import me.xdrapor.dynamicban.commands.DynamicIPList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class DynamicCheck implements CommandExecutor
{
	
	private DynamicBan plugin;
	
	public DynamicCheck(DynamicBan plugin) 
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
			cs.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_RED + "DynamicBan" + ChatColor.DARK_PURPLE + "] : " + ChatColor.RED +ChatColor.RED + " The correct command is /dyncheck (name)");
			cs.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_RED + "DynamicBan" + ChatColor.DARK_PURPLE + "] : " + ChatColor.RED +ChatColor.RED + " Usage: Checks the IP of the specified player.");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("dyncheck"))
		{
			
			Player playerToBan = plugin.getServer().getPlayer(args[0].toLowerCase());			
			InetSocketAddress IPAdressPlayer = playerToBan.getAddress();
						
			String undelimited = IPAdressPlayer.toString(); //  /127.0.0.1:34657
			String[] delimitedOne;                          //   127.0.0.1:34657
			//String[] delimitedTwo;                          //   127.0.0.1
			String delimiter = "/";
			//String delimiterTwo = ":";
						
			delimitedOne = undelimited.split(delimiter);        //   127.0.0.1:34657
			//delimitedTwo = delimitedOne[1].split(delimiterTwo); //   127.0.0.1
						
			String IPAddressDelimited = delimitedOne[1];		// 127.0.0.1:34657
			//String IPAddressDelimitedTwo = delimitedTwo[0];     // 127.0.0.1

			if(cs instanceof Player)
			{
				Player player = (Player)cs;
					
				if(player.hasPermission("dynamicban.ipcheck"))
				{
					player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_RED + "DynamicBan" + ChatColor.DARK_PURPLE + "] : " + ChatColor.RED +args[0] + "'s IP-Address is : " + IPAddressDelimited);
					
				}
				else
				{
					player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_RED + "DynamicBan" + ChatColor.DARK_PURPLE + "] : " + ChatColor.RED + "You do not have the permissions to see " + args[0] + "'s IP!");
				}
			}
			else
			{
				cs.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_RED + "DynamicBan" + ChatColor.DARK_PURPLE + "] :" +args[0] + "'s IP-Address is : " + IPAddressDelimited);
			}
		}
		return true;
	}
}
