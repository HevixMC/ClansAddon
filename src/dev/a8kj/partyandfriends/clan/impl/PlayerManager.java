package dev.a8kj.partyandfriends.clan.impl;

import org.bukkit.entity.Player;

import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClanPlayer;
import dev.a8kj.partyandfriends.clan.IPlayer;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class PlayerManager implements IPlayer {

    @Getter
    @Setter
    private Player player;

    public PlayerManager(@NonNull Player player) {
        setPlayer(player);
    }

    @Override
    public String getClanName() {
        return clansManager.getClan(getPafPlayer(player)) != null
                ? clansManager.getClan(getPafPlayer(player)).getClanName()
                : null;
    }

    @Override
    public ClanPlayer getClanPlayer() {
        return clansManager.getClanPlayer(getPafPlayer(player)) != null
                ? clansManager.getClanPlayer(getPafPlayer(player))
                : null;
    }

    @Override
    public Clan getClan() {
        return clansManager.getClan(getPafPlayer(player)) != null
                ? clansManager.getClan(getPafPlayer(player))
                : null;
    }

    @Override
    public String getClanColor() {
        return clansManager.getClan(getPafPlayer(player)) != null
                ? clansManager.getClan(getPafPlayer(player)).getClanColor()
                : null;
    }

    public PAFPlayer getPafPlayer(@NonNull Player player) {
        return PAFPlayerManager.getInstance().getPlayer(player);
    }

    @Override
    public boolean inClan() {
        return getClan() != null;
    }

}
