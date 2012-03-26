package me.xdrapor.dynamicban;

import java.util.logging.Logger;

import me.xdrapor.dynamicban.commands.DynamicCheck;
import me.xdrapor.dynamicban.commands.DynamicIPBan;
import me.xdrapor.dynamicban.commands.DynamicIPBanKick;
import me.xdrapor.dynamicban.commands.DynamicIPList;

import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class DynamicBan extends JavaPlugin{
	
	private Logger log = Logger.getLogger("Minecraft");
	private DynamicIPBan myExecutor;
	private DynamicIPBanKick myExecutor2;
	private DynamicCheck myExecutor3;
	private DynamicIPList myExecutor4;
	
	
	public void onEnable() {

		myExecutor = new DynamicIPBan(this);
		myExecutor2 = new DynamicIPBanKick(this);
		myExecutor3 = new DynamicCheck(this);
		myExecutor4 = new DynamicIPList(this);
		
		getCommand("dynban").setExecutor(myExecutor);
		getCommand("dynbk").setExecutor(myExecutor2);
		getCommand("dyncheck").setExecutor(myExecutor3);
		getCommand("dynlist").setExecutor(myExecutor4);
		
		System.out.println("[" + getDescription().getName() + "] Plugin has been enabled!");

	}
	
	public void onDisable() {
		
		System.out.println("[" + getDescription().getName() + "] Plugin has been disabled!");
		}
}