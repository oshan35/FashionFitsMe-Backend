package com.example.VirtualFitON.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class CustomerRegisterDTO {


    private String firstName;


    private String lastName;


    private String country;


    private String username;


    private String password;

    public CustomerRegisterDTO( String firstName, String lastName, String country, String username, String password) {
       // this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.username = username;
        this.password = password;
    }

    public CustomerRegisterDTO() {

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
