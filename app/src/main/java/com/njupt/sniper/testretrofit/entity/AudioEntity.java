package com.njupt.sniper.testretrofit.entity;

/**
 * author：Zsl
 * date：2016/7/5
 */
public class AudioEntity extends BaseIdName {
    private String share_url;
    private String audio_url;
    private String title;
    private String content;
    private int audio_time;
    private String img;
    private String small_img;
    private String description;
    private int view_times;

    private AudioEntity last_audio;
    private AudioEntity next_audio;

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
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

    public int getAudio_time() {
        return audio_time;
    }

    public void setAudio_time(int audio_time) {
        this.audio_time = audio_time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSmall_img() {
        return small_img;
    }

    public void setSmall_img(String small_img) {
        this.small_img = small_img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getView_times() {
        return view_times;
    }

    public void setView_times(int view_times) {
        this.view_times = view_times;
    }

    public AudioEntity getLast_audio() {
        return last_audio;
    }

    public void setLast_audio(AudioEntity last_audio) {
        this.last_audio = last_audio;
    }

    public AudioEntity getNext_audio() {
        return next_audio;
    }

    public void setNext_audio(AudioEntity next_audio) {
        this.next_audio = next_audio;
    }
}
