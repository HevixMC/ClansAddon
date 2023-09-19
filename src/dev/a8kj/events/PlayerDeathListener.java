package dev.a8kj.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import dev.a8kj.database.data.management.ClanManager;
import dev.a8kj.database.impl.ConnectionManager;
import dev.a8kj.leaderboard.OrderBy;
import dev.a8kj.partyandfriends.clan.impl.PlayerManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class PlayerDeathListener implements Listener {

    @Getter
    @Setter
    public ConnectionManager connectionManager;

    public PlayerDeathListener(@NonNull ConnectionManager connectionManager) {
        setConnectionManager(connectionManager);
    }

    private final ClanManager ourClanManager = new ClanManager(connectionManager);

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();

        if (victim == null)
            return;

        if (killer == null)
            return;

        PlayerManager victimManager = new PlayerManager(victim);
        PlayerManager killerManager = new PlayerManager(killer);

        if (!victimManager.getClanName().equals(killerManager.getClanName())) {
            if (!(victimManager.inClan()) && !(killerManager.inClan())) {
                // nothing
            } else if ((victimManager.inClan()) && (killerManager.inClan())) {
                ourClanManager.addDataToClan(victimManager.getClanName(), OrderBy.DEATHS);
                ourClanManager.addDataToClan(killerManager.getClanName(), OrderBy.KILLS);
            } else if (!(victimManager.inClan()) && (killerManager.inClan())) {
                ourClanManager.addDataToClan(killerManager.getClanName(), OrderBy.KILLS);
            } else if ((victimManager.inClan()) && !(killerManager.inClan())) {
                ourClanManager.addDataToClan(victimManager.getClanName(), OrderBy.DEATHS);
            } else {
                throw new IllegalStateException();
            }

            // CHATGPT : if i remove first if and replace that with else , the condition will
            // work fine of not such as
            /*
             * if ((victimManager.inClan()) && (killerManager.inClan())) {
             * ourClanManager.addDataToClan(victimManager.getClanName(), OrderBy.DEATHS);
             * ourClanManager.addDataToClan(killerManager.getClanName(), OrderBy.KILLS);
             * } else if (!(victimManager.inClan()) && (killerManager.inClan())) {
             * ourClanManager.addDataToClan(killerManager.getClanName(), OrderBy.KILLS);
             * } else if ((victimManager.inClan()) && !(killerManager.inClan())) {
             * ourClanManager.addDataToClan(victimManager.getClanName(), OrderBy.DEATHS);
             * } else {
             * // nothing
             * }
             */
        }

    }

}
