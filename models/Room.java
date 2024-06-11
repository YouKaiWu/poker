package models;

public class Room {
    public String id;
    public int numPlayers;

    public Room(String id, int numUsers) {
        this.id = id.strip();
        this.numPlayers = numUsers;
    }
}
