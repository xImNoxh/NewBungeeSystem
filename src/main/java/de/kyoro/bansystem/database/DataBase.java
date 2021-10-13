package de.kyoro.bansystem.database;

import de.kyoro.bansystem.BungeeSystem;

import java.sql.Connection;
import java.sql.ResultSet;

public abstract class DataBase {

    protected BungeeSystem bungeeSystem;

    protected void Database(final BungeeSystem bungeeSystem) {
        this.bungeeSystem = bungeeSystem;
    }

    public abstract void connect();

    public abstract void disconnect();

    public abstract Connection getConnection();

    public abstract boolean isConnected();

    public abstract ResultSet query(String query);

    public abstract void update(String update);
}
