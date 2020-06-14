package com.example.marinero_kj.pojo;

public class Sunrise {

    private String sunrise;
    private String sunset;
    private String imageUrl;

    public Sunrise(String sunrise, String sunset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        imageUrl= " https://source.unsplash.com/random";
    }

    public String getImageUrl(){return imageUrl;}

    public void setImageUrl(String imageUrl){this.imageUrl=imageUrl;}

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
