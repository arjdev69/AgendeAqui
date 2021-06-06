package com.brunoaraujo.agendeaqui.model;

public class Appointments {

    private String past;
    private Boolean cancelable;
    private Integer id;
    private String date;
    private Integer provider_id;
    private Provider provider;

    public Appointments(){

    }

    public Appointments(String past, Boolean cancelable, Integer id, String date, Provider provider) {
        this.past = past;
        this.cancelable = cancelable;
        this.id = id;
        this.date = date;
        this.provider = provider;
    }

    public String getPast() {
        return past;
    }

    public void setPast(String past) {
        this.past = past;
    }

    public Boolean getCancelable() {
        return cancelable;
    }

    public void setCancelable(Boolean cancelable) {
        this.cancelable = cancelable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Integer getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(Integer provider_id) {
        this.provider_id = provider_id;
    }
}
