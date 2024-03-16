package hkmu.comps321f.weather_app.Domains;


import java.util.ArrayList;
import java.lang.String;



public class CurrentDetail {
    //current weather in main activity
    private String description, temp, temp_min, temp_max, rain, humidity, wind_speed, date, pic_path;
    public static ArrayList<CurrentDetail> currentDetailArrayList = new ArrayList<>();

    public CurrentDetail(String description, String temp, String temp_min, String temp_max, String rain, String humidity, String wind_speed, String date, String pic_path) {
        this.description = description;
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.rain = rain;
        this.humidity = humidity;
        this.wind_speed = wind_speed;
        this.date = date;
        this.pic_path = pic_path;



        /*try {
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
            this.description = wDataType.translateDescription(w_code);
            this.pic_path = wDataType.translateIcon(w_code);

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    //public String translateIcon(String w_code) {
        //return wDataType.wIconMap.get(w_code);
    //}
    //public String translateDescription(String w_code) {return wDataType.wCodeList.get(w_code);}


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


