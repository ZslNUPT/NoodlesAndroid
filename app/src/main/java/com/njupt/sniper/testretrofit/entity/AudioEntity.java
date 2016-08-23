package com.njupt.sniper.testretrofit.entity;

import java.io.Serializable;

/**
 * author：Zsl
 * date：2016/7/5
 */
public class AudioEntity implements Serializable {
    private int id;
    private String name;
    private String shareUrl;
    private String audioUrl;
    private String title;
    private String content;
    private int audioTime;
    private String img;
    private String smallImg;
    private String description;
    private int viewTimes;

    private AudioEntity lastAudio;
    private AudioEntity nextAudio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAudioTime() {
        return audioTime;
    }

    public void setAudioTime(int audioTime) {
        this.audioTime = audioTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(int viewTimes) {
        this.viewTimes = viewTimes;
    }

    public AudioEntity getLastAudio() {
        return lastAudio;
    }

    public void setLastAudio(AudioEntity lastAudio) {
        this.lastAudio = lastAudio;
    }

    public AudioEntity getNextAudio() {
        return nextAudio;
    }

    public void setNextAudio(AudioEntity nextAudio) {
        this.nextAudio = nextAudio;
    }


}
