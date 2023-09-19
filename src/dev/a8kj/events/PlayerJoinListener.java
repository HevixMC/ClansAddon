package dev.a8kj.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev.a8kj.database.data.management.ClanManager;
import dev.a8kj.database.impl.ConnectionManager;
import dev.a8kj.partyandfriends.clan.impl.PlayerManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class PlayerJoinListener implements Listener {

    @Getter
    @Setter
    private ConnectionManager connectionManager;

    public PlayerJoinListener(@NonNull ConnectionManager connectionManager) {
        setConnectionManager(connectionManager);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player == null)
            return;

        PlayerManager playerManager = new PlayerManager(player);

        if (!playerManager.inClan())
            return;

        String clanName = playerManager.getClanName();

        ClanManager clanManager = new ClanManager(getConnectionManager());

        if (!clanManager.isStored(clanName)) {
            clanManager.addClan(clanName);
        }
    }

}
