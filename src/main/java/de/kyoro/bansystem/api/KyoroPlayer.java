package de.kyoro.bansystem.api;

import de.kyoro.bansystem.BungeeSystem;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@AllArgsConstructor
public class KyoroPlayer {

    private final UUID uuid;

    /*
    regestrierungs id
    first login
    last login
    loginstreak
    onlinetime
    coins
    notify
     */


    public void createUser(String name) {
        if(playerExists()) return;
        BungeeSystem.getMySQL().update("INSERT INTO Users (Name, UUID, ID, FirstLogin, LastLogin, OnlineTime, LoginStreak, Coins, NotifyState)" +
                " VALUES ('" + name + "', '" + this.uuid + "', 0, '', '', 0, 0, 0, 0)");
    }

    public boolean playerExists() {
        boolean user = false;
        try {
            ResultSet result = BungeeSystem.getMySQL().query("SELECT * FROM Users WHERE UUID LIKE '" + this.uuid + "';");
            user = result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    //<editor-fold desc="Coins">
    public int getCoins() {
        int coins = -1;
        try {
            ResultSet result = BungeeSystem.getMySQL().query("SELECT * FROM Users WHERE UUID LIKE '" + this.uuid + "';");
            if (result.next())
                coins = result.getInt("Coins");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coins;
    }

    public void setCoins(int Amount) {
        BungeeSystem.getMySQL().update("UPDATE Users SET Coins= '" + Amount + "' WHERE UUID LIKE '" + this.uuid + "';");
    }

    public void addCoins(int Amount) {
        setCoins(this.getCoins() + Amount);
    }

    public void removeCoins(int Amount) {
        setCoins(this.getCoins() - Amount);
    }

    public void resetCoins() {
        setCoins(0);
    }
    //</editor-fold>

    //<editor-fold desc="Notify">
    public void setNotifyState(int State) {
        try {
            BungeeSystem.getMySQL().update("UPDATE Users SET NotifyState= '" + State + "' WHERE UUID= '" + this.uuid + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNotifyState() {
        int state = 0;
        try {
            ResultSet result = BungeeSystem.getMySQL().query("SELECT * FROM Users WHERE UUID LIKE '" + this.uuid + "';");
            if (result.next())
                state = result.getInt("NotifyState");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return state;
    }
    //</editor-fold>

    //<editor-fold desc="OnlineTime">
    public void setOnlineTime(long millis) {
        try {
            BungeeSystem.getMySQL().update("UPDATE Users SET OnlineTime= '" + millis + "' WHERE UUID= '" + this.uuid + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getOnlineTime() {
        int millis = 0;
        try {
            ResultSet result = BungeeSystem.getMySQL().query("SELECT * FROM Users WHERE UUID LIKE '" + this.uuid + "';");
            if (result.next())
                millis = result.getInt("OnlineTime");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return millis;
    }

    public void resetOnlineTime() {
        setOnlineTime(0);
    }
    //</editor-fold>

    //<editor-fold desc="LoginStreak">
    public void addStreak() {
        try {
            int streak = getStreak();
            BungeeSystem.getMySQL().update("UPDATE Users SET LoginStreak= '" + streak+1 + "' WHERE UUID= '" + this.uuid + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getStreak() {
        int streak = 0;
        try {
            ResultSet result = BungeeSystem.getMySQL().query("SELECT * FROM Users WHERE UUID LIKE '" + this.uuid + "';");
            if (result.next())
                streak = result.getInt("LoginStreak");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return streak;
    }

    public void resetStreak() {
        try {
            BungeeSystem.getMySQL().update("UPDATE Users SET LoginStreak= '" + "0" + "' WHERE UUID= '" + this.uuid + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold desc="FirstLogin">
    public void setFirstLogin(String date) {
        try {
            BungeeSystem.getMySQL().update("UPDATE Users SET FirstLogin= '" + date + "' WHERE UUID= '" + this.uuid + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFirstLogin() {
        String date = "XX:XX XX.XX.XXXX";
        try {
            ResultSet result = BungeeSystem.getMySQL().query("SELECT * FROM Users WHERE UUID LIKE '" + this.uuid + "';");
            if (result.next())
                date = result.getString("FirstLogin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }
    //</editor-fold>

    //<editor-fold desc="LastLogin">
    public void setLastLogin(String date) {
        try {
            BungeeSystem.getMySQL().update("UPDATE Users SET LastLogin= '" + date + "' WHERE UUID= '" + this.uuid + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLastLogin() {
        String date = "XX:XX XX.XX.XXXX";
        try {
            ResultSet result = BungeeSystem.getMySQL().query("SELECT * FROM Users WHERE UUID LIKE '" + this.uuid + "';");
            if (result.next())
                date = result.getString("LastLogin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }
    //</editor-fold>

    //<editor-fold desc="ID">
    public int getID() {
        int id = 0;
        try {
            ResultSet result = BungeeSystem.getMySQL().query("SELECT * FROM Users WHERE UUID LIKE '" + this.uuid + "';");
            if (result.next())
                id = result.getInt("ID");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
    //</editor-fold>
}
