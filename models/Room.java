package models;

public class Room {
    public String id;
    public String[] users;
    public int numPlayers;

    public Room(String id, String user) {
        this.id = id.strip();
        this.users = user.strip().split(" ");
        this.numPlayers = users.length;
    }
}
