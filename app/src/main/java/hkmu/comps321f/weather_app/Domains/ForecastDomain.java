package hkmu.comps321f.weather_app.Domains;

import java.util.ArrayList;

public class ForecastDomain {
    //Next 6 days in forecast activity
    private String day, pic_path, status, highTemp, lowTemp, rain, wind_speed;
    private boolean visibility;

    public static ArrayList<ForecastDomain> forecastArrayList = new ArrayList<>();

    public ForecastDomain(String day, String pic_path, String status, String highTemp, String lowTemp, String rain, String wind_speed) {
        this.day = day;
        this.pic_path = pic_path;
        this.status = status;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.rain = rain;
        this.wind_speed = wind_speed;
        this.visibility = false;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPicPath() {
        return pic_path;
    }

    public void setPicPath(String picPath) {
        this.pic_path = picPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public static void addForecastDomain(ForecastDomain f){
        forecastArrayList.add(f);
    }
}


