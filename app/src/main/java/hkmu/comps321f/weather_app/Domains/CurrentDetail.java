package hkmu.comps321f.weather_app.Domains;

import org.json.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.lang.String;


public class CurrentDetail {
    private String w_code, description, temp, temp_min, temp_max, rain, humidity, wind_speed, date, pic_path;
    public static ArrayList<CurrentDetail> currentDetailArrayList = new ArrayList<>();
    private static Map<String, String> wCodeList;
    static {
        wCodeList = new HashMap<>();
        wCodeList.put("0","Clear Sky");
        wCodeList.put("1","Mainly Clear");
        wCodeList.put("2","Partly Cloudy");
        wCodeList.put("3","Overcast");
        wCodeList.put("45","Fog");
        wCodeList.put("48","Rime Fog");
        wCodeList.put("51","Drizzle");
        wCodeList.put("53","Drizzle");
        wCodeList.put("55","Dense Drizzle");
        wCodeList.put("56","Freezing Drizzle");
        wCodeList.put("57","Dense Freezing Drizzle");
        wCodeList.put("61","Slightly Rain");
        wCodeList.put("63","Rain");
        wCodeList.put("65","Heavy Rain");
        wCodeList.put("66","Freezing Rain");
        wCodeList.put("67","Heavy Freezing Rain");
        wCodeList.put("71","Slight Snow");
        wCodeList.put("73","Snow");
        wCodeList.put("75","Heavy Snow");
        wCodeList.put("77","Snow Grain");
        wCodeList.put("80","Slight Rain Shower");
        wCodeList.put("81","Rain Shower");
        wCodeList.put("82","Violent Rain Shower");
        wCodeList.put("85","Snow Shower");
        wCodeList.put("86","Heavy Snow Shower");
        wCodeList.put("96","Thunderstorm");
    }


    public CurrentDetail(JSONObject jsonObj) {
        try {
            JSONObject current = jsonObj.getJSONObject("current");//move to current object
            //this.c_time = current.getString("time");
            this.date = retrieveDate(current.getString("time"));
            this.temp = current.getString("temperature_2m");
            this.w_code = current.getString("weather_code");
            this.humidity = current.getString("relative_humidity_2m");
            this.wind_speed = current.getString("wind_speed_10m");
            this.rain = current.getString("rain");
            JSONObject daily = jsonObj.getJSONObject("daily");//move to daily object
            JSONArray dailyMaxTempArray = (JSONArray) daily.get("temperature_2m_max");
            this.temp_max = dailyMaxTempArray.getString(0);
            JSONArray dailyMinTempArray = (JSONArray) daily.get("temperature_2m_min");
            this.temp_min = dailyMinTempArray.getString(0);
            this.description = translateDescription(w_code);
            this.pic_path = translateIcon(w_code);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getW_code() {
        return w_code;
    }

    public void setW_code(String w_code) {
        this.w_code = w_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    /*public String getC_time() {
        return c_time;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }*/

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public String translateIcon(String w_code) {
        return wDataType.wIconMap.get(w_code);
    }
    public String translateDescription(String w_code) {
        return wCodeList.get(w_code);
    }

    public String retrieveDate(String time) {
        String dateStr = "";
        String newDate = "";
        for (int i = 0; i <= 10; i++) {
            dateStr += time.charAt(i);

        }
        try {
            Date theDate = new SimpleDateFormat("yyyy-mm-dd").parse(dateStr);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMM yyyy");
            newDate = (String) newDateFormat.format(theDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static void addCurrentDetail(CurrentDetail c){
        currentDetailArrayList.add(c);
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

    /*this.id = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
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
            e.printStackTrace();*/


