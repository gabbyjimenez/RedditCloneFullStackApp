package com.techelevator.model;

public class Den {

    private int denId;

    private String denName;

    private int denCreatorId;

    private String denCreatorUserName;

    public Den(int denId, String denName, int denCreator, String denCreatorUserName) {
        this.denId = denId;
        this.denName = denName;
        this.denCreatorId = denCreatorId;
        this.denCreatorUserName = denCreatorUserName;
    }

    public Den(){

    }
    public int getDenId() {
        return denId;
    }

    public void setDenId(int denId) {
        this.denId = denId;
    }

    public String getDenName() {
        return denName;
    }

    public void setDenName(String denName) {
        this.denName = denName;
    }

    public int getDenCreatorId() {
        return denCreatorId;
    }

    public void setDenCreatorId(int denCreatorId) {
        this.denCreatorId = denCreatorId;
    }

    public String getDenCreatorUserName() {
        return denCreatorUserName;
    }

    public void setDenCreatorUserName(String denCreatorUserName) {
        this.denCreatorUserName = denCreatorUserName;
    }
}
