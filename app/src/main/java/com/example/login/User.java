package com.example.login;

public class User {
    public String Name,  Email;
    public Long CubCash;
    public User(){

    }

    public User(String Name, Long CubCash,String Email){
        this.Name = Name;
        this.CubCash = CubCash;
        this.Email = Email;
    }
}
