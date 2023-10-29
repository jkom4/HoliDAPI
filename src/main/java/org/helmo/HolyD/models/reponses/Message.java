package org.helmo.HolyD.models.reponses;


import java.time.OffsetDateTime;


public class Message {

    private Long id;
    private String content;
    private OffsetDateTime sendingDate;
    private Participant sender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Participant getSender() {
        return sender;
    }

    public void setSender(Participant sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sendingDate=" + sendingDate +
                ", sender=" + sender +
                '}';
    }
}
