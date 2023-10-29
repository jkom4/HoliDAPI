package org.helmo.HolyD.models.SaveOldWithSize;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;


public class Message {

    @Size(min = 1, max = 800, message = "Wrong content message size min=1 max=800")
    private String content;

    @NotNull
    private OffsetDateTime sendingDate;

    @NotNull
    private User user;

    private Vacance vacance;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OffsetDateTime getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(OffsetDateTime sendingDate) {
        this.sendingDate = sendingDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", sendingDate=" + sendingDate +
                ", user=" + user +
                '}';
    }
}
