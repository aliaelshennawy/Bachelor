package models;

/**
 * Created by root on 22/05/16.
 */
public class Notification {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String message;

    public Integer getNotifyIcon() {
        return notifyIcon;
    }

    public void setNotifyIcon(Integer notifyIcon) {
        this.notifyIcon = notifyIcon;
    }

    Integer notifyIcon;
}
