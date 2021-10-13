package de.kyoro.bansystem;

import de.kyoro.bansystem.database.MySQL;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public class BungeeSystem extends Plugin {

    private static BungeeSystem instance;
    private static MySQL mySQL;

    @Override
    public void onEnable() {
        instance = this;
        mySQL = new MySQL();
        mySQL.connect();
    }

    @Override
    public void onDisable() {

    }

    public static BungeeSystem getInstance() {
        return instance;
    }

    public static MySQL getMySQL() {
        return mySQL;
    }
}
