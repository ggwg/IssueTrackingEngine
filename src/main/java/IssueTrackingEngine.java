package main.java;

import main.java.Utils.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IssueTrackingEngine {

    private final Map<Integer, Issue> issues;
    private final Map<Integer, User> users;

    public IssueTrackingEngine() {
        this.issues = new HashMap<>();
        this.users = new HashMap<>();
    }

    /* Creates a new issue with name title. Adds new Issue together with its integer ID
       to list of issues. Returns ID of newly created issue.
     */
    public int addIssue(String title) {
        Issue newIssue = new Issue(title);
        issues.put(newIssue.getID(), newIssue);
        return newIssue.getID();
    }

    /* Creates a new issue with name title and date. Adds new Issue together with its
       integer ID to list of issues. Returns ID of newly created issue.
     */
    public int addIssue(String title, String startDate) {
        Issue newIssue = new Issue(title, startDate);
        issues.put(newIssue.getID(), newIssue);
        return newIssue.getID();
    }

    /* Removes an issue completely based on an issue's ID
     *  If issueID not found, does nothing
     * */
    public void removeIssue(int issueID) {
        issues.remove(issueID);
    }

    /* Sets the state of an issue, with optional comment */
    public void setIssueState(int issueID, State state, String comment) {
        issues.get(issueID).updateState(state, comment);
    }

    /* Adds a user to an issue.
       Many users can be added to a single issue.
     */
    public void assignUser(int userID, int issueID) {
        issues.get(issueID).addUser(userID);
    }

    /* Adds comment to an issue */
    public void addIssueComment(int issueID, String comment) {
        issues.get(issueID).addComment(comment);
    }

    /* Adds new user and returns ID of the new user */
    public int addUser(String name) {
        User newUser = new User(name);
        users.put(newUser.getID(), newUser);
        return newUser.getID();
    }

    /* Removes user with ID userID
     *  If issueID not found, does nothing
     * */
    public void removeUser(int userID) {
        users.remove(userID);
    }

    /* Get a list of issues, optionally filtered by a set of parameters
       collection of issues = getIssues(state = null, userID = null, startDate = null, endDate = null)
       If all filter parameters are null, all issues will be returned.
     */
    public List<Issue> getIssues(State state, Integer userID, String startDate, String endDate) {
        return issues.values().stream()
                .filter(state == null ? x -> true : x -> x.getCurrentState() == state)
                .filter(userID == null ? x -> true : x -> x.getUserIDs().contains(userID))
                .filter(startDate == null ? x -> true : x -> x.getCreationDate().compareTo(startDate) >= 0)
                .filter(endDate == null ? x -> true : x -> x.getCreationDate().compareTo(endDate) <= 0)
                .collect(Collectors.toList());
    }

    /* Returns a collection of all added users in the system. */
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    /* Returns a specific issue given by issueID */
    public Issue getIssue(int issueID) {
        return issues.get(issueID);
    }

    /* Returns a specific issue given by issueID */
    public User getUser(int userID) {
        return users.get(userID);
    }

    public int getNumberOfIssues() {
        return issues.size();
    }

    public int getNumberOfUsers() {
        return users.size();
    }

    public boolean containsIssue(int issueID) {
        return issues.containsKey(issueID);
    }

    public boolean containsUser(int issueID) {
        return users.containsKey(issueID);
    }
}
