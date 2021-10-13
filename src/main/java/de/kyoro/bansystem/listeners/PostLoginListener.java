package de.kyoro.bansystem.listeners;

import de.kyoro.bansystem.api.KyoroPlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostLoginListener implements Listener {

    @EventHandler
    public void onhandel(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        KyoroPlayer kyoroPlayer = new KyoroPlayer(player.getUniqueId());
        if(!kyoroPlayer.playerExists()) {
            kyoroPlayer.createUser(player.getName());
            kyoroPlayer.setCoins(1000);
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy - HH:mm");
            final String message = "" + simpleDateFormat.format(new Date());
            kyoroPlayer.setFirstLogin(message);
            for (ProxiedPlayer proxiedPlayer : ProxyServer.getInstance().getPlayers()) {
                proxiedPlayer.sendMessage("§8§m---------------");
                proxiedPlayer.sendMessage(" ");
                proxiedPlayer.sendMessage("§7Der Spieler §6" + player.getName() + " §7ist der §6#" + kyoroPlayer.getID() + " §7Spieler auf dem Netzwerk§8.");
                proxiedPlayer.sendMessage(" ");
                proxiedPlayer.sendMessage("§8§m---------------");
            }
        }
    }
}
