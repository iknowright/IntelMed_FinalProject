package com.example.finalproject;

public class videoEntry {
    private String videoID;
    private String videoTitle;

    public void init(String id, String title) {
        this.videoID = id;
        this.videoTitle = title;
    }

    public String GetVideoID() {
        return this.videoID;
    }

    public String GetVideoTitle() {
        return this.videoTitle;
    }
}
