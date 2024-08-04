package com.techelevator.model;

public class PostDto {

    private int postId;
    private int creatorId;
    private String creatorUsername;
    private int denId;
    private String postTitle;
    private String postDesc;
    private String denName;

    public PostDto (){

    }

    public PostDto(int postId, int creatorId, String creatorUsername, int denId, String postTitle, String postDesc, String denName) {
        this.postId = postId;
        this.creatorId = creatorId;
        this.creatorUsername = creatorUsername;
        this.denId = denId;
        this.postTitle = postTitle;
        this.postDesc = postDesc;
        this.denName = denName;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getDenId() {
        return denId;
    }

    public void setDenId(int denId) {
        this.denId = denId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getDenName() {
        return denName;
    }

    public void setDenName(String denName) {
        this.denName = denName;
    }
}
