package com.example.VirtualFitON.DTO;

public class SignUpDTO {

    private String firstname;


    private String lastname;


    private String username;


    private String password;

    public SignUpDTO(String firstname, String password, String lastname, String username) {
        this.firstname = firstname;
        this.password = password;
        this.lastname = lastname;
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
