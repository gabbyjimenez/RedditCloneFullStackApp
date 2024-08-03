package com.techelevator.model;

public class DenDto {

    private int denId;

    private String denName;

    private int denCreatorId;

    private String denCreatorUserName;

    public DenDto(int denId, String denName, int denCreatorId, String denCreatorUserName) {
        this.denId = denId;
        this.denName = denName;
        this.denCreatorId = denCreatorId;
        this.denCreatorUserName = denCreatorUserName;
    }

    public DenDto(){

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
