package main.java;

import main.java.UniqueIDGenerator.UserIDGenerator;

public class User {

    private final String name;
    private final int id;

    public User(String name) {
        this.name = name;
        this.id = UserIDGenerator.getInstance().generateUniqueID();
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }
}
