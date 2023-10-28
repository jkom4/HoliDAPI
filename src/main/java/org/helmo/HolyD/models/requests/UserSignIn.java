package org.helmo.HolyD.models.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserSignIn {

    @Email
    @Size(min = 2, max = 100, message = "Wrong email size min=2 max=100")
    private String email;
    @Size(min = 2, max = 100, message = "Wrong password size min=2 max=100")
    private String passwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "UserSignIn{" +
                "email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
