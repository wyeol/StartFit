package com.finaltest.startfit.login;

public class UserAccount {
    private String emailId;
    private String password;
    private String idToken; //고유 Token

    public UserAccount(){ }

    public String getEmailId() {
        return emailId;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
