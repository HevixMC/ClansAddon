package dev.a8kj.partyandfriends.clan;

import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClanPlayer;
import de.simonsator.partyandfriends.clan.api.ClansManager;

public interface IPlayer {

    ClansManager clansManager = ClansManager.getInstance();

    boolean inClan();

    String getClanColor();

    String getClanName();

    ClanPlayer getClanPlayer();

    Clan getClan();

}
