package main.java.UniqueIDGenerator;

public class UserIDGenerator {

    private static UserIDGenerator instance;

    private int uniqueID;

    private UserIDGenerator() {
        uniqueID = 0;
    }

    /* Singleton structure used to ensure there can only be one UniqueIDGenerator
       Synchronized to prevent threads creating multiple instances race condition
     */
    public static synchronized UserIDGenerator getInstance() {
        if (instance == null) {
            instance = new UserIDGenerator();
        }
        return instance;
    }

    public synchronized int generateUniqueID() {
        uniqueID++;
        return uniqueID;
    }
}
