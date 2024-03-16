package hkmu.comps321f.weather_app.Domains;

import java.util.ArrayList;

public class TomorrowDomain {
    private String description, temp_max, temp_min, rain, wind_speed, pic_path;
    public static ArrayList<TomorrowDomain> tomorrowArrayList = new ArrayList<>();

    public TomorrowDomain(String description, String temp_max, String temp_min, String rain, String wind_speed, String pic_path) {
        this.description = description;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.rain = rain;
        this.wind_speed = wind_speed;
        this.pic_path = pic_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
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

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public static void addTomorrow(TomorrowDomain t){
        tomorrowArrayList.add(t);
    }
}
