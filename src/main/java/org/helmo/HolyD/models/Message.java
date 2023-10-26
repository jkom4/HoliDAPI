package org.helmo.HolyD.models;


import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;


@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Message")
    @SequenceGenerator(name = "id_Message", sequenceName = "ID_MESSAGE", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private OffsetDateTime sendingDate;

    @OneToOne(optional = false)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id.equals(message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sendingDate=" + sendingDate +
                ", user=" + user +
                '}';
    }
}
