package hkmu.comps321f.weather_app.Domains;

public class Hourly {
private String Hour;
private int temp;
private String picpath;

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getPicPath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public Hourly(String hour, int temp, String picpath) {
        Hour = hour;
        this.temp = temp;
        this.picpath = picpath;
    }
}
