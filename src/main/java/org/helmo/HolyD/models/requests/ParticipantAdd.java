package org.helmo.HolyD.models.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class ParticipantAdd {

    @Email
    @Size(min = 2, max = 100, message = "Wrong email size min=2 max=100")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ParticipantAdd{" +
                "email='" + email + '\'' +
                '}';
    }
}
