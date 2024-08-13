package com.techelevator.model;

public class FavoriteDto {
    private int favoriteId;

    private boolean toggleStatus;

    private int denId;

    private int userId;


    public FavoriteDto(){};

    public FavoriteDto(int favoriteId, boolean toggleStatus, int denId, int userId) {
        this.favoriteId = favoriteId;
        this.toggleStatus = toggleStatus;
        this.denId = denId;
        this.userId = userId;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public boolean isToggleStatus() {
        return toggleStatus;
    }

    public void setToggleStatus(boolean toggleStatus) {
        this.toggleStatus = toggleStatus;
    }

    public int getDenId() {
        return denId;
    }

    public void setDenId(int denId) {
        this.denId = denId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
