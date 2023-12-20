package org.helmo.HolyD.models.requests;

import javax.validation.constraints.Size;

public class MessageAdd {
    @Size(min = 1, max = 800, message = "Wrong content message size min=1 max=800")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageAdd{" +
                "content='" + content + '\'' +
                '}';
    }
}
