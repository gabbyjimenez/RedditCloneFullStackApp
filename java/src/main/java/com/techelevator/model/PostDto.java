package com.techelevator.model;

import java.time.LocalDateTime;

public class PostDto {

    private int postId;
    private int creatorId;
    private String creatorUsername;
    private int denId;
    private String postTitle;
    private String postDesc;
    private String denName;
    private int upvotes;
    private int downvotes;
    private boolean pinned;
    private LocalDateTime timeCreated;

    private String pfpUrl;

    public PostDto (){

    }

    public PostDto(int postId, int creatorId, String creatorUsername, int denId, String postTitle, String postDesc, String denName, int upvotes, int downvotes, boolean pinned, LocalDateTime timeCreated, String pfpUrl) {
        this.postId = postId;
        this.creatorId = creatorId;
        this.creatorUsername = creatorUsername;
        this.denId = denId;
        this.postTitle = postTitle;
        this.postDesc = postDesc;
        this.denName = denName;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.pinned = pinned;
        this.timeCreated = timeCreated;
        this.pfpUrl = pfpUrl;
    }

    public String getPfpUrl() {
        return pfpUrl;
    }

    public void setPfpUrl(String pfpUrl) {
        this.pfpUrl = pfpUrl;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getPostId() {
        return postId;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
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

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }
}
