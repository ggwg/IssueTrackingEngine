package main.java.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {

    private final String message;
    private final String timeStamp;

    public Comment(String message) {
        this.message = message;
        this.timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

}
