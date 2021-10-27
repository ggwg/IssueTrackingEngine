package main.java;

import main.java.UniqueIDGenerator.IssueIDGenerator;
import main.java.Utils.Comment;
import main.java.Utils.State;
import main.java.Utils.StateChange;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Issue {

    private final String title;
    private final int id;
    private final String creationDate;
    private State currentState;
    private final List<Comment> comments;
    private final List<Integer> userIDs;
    private final List<StateChange> stateChanges;

    public Issue(String title) {
        this.title = title;
        this.id = IssueIDGenerator.getInstance().generateUniqueID();
        this.creationDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        this.currentState = State.TODO;
        this.comments = new ArrayList<>();
        this.userIDs = new ArrayList<>();
        this.stateChanges = new ArrayList<>();
    }

    /* Testing function required to check date comparison functionality */
    public Issue(String title, String creationDate) {
        this.title = title;
        this.id = IssueIDGenerator.getInstance().generateUniqueID();
        this.creationDate = creationDate;
        this.currentState = State.TODO;
        this.comments = new ArrayList<>();
        this.userIDs = new ArrayList<>();
        this.stateChanges = new ArrayList<>();
    }

    public void updateState(State state, String comment) {
        currentState = state;
        stateChanges.add(new StateChange(state, comment));
    }

    public void addUser(int userID) {
        userIDs.add(userID);
        /* If someone starts working on an issue then state should change to IN_PROGRESS */
        if (userIDs.size() == 1 && currentState == State.TODO) {
            currentState = State.IN_PROGRESS;
        }
    }

    public void addComment(String message) {
        comments.add(new Comment(message));
    }

    public int getID() {
        return id;
    }

    public List<Integer> getUserIDs() {
        return userIDs;
    }

    public List<StateChange> getStateChanges() {
        return stateChanges;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public State getCurrentState() {
        return currentState;
    }

    public String getTitle() {
        return title;
    }
}
