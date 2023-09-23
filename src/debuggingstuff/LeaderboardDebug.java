package debuggingstuff;

import java.util.Arrays;
import java.util.List;

import dev.a8kj.leaderboard.hologramapi.manager.LeaderboardBuilder;
import dev.a8kj.util.DateUtils;

public class LeaderboardDebug {

    public static void main(String[] args) {

        List topPlayers = Arrays.asList(" #1 Nigga - 14500", " #1 Figga - 1500");

        new LeaderboardBuilder().setHeader("     Top Kills Leaderboard     ")
                .setInsideLines(topPlayers).setFooter(" >> Last update was at " + DateUtils.getFormattedDate()).debug();
    }
}
