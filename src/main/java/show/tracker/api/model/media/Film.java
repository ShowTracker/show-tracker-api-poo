/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package show.tracker.api.model.media;


public class Film extends Media {
    private String duration;

    public Film(String duration, int id, String year, String title, boolean isAdult) {
        super(id, year, title, isAdult);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    
}
