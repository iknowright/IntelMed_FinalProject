package com.example.finalproject;

public class videoEntry {
    private String videoID;
    private String videoTitle;
    private String imageUrl;

    public void init(String id, String title, String imageUrl) {
        this.videoID = id;
        this.videoTitle = title;
        this.imageUrl = imageUrl;
    }

    public String GetVideoID() {
        return this.videoID;
    }

    public String GetVideoTitle() {
        return this.videoTitle;
    }
    public String GetImageUrl() {
        return this.imageUrl;
    }
}
