package com.example.marinero_kj.persistance.networking;

import com.example.marinero_kj.pojo.Sunrise;

public class SunriseResponse {
    //"sunrise":"5:40:53 AM","sunset":"6:53:36 PM"
    private SunriseR results;

    public Sunrise getSunrise(){
        if(results!=null)
            return results.getSunrise();
        else return new Sunrise("error","error");
    }

    private class SunriseR{
        private String sunrise;
        private String sunset;

        public Sunrise getSunrise(){
            return new Sunrise(sunrise, sunset);
        }
    }
}
