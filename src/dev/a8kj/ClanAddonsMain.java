package dev.a8kj;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev.a8kj.command.LeaderboardCommand;
import dev.a8kj.configuration.Configuration;
import dev.a8kj.database.impl.ConnectionManager;
import dev.a8kj.events.PlayerDeathListener;
import dev.a8kj.events.PlayerJoinListener;
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

    void registerEvents() {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        Listener[] listeners = { new PlayerDeathListener(connectionManager),
                new PlayerJoinListener(connectionManager) };
        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, instance);
        }
    }


    void registerCommand(){
        getCommand("setlb").setExecutor(new LeaderboardCommand());
    }

    @Override
    public void onEnable() {
        init();
        registerEvents();
        registerCommand();
    }

    @Override
    public void onDisable() {
        setInstance(null);
    }

}
