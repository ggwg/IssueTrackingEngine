package main.java;

import main.java.Utils.State;

import java.util.List;

public class Main {

    /* Demo of test scenario 1 given in spec */
    public static void main(String[] args) {
        IssueTrackingEngine issueTrackingEngine = new IssueTrackingEngine();
        int userID = issueTrackingEngine.addUser("Steve");
        int issueID = issueTrackingEngine.addIssue("The app crashes on login.");
        issueTrackingEngine.assignUser(userID, issueID);
        issueTrackingEngine.setIssueState(issueID, State.IN_PROGRESS, "I'm on it!");

        /* Display list of users */
        List<User> users = issueTrackingEngine.getUsers();
        for (User user : users) {
            System.out.println("User ID: " + user.getID() + ", Name: " + user.getName());
        }
        /* Display list of issues */
        List<Issue> issues = issueTrackingEngine.getIssues(null, null, null, null);
        for (Issue issue : issues) {
            System.out.print("Issue ID: " + issue.getID() + ", Title: " + issue.getTitle() + ", State: " +
                    issue.getCurrentState() + ", Users: ");
            for (int i : issue.getUserIDs()) {
                System.out.print(issueTrackingEngine.getUser(i).getName() + " ");
            }
            System.out.println();
        }
    }

}
