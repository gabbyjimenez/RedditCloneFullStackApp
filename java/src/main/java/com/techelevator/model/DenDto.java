package com.techelevator.model;

import java.util.List;

public class DenDto {

    private int denId;

    private String denName;

    private int denCreatorId;

    private String denCreatorUserName;

    private List<String> categoryNames;

    private String denDesc;


    public DenDto(int denId, String denName, int denCreatorId, String denCreatorUserName, List<String> categoryNames, String denDesc) {
        this.denId = denId;
        this.denName = denName;
        this.denCreatorId = denCreatorId;
        this.denCreatorUserName = denCreatorUserName;
        this.categoryNames = categoryNames;
        this.denDesc = denDesc;
    }

    public DenDto(){

    }

    public String getDenDesc() {
        return denDesc;
    }

    public void setDenDesc(String denDesc) {
        this.denDesc = denDesc;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
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
