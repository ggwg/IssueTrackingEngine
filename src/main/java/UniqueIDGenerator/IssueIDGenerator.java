package main.java.UniqueIDGenerator;

public class IssueIDGenerator {

    private static IssueIDGenerator instance;

    private int uniqueID;

    private IssueIDGenerator() {
        uniqueID = 0;
    }

    /* Singleton structure used to ensure there can only be one UniqueIDGenerator
       Synchronized to prevent threads creating multiple instances race condition
     */
    public static synchronized IssueIDGenerator getInstance() {
        if (instance == null) {
            instance = new IssueIDGenerator();
        }
        return instance;
    }

    public synchronized int generateUniqueID() {
        uniqueID++;
        return uniqueID;
    }
}
