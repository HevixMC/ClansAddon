package dev.a8kj.database;

public interface IConnection {

    final String NOT_CONNECTED_MESSAGE = "Not connected to the database.";

    void connect();

    void disconnect();

    boolean isConnected();

}
