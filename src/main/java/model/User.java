package model;

import services.AttributeEncryptor;

import javax.persistence.*;

// class/entity User according given UML

@Entity
public class User {
    @Id
    private String login;
    @Convert(converter = AttributeEncryptor.class) //calls AttributeEncryptor to encrypt or decrypt
    private String passwordhash;
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL) // ALL to allow delete and update from user (GUI module)
    private Person person;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public User(String login, String passwordhash) {
        this.login = login;
        this.passwordhash = passwordhash;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", passwordhash='" + passwordhash + '\'' +
                ", active=" + active +
                ", person=" + person +
                '}';
    }


}


