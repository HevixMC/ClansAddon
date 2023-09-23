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

    public LeaderboardBuilder() {
        setLocation(null);
    }

    public LeaderboardBuilder setHeader(@NonNull String line) {
        this.header = line;
        return this;
    }

    public LeaderboardBuilder setInsideLines(@NonNull List<String> insideLines) {
        this.insideLines = insideLines;
        return this;
    }

    public LeaderboardBuilder setFooter(@NonNull String line) {
        this.footer = line;
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

    public LeaderboardBuilder debug() {
        StringBuilder strBuilder = new StringBuilder();
        if (header != null)
            strBuilder.append(header).append("\n").append("\n");
        if (insideLines != null) {
            for (String myLine : insideLines) {
                strBuilder.append(myLine);
                strBuilder.append("\n");
            }
            strBuilder.append("\n");
        }
        if (footer != null)
            strBuilder.append(footer);

        System.out.println(strBuilder.toString());
        return this;
    }

    public void debugg() {
        StringBuilder strBuilder = new StringBuilder();
        if (header != null)
            strBuilder.append(header).append("\n").append("\n");
        if (insideLines != null) {
            for (String myLine : insideLines) {
                strBuilder.append(myLine);
                strBuilder.append("\n");
            }
            strBuilder.append("\n");
        }
        if (footer != null)
            strBuilder.append(footer);

        System.out.println(strBuilder.toString());

    }

    public void update() {
        hologram.clearLines();
        this.build();
    }
}
