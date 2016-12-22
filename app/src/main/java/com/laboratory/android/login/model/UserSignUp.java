package com.laboratory.android.login.model;

/**
 * Created by Susiane on 21/12/2016.
 */

public class UserSignUp {
    private String name;
    private String lastName;
    private String eMail;

    public UserSignUp(String name, String lastName, String eMail) {
        this.name = name;
        this.lastName = lastName;
        this.eMail = eMail;
    }
    public UserSignUp() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
