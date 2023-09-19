package dev.a8kj.partyandfriends.clan.util;

import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClansManager;
import lombok.NonNull;

public class ClanUtils {

    private ClanUtils() {
        throw new IllegalStateException("Utility class");
    }

    static ClansManager clanManager = ClansManager.getInstance();

    public static boolean isAvailable(@NonNull String clanName) {
        return clanManager.getClan(clanName) != null;
    }

    public static Clan getClan(@NonNull String clanName) {
        if (!isAvailable(clanName))
            return null;

        return clanManager.getClan(clanName);
    }

    public static String getClanColor(@NonNull String clanName) {
        if (!isAvailable(clanName))
            return null;

        return clanManager.getClan(clanName).getClanColor();
    }

    public static String getClanTag(@NonNull String clanName) {
        if (!isAvailable(clanName))
            return null;

        return clanManager.getClan(clanName).getClanTag();
    }

    public static String getColoredClanTag(@NonNull String clanName) {
        if (!isAvailable(clanName))
            return null;

        return clanManager.getClan(clanName).getColoredClanTag();
    }
}
