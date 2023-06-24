/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package show.tracker.api.model;

import java.util.ArrayList;
import java.util.List;

import show.tracker.api.model.media.Media;

/**
 *
 * @author Yanna Torres
 */
public class WatchedList {
    private int id;
    private String user_email;
    private List<Media> medias;

    public WatchedList(int id, String user_email) {
        this.id = id;
        this.user_email = user_email;
        this.medias = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return this.user_email;
    }

    public void setUserEmail(String user_email) {
        this.user_email = user_email;
    }

    public List<Media> getMedias() {
        return medias;
    }
    
    public void addMedia(Media media) {
        this.medias.add(media);
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }
}
