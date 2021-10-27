package test.java;

import main.java.Issue;
import main.java.IssueTrackingEngine;
import main.java.Utils.State;
import main.java.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class IssueTrackingEngineTest {

    private final IssueTrackingEngine testIssueTrackingEngine = new IssueTrackingEngine();

    @Test
    public void addingIssueUpdatesListOfIssues() {
        int issueID = testIssueTrackingEngine.addIssue("Test Issue 1");
        assertThat(testIssueTrackingEngine.getNumberOfIssues(), is(1));
        assertTrue(testIssueTrackingEngine.containsIssue(issueID));
    }

    @Test
    public void removingIssueUpdatesListOfIssues() {
        testIssueTrackingEngine.addIssue("Test Issue 1");
        int issueID = testIssueTrackingEngine.addIssue("Test Issue 2");
        testIssueTrackingEngine.addIssue("Test Issue 3");

        testIssueTrackingEngine.removeIssue(issueID);
        // New length of list should be 2 after removing an issue
        assertThat(testIssueTrackingEngine.getNumberOfIssues(), is(2));
        // issueID should be removed from to list issues
        assertFalse(testIssueTrackingEngine.containsIssue(issueID));
    }

    @Test
    public void issueStateChangesCorrectlyRecorded() {
        int issueID = testIssueTrackingEngine.addIssue("Test Issue");
        testIssueTrackingEngine.setIssueState(issueID, State.IN_PROGRESS, null);
        testIssueTrackingEngine.setIssueState(issueID, State.DONE, "Completed Issue");
        assertThat(testIssueTrackingEngine.getIssue(issueID).getStateChanges().size(), is(2));
    }

    @Test
    public void issueStateChangeCommentsCorrectlyRecorded() {
        int issueID = testIssueTrackingEngine.addIssue("Test Issue");
        testIssueTrackingEngine.setIssueState(issueID, State.DONE, "Finished Issue 1");
        assertThat(testIssueTrackingEngine.getIssue(issueID).getStateChanges().get(0).getComment(),
                is("Finished Issue 1"));
    }

    @Test
    public void usersHaveBeenCorrectlyAssignedToIssue() {
        int issueID = testIssueTrackingEngine.addIssue("Test Issue");
        int userID = testIssueTrackingEngine.addUser("Elon Musk");
        testIssueTrackingEngine.assignUser(userID, issueID);
        assertThat(testIssueTrackingEngine.getIssue(issueID).getUserIDs().size(), is(1));
        assertTrue(testIssueTrackingEngine.getIssue(issueID).getUserIDs().contains(userID));
    }

    @Test
    public void usersAssignedToIssueHaveBeenRecordedCorrectly() {
        int issueID = testIssueTrackingEngine.addIssue("Test Issue");
        int userA = testIssueTrackingEngine.addUser("Mark Zuckerberg");
        int userB = testIssueTrackingEngine.addUser("Jeff Bezos");
        testIssueTrackingEngine.assignUser(userA, issueID);
        testIssueTrackingEngine.assignUser(userB, issueID);
        assertThat(testIssueTrackingEngine.getUser(
                testIssueTrackingEngine.getIssue(issueID).getUserIDs().get(0)).getName(),
                is("Mark Zuckerberg"));
        assertThat(testIssueTrackingEngine.getUser(
                testIssueTrackingEngine.getIssue(issueID).getUserIDs().get(1)).getName(),
                is("Jeff Bezos"));
    }

    @Test
    public void addingUserUpdatesListOfUsers() {
        int userID = testIssueTrackingEngine.addUser("Test Issue 1");
        assertThat(testIssueTrackingEngine.getNumberOfUsers(), is(1));
        assertTrue(testIssueTrackingEngine.containsUser(userID));
    }

    @Test
    public void removingUserUpdatesListOfUsers() {
        testIssueTrackingEngine.addUser("Steve Jobs");
        int userID = testIssueTrackingEngine.addUser("Lisa Su");
        testIssueTrackingEngine.addUser("William Gates");

        testIssueTrackingEngine.removeUser(userID);
        // New length of list should be 2 after removing a user
        assertThat(testIssueTrackingEngine.getNumberOfUsers(), is(2));
        // userID should be removed from to list issues
        assertFalse(testIssueTrackingEngine.containsUser(userID));
    }

    @Test
    public void getUsersReturnsCorrectListOfUsers() {
        int userA_ID = testIssueTrackingEngine.addUser("Abraham Lincoln");
        int userB_ID = testIssueTrackingEngine.addUser("Winston Churchill");
        int userC_ID = testIssueTrackingEngine.addUser("Genghis Khan");

        List<User> users = testIssueTrackingEngine.getUsers();
        assertThat(users.size(), is(3));
        assertTrue(users.contains(testIssueTrackingEngine.getUser(userA_ID)));
        assertTrue(users.contains(testIssueTrackingEngine.getUser(userB_ID)));
        assertTrue(users.contains(testIssueTrackingEngine.getUser(userC_ID)));
    }

    @Test
    public void getIssuesFiltersBasedOnState() {
        int issueA_ID = testIssueTrackingEngine.addIssue("Issue A");
        testIssueTrackingEngine.setIssueState(issueA_ID, State.DONE, null);
        int issueB_ID = testIssueTrackingEngine.addIssue("Issue B");
        testIssueTrackingEngine.setIssueState(issueB_ID, State.IN_PROGRESS, null);

        List<Issue> doneIssues = testIssueTrackingEngine.getIssues(State.DONE, null, null, null);
        assertThat(doneIssues.size(), is(1));
        assertTrue(doneIssues.contains(testIssueTrackingEngine.getIssue(issueA_ID)));
    }


    @Test
    public void getIssuesFiltersBasedOnUserID() {
        int userA_ID = testIssueTrackingEngine.addUser("User A");
        int userB_ID = testIssueTrackingEngine.addUser("User B");

        int issueA_ID = testIssueTrackingEngine.addIssue("Issue A");
        testIssueTrackingEngine.assignUser(userA_ID, issueA_ID);
        int issueB_ID = testIssueTrackingEngine.addIssue("Issue B");
        testIssueTrackingEngine.assignUser(userB_ID, issueB_ID);

        List<Issue> userA_Issues = testIssueTrackingEngine.getIssues(null, userA_ID, null, null);
        assertThat(userA_Issues.size(), is(1));
        assertTrue(userA_Issues.contains(testIssueTrackingEngine.getIssue(issueA_ID)));
    }

    @Test
    public void getIssuesFiltersBasedOnStartDate() {
        int issueA_ID = testIssueTrackingEngine.addIssue("Issue A", "20201110");
        int issueB_ID = testIssueTrackingEngine.addIssue("Issue B", "20201114");
        int issueC_ID = testIssueTrackingEngine.addIssue("Issue C", "20230201");

        List<Issue> issuesAfter20201114 = testIssueTrackingEngine.getIssues(null, null, "20201114", null);
        assertThat(issuesAfter20201114.size(), is(2));
        assertTrue(issuesAfter20201114.contains(testIssueTrackingEngine.getIssue(issueB_ID)));
        assertTrue(issuesAfter20201114.contains(testIssueTrackingEngine.getIssue(issueC_ID)));
    }

    @Test
    public void getIssuesFiltersBasedOnEndDate() {
        int issueA_ID = testIssueTrackingEngine.addIssue("Issue A", "20201110");
        int issueB_ID = testIssueTrackingEngine.addIssue("Issue B", "20201114");
        int issueC_ID = testIssueTrackingEngine.addIssue("Issue C", "20230201");

        List<Issue> issuesBefore20201114 = testIssueTrackingEngine.getIssues(null, null, null, "20201114");
        assertThat(issuesBefore20201114.size(), is(2));
        assertTrue(issuesBefore20201114.contains(testIssueTrackingEngine.getIssue(issueA_ID)));
        assertTrue(issuesBefore20201114.contains(testIssueTrackingEngine.getIssue(issueB_ID)));
    }

    @Test
    public void getIssuesReturnsCorrectListOfIssues() {
        int userA_ID = testIssueTrackingEngine.addUser("User A");

        int issueA_ID = testIssueTrackingEngine.addIssue("Issue A", "20201114");
        testIssueTrackingEngine.assignUser(userA_ID, issueA_ID);
        testIssueTrackingEngine.setIssueState(issueA_ID, State.IN_PROGRESS, null);

        int issueB_ID = testIssueTrackingEngine.addIssue("Issue B", "20201216");
        testIssueTrackingEngine.assignUser(userA_ID, issueB_ID);
        testIssueTrackingEngine.setIssueState(issueB_ID, State.DONE, null);

        List<Issue> specificIssues = testIssueTrackingEngine.getIssues(State.IN_PROGRESS, userA_ID, "20201010", "20201201");
        assertThat(specificIssues.size(), is(1));
        assertTrue(specificIssues.contains(testIssueTrackingEngine.getIssue(issueA_ID)));
    }
}
