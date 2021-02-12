package org.binaryitplanet.eranlist.Model;

import java.io.Serializable;

public class ItemData implements Serializable {
    private String string;
    private int progress;
    private String date;
    private String location;

    public ItemData(){}
    public ItemData(String string, int progress, String date, String location) {
        this.string = string;
        this.progress = progress;
        this.date = date;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
