package dev.a8kj.database;

public interface IConnection {

    final String NOT_CONNECTED_MESSAGE = "Not connected to the database.";

    void connect();

    void disconnect();

    boolean isConnected();

    default String me() {
        return "bro am so sad id have life bro help me i have a lot of problems with my parnets and brothers bro idk what am doing bro :( my death is comming soon";
    }

}
