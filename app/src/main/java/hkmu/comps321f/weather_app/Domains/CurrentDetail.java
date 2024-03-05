package hkmu.comps321f.weather_app.Domains;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrentDetail {
    private String status, description, humidity, temp, temp_min, temp_max, windSpeed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;


    //String picPath, rain,

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    //public CurrentDetail(String status, String picPath, String highTemp, String lowTemp, String rain, String windSpeed, String humidity) {
    public CurrentDetail (JSONObject jsonObject){
        try{
            this.id = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            this.status = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
            this.description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
            double tempResult = jsonObject.getJSONObject("main").getDouble("temp")-273.15;
            double temp_minResult = jsonObject.getJSONObject("main").getDouble("temp_min")-273.15;
            double temp_maxResult = jsonObject.getJSONObject("main").getDouble("temp_max")-273.15;
            this.humidity = jsonObject.getJSONObject("main").getString("humidity");
            this.windSpeed = jsonObject.getJSONObject("wind").getString("speed");

            this.temp=Integer.toString((int)Math.rint(tempResult));
            this.temp_min=Integer.toString((int)Math.rint(temp_minResult));
            this.temp_max=Integer.toString((int)Math.rint(temp_maxResult));


        }catch (JSONException e){
            e.printStackTrace();
        }


    }

            //(String status, String highTemp, String lowTemp, String windSpeed, String humidity) {
        //this.status = status;
        //this.picPath = picPath;
        //this.highTemp = highTemp;
        //this.lowTemp = lowTemp;
        //this.rain = rain;
        //this.windSpeed = windSpeed;
        //this.humidity = humidity;
    }

