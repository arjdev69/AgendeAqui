package com.brunoaraujo.agendeaqui.model;

public class Avatar {

    private String url;
    private Integer id;
    private String path;

    public Avatar(){

    }

    public Avatar(String url, Integer id, String path) {
        this.url = url;
        this.id = id;
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
