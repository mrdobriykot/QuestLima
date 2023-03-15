package com.javarush.khmelov.entity;

import java.util.Collection;

public class ProxyUser extends User{

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getLogin() {
        return super.getLogin();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public Role getRole() {
        return super.getRole();
    }

    @Override
    public Collection<Quest> getQuests() {
        return super.getQuests();
    }

    @Override
    public Collection<Game> getGames() {
        return super.getGames();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public void setLogin(String login) {
        super.setLogin(login);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setRole(Role role) {
        super.setRole(role);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getImage() {
        return super.getImage();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
