package Hotel;


import java.util.Date;

public class Reservation {
    private String userInfo;
    private Date from;
    private Date to;

    public Reservation(String userInfo, Date from, Date to) {
        this.userInfo = userInfo;
        this.from = from;
        this.to = to;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

}