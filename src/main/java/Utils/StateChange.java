package main.java.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StateChange {

    private final State state;
    private final String comment;
    private final String timeStamp;

    public StateChange(State state, String comment) {
        this.state = state;
        this.comment = comment;
        this.timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public State getState() {
        return state;
    }

    public String getComment() {
        return comment;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
