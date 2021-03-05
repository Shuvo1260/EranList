package org.binaryitplanet.eranlist.Model;

import java.io.Serializable;

public class ItemData implements Serializable {
    private int num1;
    private int num2;
    private String string;
    private int progress;
    private String date;
    private String location;

    public ItemData(){}
    public ItemData(int num1, int num2, String string, int progress, String date, String location) {
        this.num1 = num1;
        this.num2 = num2;
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

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }
}
