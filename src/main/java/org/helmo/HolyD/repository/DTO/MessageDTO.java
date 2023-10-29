package org.helmo.HolyD.repository.DTO;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Objects;


@Entity
@Table(name = "MESSAGE")
public class MessageDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Message")
    @SequenceGenerator(name = "id_Message", sequenceName = "ID_MESSAGE", allocationSize = 1)
    private Long id;

    @Size(min = 1, max = 800, message = "Wrong content message size min=1 max=800")
    @Column(length = 800, nullable = false)
    private String content;

    @Column(nullable = false)
    private OffsetDateTime sendingDate;

    @OneToOne(optional = false)
    private UserDTO sender;

    @ManyToOne(optional = false)
    private VacanceDTO vacance;

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

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDTO message = (MessageDTO) o;
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
                ", sender=" + sender +
                '}';
    }
}
