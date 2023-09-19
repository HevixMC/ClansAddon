package dev.a8kj.leaderboard.hologramapi.manager;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import dev.a8kj.ClanAddonsMain;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class LeaderboardBuilder {

    @Getter
    @Setter
    private Location location;

    @Getter
    @Setter
    private Hologram hologram;

    private String header;
    private String footer;

    private List<String> insideLines;

    public LeaderboardBuilder(@NonNull Location location) {
        setLocation(location);
    }

    public LeaderboardBuilder setHeader(@NonNull String line) {
        hologram.appendTextLine(line);
        return this;
    }

    public LeaderboardBuilder setInsideLines(@NonNull List<String> insideLines) {
        this.insideLines = insideLines;
        return this;
    }

    public LeaderboardBuilder setFooter(@NonNull String line) {
        hologram.appendTextLine(line);
        return this;
    }

    public LeaderboardBuilder appendTextLine(@NonNull String line) {
        hologram.appendTextLine(line);
        return this;
    }

    public LeaderboardBuilder appendTextLines(@NonNull List<String> lines) {
        for (String line : lines) {
            hologram.appendTextLine(line);
        }
        return this;
    }

    public LeaderboardBuilder appendItemLine(@NonNull ItemStack itemStack) {
        hologram.appendItemLine(itemStack);
        return this;
    }

    public LeaderboardBuilder appendSeparator() {
        hologram.appendTextLine(" ");
        return this;
    }

    public Hologram build() {
        setHologram(HologramsAPI.createHologram(ClanAddonsMain.getInstance(), location));
        if (header != null)
            this.appendTextLine(header).appendSeparator();
        if (insideLines != null)
            this.appendTextLines(insideLines).appendSeparator();
        if (footer != null)
            this.appendTextLine(footer);
        return hologram;
    }

    public LeaderboardBuilder clearLines() {
        hologram.clearLines();
        return this;
    }

    public void update() {
        hologram.clearLines();
        this.build();
    }
}
