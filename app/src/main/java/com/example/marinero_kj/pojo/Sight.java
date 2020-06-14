package com.example.marinero_kj.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="sight_table")
public class Sight implements Serializable {

    @PrimaryKey
    @NonNull
    private String name;
    private String description, url;
    private double latitude, longitude;
    private String image;
    private boolean isTown, isHearted;

    public Sight(String name, String description, String url, boolean isTown){
        this.name=name;
        this.description=description;
        this.isTown=isTown;
        this.url=url;
        image="";
        isHearted=false;
        latitude=-1;
        longitude=-1;
    }

    @Ignore
    public Sight(String name, String description, String url, String image, boolean isTown, boolean isHearted){
        this.name=name;
        this.description=description;
        this.isTown=isTown;
        this.url=url;
        this.isHearted=isHearted;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url=url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isTown() {
        return isTown;
    }

    public void setTown(boolean town) {
        isTown = town;
    }

    public boolean isHearted() {
        return isHearted;
    }

    public void setHearted(boolean hearted) {
        isHearted = hearted;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
