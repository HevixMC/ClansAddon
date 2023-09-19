package dev.a8kj.database.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.a8kj.database.IConnection;
import dev.a8kj.logger.LoggerManager;
import lombok.Getter;
import lombok.Setter;

public class ConnectionManager extends LoggerManager implements IConnection {

    @Getter
    @Setter
    private Connection connection;
    @Getter
    @Setter
    private String host;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String database;
    @Getter
    @Setter
    private String password;

    public ConnectionManager(String host, String username, String database, String password) {
        setHost(host);
        setUsername(username);
        setDatabase(database);
        setUsername(username);
        setPassword(password);
    }

    public ConnectionManager() {
    }

    @Override
    public void connect() {
        String jdbcUrl = "jdbc:mysql://" + host + ":" + "3306" + "/" + database;

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            logger.info("Connected to MySQL database successfully !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
                logger.info("Database connection has been closed !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }

    public void createTable(String tableName, String tableStructure) {
        if (!isConnected()) {
            logger.warning(NOT_CONNECTED_MESSAGE);
            return;
        }

        String createTableQuery = "create table if not exists " + tableName + " (" + tableStructure + ");";

        try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String sql) {
        if (!isConnected()) {
            logger.warning(NOT_CONNECTED_MESSAGE);
            return null;
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int update(String sql) {
        if (!isConnected()) {
            logger.warning(NOT_CONNECTED_MESSAGE);
            return -1;
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
