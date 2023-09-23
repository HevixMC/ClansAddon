package dev.a8kj.leaderboard.hologramapi.task;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.scheduler.BukkitRunnable;

import dev.a8kj.leaderboard.hologramapi.manager.LeaderboardBuilder;

public class UpdateTask extends BukkitRunnable {

    public static List<LeaderboardBuilder> leaderboards = new ArrayList<LeaderboardBuilder>();

    @Override
    public void run() {
        if (leaderboards.isEmpty()) {
            return;
        }

        for (LeaderboardBuilder leaderboard : leaderboards) {
            if (leaderboard.getHologram() != null) {
                leaderboard.clearLines().build();
            }
        }

    }

}
