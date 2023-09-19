package dev.a8kj.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.a8kj.ClanAddonsMain;
import dev.a8kj.database.data.management.ClanManager;
import dev.a8kj.leaderboard.OrderBy;
import dev.a8kj.leaderboard.hologramapi.manager.LeaderboardBuilder;
import dev.a8kj.logger.LoggerManager;
import dev.a8kj.util.ChatUtils;
import dev.a8kj.util.DateUtils;

public class LeaderboardCommand extends LoggerManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            logger.warning("this command only for player usage !");
            return false;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("leaderboard.setup")) {
            ChatUtils.sendColoredMessage(player,
                    "&cHey , you don't have enough permission to perform/execute this command !");
            return false;
        }

        switch (args.length) {
            case 0:
                ChatUtils.sendColoredMessage(player, "&cWrong usage try to type : /setlb (deaths/kills)");
                break;

            case 1:
                LeaderboardBuilder leaderboardBuilder = new LeaderboardBuilder(player.getLocation());
                if (!args[0].toLowerCase().equalsIgnoreCase("deaths")
                        || !args[0].toLowerCase().equalsIgnoreCase("kills")) {
                    ChatUtils.sendColoredMessage(player, "&cWrong usage try to type : /setlb (deaths/kills)");
                    return false;
                } else if (args[0].toLowerCase().equalsIgnoreCase("deaths")) {
                    leaderboardBuilder.setHeader("Top 10 deaths leaderboard")
                            .setInsideLines(
                                    new ClanManager(ClanAddonsMain.getConnectionManager()).getTop(10, OrderBy.DEATHS))
                            .setFooter("Last update was in : " + DateUtils.getFormattedDate()).build();
                } else {
                    
                }

                break;

            default:
                ChatUtils.sendColoredMessage(player, "&cWrong usage try to type : /setlb (deaths/kills)");
                break;
        }
        return true;
    }

}
