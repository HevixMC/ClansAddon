package dev.a8kj;

import org.bukkit.plugin.java.JavaPlugin;

import dev.a8kj.configuration.Configuration;
import dev.a8kj.database.impl.ConnectionManager;
import lombok.Getter;
import lombok.Setter;

public class ClanAddonsMain extends JavaPlugin {

    @Getter
    @Setter
    private static ClanAddonsMain instance;
    @Getter
    @Setter
    private static ConnectionManager connectionManager;
    @Getter
    @Setter
    private static Configuration configuration;

    void init() {
        setInstance(this);
        setConfiguration(new Configuration("configuration.yml", this, true));
        ConnectionManager cManager = new ConnectionManager();
        connectionManager.setDatabase(configuration.getConfig().getString("MYSQL.DATABASE", "clan_data"));
        connectionManager.setUsername(configuration.getConfig().getString("MYSQL.USERNAME", "ROOT"));
        connectionManager.setPassword(configuration.getConfig().getString("MYSQL.PASSWORD", "easypassword"));
        connectionManager.setHost(configuration.getConfig().getString("MYSQL.HOST", "localhost"));
        setConnectionManager(cManager);
    }

    @Override
    public void onEnable() {
        init();
    }

    @Override
    public void onDisable() {
        setInstance(null);
    }

}
