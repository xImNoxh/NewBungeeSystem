package de.kyoro.bansystem.database;

import de.kyoro.bansystem.BungeeSystem;
import de.kyoro.bansystem.config.Config;
import de.kyoro.bansystem.config.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL extends DataBase{

    private Connection connection;

    @Override
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Config config = new ConfigReader().readConfig();
            this.connection = DriverManager.getConnection("jdbc:mysql://" + config.getAddress() + ":" + config.getPort() + "/"
                    + config.getDatabase() + "?autoReconnect=true", config.getUser(), config.getPasswort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        if (isConnected()) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean isConnected() {
        try {
            if(connection != null && connection.isValid(3)) return true;
        }catch (SQLException ignored) {

        }
        return false;
    }


    @Override
    public ResultSet query(String query) {
        if (!isConnected()) connect();

        ResultSet result = null;

        try {
            result = connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void update(String update) {
        if (!isConnected()) connect();

        try {
            this.connection.createStatement().executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTabel() {
        BungeeSystem.getMySQL().update(
                "CREATE TABLE IF NOT EXISTS Users " +
                        "(Name VARCHAR(16),UUID VARCHAR(100), ID INT NOT NULL AUTO_INCREMENT, FirstLogin VARCHAR(100)" +
                        ", LastLogin VARCHAR(100), OnlineTime BIGINT, LoginStreak INT, " +
                        "Coins INT, NotifyState INT);");
    }
}
