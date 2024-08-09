package com.techelevator.model;

public class VotingDto {

    private int responseUserId;

    private boolean voteStatus;

    private int userId;
    private int objectId;

    public VotingDto (){

    };

    public VotingDto(int responseUserId, boolean voteStatus, int userId, int objectId) {
        this.responseUserId = responseUserId;
        this.voteStatus = voteStatus;
        this.userId = userId;
        this.objectId = objectId;
    }

    public int getResponseUserId() {
        return responseUserId;
    }

    public void setResponseUserId(int reponseUserId) {
        this.responseUserId = reponseUserId;
    }

    public boolean isVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(boolean voteStatus) {
        this.voteStatus = voteStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
}
