package dev.a8kj.database.data.management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import dev.a8kj.database.impl.ConnectionManager;
import dev.a8kj.leaderboard.OrderBy;
import dev.a8kj.logger.LoggerManager;
import dev.a8kj.partyandfriends.clan.util.ClanUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class ClanManager extends LoggerManager {

    @Getter
    @Setter
    private ConnectionManager connectionManager;

    public ClanManager(@NonNull ConnectionManager connectionManager) {
        setConnectionManager(connectionManager);
        init();
    }

    public void init() {
        connectionManager.createTable("clans_data",
                "clan_name varchar(18) not null , " +
                        " clan_kills int unsigned default 0, " +
                        " clan_deaths int unsigned default 0");
    }

    public boolean isStored(@NonNull String clanName) {
        String sqlStatement = "select * from clans_data where clan_name='" + clanName + "'";
        ResultSet resultSet = connectionManager.query(sqlStatement);
        try {
            if (resultSet.next()) {
                return resultSet.getString("clan_name") != null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addClan(@NonNull String clanName) {
        if (isStored(clanName))
            return;

        String sqlStatement = String
                .format("insert into clans_data (clan_name,clan_kills , clan_deaths) values ('%s',0,0);", clanName);
        String message = "";
        if (connectionManager.update(sqlStatement) != -1) {
            message = String.format("Clan with '%s' name has been added to first time into database", clanName);
            logger.info(message);
        } else {
            message = String.format("There is error found while trying to add clan with '%s' name to database !",
                    clanName);
            logger.warning(message);
        }
    }

    public void addDataToClan(@NonNull String clanName, @NonNull OrderBy orderBy) {
        if (!isStored(clanName)) {
            addClan(clanName);
            return;
        }

        String sqlStatement = String.format("update clans_data set %s= %s + 1 where clan_name ='%s'",
                orderBy.getType(), orderBy.getType(), clanName);

        String message = "";

        if (connectionManager.update(sqlStatement) != -1) {
            message = String.format("Successfully ! add %s data to clan with '%s' name !", orderBy.name().toLowerCase(),
                    clanName);
            logger.info(message);
        } else {
            message = String.format(
                    "There is error found while trying to add data to clan with '%s' name to database !",
                    clanName);
            logger.warning(message);
        }
    }

    public List<String> getTop(int limit, @NonNull OrderBy orderBy) {
        List<String> list = Collections.emptyList();

        String sqlStatement = String.format("select clanName , %s from clans_data order by %s desc limit %s",
                orderBy.getType(), orderBy.getType(), limit);

        ResultSet resultSet = connectionManager.query(sqlStatement);

        int index = 0;
        try {
            while (resultSet.next()) {

                list.set(++index,
                        "&7#" + index + ClanUtils.getClanColor(resultSet.getString("clan_name"))
                                + resultSet.getString("clan_name") + "&7 - &c"
                                + resultSet.getInt(orderBy.getType()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }
}
