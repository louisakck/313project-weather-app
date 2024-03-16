package hkmu.comps321f.weather_app.Domains;

import java.util.ArrayList;

public class Hourly {
    //hourly in main activity
    private String hour, temp, pic_path;
    public static ArrayList<Hourly> hourlyArrayList = new ArrayList<>();

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        hour = hour;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public Hourly(String hour, String temp, String pic_path) {
            this.hour = hour;
            this.temp = temp;
            this.pic_path = pic_path;

    }

    public static void addHourly(Hourly h){
        hourlyArrayList.add(h);
    }

}

