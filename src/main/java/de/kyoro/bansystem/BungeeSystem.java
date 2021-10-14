package de.kyoro.bansystem;

import de.kyoro.bansystem.config.Config;
import de.kyoro.bansystem.config.ConfigReader;
import de.kyoro.bansystem.database.MySQL;
import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public class BungeeSystem extends Plugin {

    private static BungeeSystem instance;
    private static MySQL mySQL;
    private static Config config = new ConfigReader().readConfig();

    @Override
    public void onEnable() {
        instance = this;
        ProxyServer.getInstance().getConsole().sendMessage(config.getPrefix() + "§7Das §a§lBungeeSystem §7wurde erfolgreich §a§lgestartet§7.");
        mySQL = new MySQL();
        mySQL.connect();
    }

    @Override
    public void onDisable() {
        ProxyServer.getInstance().getConsole().sendMessage(config.getPrefix() + "§7Das §a§lBungeeSystem §7wurde erfolgreich §c§lgestoppt§7.");
        mySQL.disconnect();
    }

    public static BungeeSystem getInstance() {
        return instance;
    }

    public static MySQL getMySQL() {
        return mySQL;
    }
}
