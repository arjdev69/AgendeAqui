package com.brunoaraujo.agendeaqui.model;

public class Provider {

    private Integer id;
    private String name;
    private Avatar avatar;

    public Provider() {
    }

    public Provider(Integer id, String name, Avatar avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
