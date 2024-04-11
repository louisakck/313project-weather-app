package hkmu.comps321f.weather_app.JsonHandler;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hkmu.comps321f.weather_app.Domains.CurrentDetail;
import hkmu.comps321f.weather_app.Domains.ForecastDomain;
import hkmu.comps321f.weather_app.Domains.Hourly;
import hkmu.comps321f.weather_app.Domains.TomorrowDomain;
import hkmu.comps321f.weather_app.Domains.wDataType;

public class JsonHandler extends Thread {
    private static final String TAG = "JsonHandler";
    //private String lat = "22.2783";
    //private String lon = "114.1747";
    private String lat;
    private String lon;
    private String weatherUrl;

    //"https://api.open-meteo.com/v1/forecast?latitude=22.2783&longitude=114.1747&current=temperature_2m,relative_humidity_2m,is_day,precipitation,rain,showers,snowfall,weather_code,wind_speed_10m&hourly=temperature_2m,weather_code&daily=weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max,wind_speed_10m_max&timezone=auto&forecast_days=14";

    private final int NUM_OF_HOUR = 12;//num of element in hourly

    public JsonHandler(String lat, String lon){
        this.lat = lat;
        this.lon = lon;
        weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + this.lat + "&longitude=" + this.lon + "&current=temperature_2m,relative_humidity_2m,is_day,precipitation,rain,showers,snowfall,weather_code,wind_speed_10m&hourly=temperature_2m,weather_code&daily=weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max,wind_speed_10m_max&timezone=auto&forecast_days=14";
    }

    public static String makeRequest(String weatherUrl) {
        String response = null;

        try {
            URL url = new URL(weatherUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = inputStreamToString(in);
        } catch (
                MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (
                ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (
                IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    /*--------------get current weather Json-------------*/
    private static String inputStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    /*-----------------------------------------------------*/

    public void run() {
        String weatherStr = makeRequest(weatherUrl);
        Log.e(TAG, "Response from url:" + weatherStr);

        if (weatherStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(weatherStr);
                //get current weather
                createCurrent(jsonObj);
                //get hourly weather
                createHourly(jsonObj);
                //get tomorrow weather
                createTomorrow(jsonObj);
                //get forecast weather
                createForecast(jsonObj);
            } catch (JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }
        }

    public String retrieveTime(String time) {
        String timeStr = "";
        for (int i = 11; i <= 12; i++) {
            timeStr += time.charAt(i);
        }
        return timeStr;
    }

    public void createCurrent(JSONObject obj){
        String date, temp, w_code, humidity, wind_speed, rain, temp_max, temp_min, status, pic_path;
        try {
            JSONObject current = obj.getJSONObject("current");//move to current object
            date = retrieveDate(current.getString("time"));
            temp = current.getString("temperature_2m");
            w_code = current.getString("weather_code");
            humidity = current.getString("relative_humidity_2m");
            wind_speed = current.getString("wind_speed_10m");
            rain = current.getString("rain");
            JSONObject daily = obj.getJSONObject("daily");//move to daily object
            JSONArray dailyMaxTempArray = (JSONArray) daily.get("temperature_2m_max");
            temp_max = dailyMaxTempArray.getString(0);
            JSONArray dailyMinTempArray = (JSONArray) daily.get("temperature_2m_min");
            temp_min = dailyMinTempArray.getString(0);
            status = wDataType.translateDescription(w_code);
            pic_path = wDataType.translateIcon(w_code);
            CurrentDetail currentDetail = new CurrentDetail(status, temp, temp_min, temp_max, rain, humidity, wind_speed, date, pic_path);
            CurrentDetail.addCurrentDetail(currentDetail);

        } catch (JSONException e) {
            Log.e(TAG, "Json Current parsing error: " + e.getMessage());
        }
    }

    public void createHourly(JSONObject obj) {
        String time;
        JSONArray tempArray, weatherArray;
        try {
            JSONObject current = obj.getJSONObject("current");//move to current object for current time first
            time = retrieveTime(current.getString("time"));
            JSONObject hourly = obj.getJSONObject("hourly");
            tempArray = hourly.getJSONArray("temperature_2m");
            weatherArray = hourly.getJSONArray("weather_code");

            int theTime = Integer.parseInt(time) + 1;
            int endTime = theTime + NUM_OF_HOUR;
            String temp, icon;
            for (int i = theTime; i < endTime; i++) {
                String string_i = Integer.toString(i);
                temp = tempArray.getString(i);
                icon = wDataType.wIconMap.get(weatherArray.getString(i));
                Hourly theHourly = new Hourly(string_i, temp, icon);
                Hourly.addHourly(theHourly);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Hourly parsing error: " + e.getMessage());
        }

    }

    public void createTomorrow(JSONObject obj){
        String w_code, status, temp_max, temp_min, rain, wind_speed,  pic_path;
        JSONArray weatherArray, maxTempArray, minTempArray, rainArray, windSpeedArray;
        try {
            JSONObject daily = obj.getJSONObject("daily");//move to current object
            weatherArray = daily.getJSONArray("weather_code");
            maxTempArray = daily.getJSONArray("temperature_2m_max");
            minTempArray = daily.getJSONArray("temperature_2m_min");
            rainArray = daily.getJSONArray("precipitation_probability_max");
            windSpeedArray = daily.getJSONArray("wind_speed_10m_max");

            w_code = weatherArray.getString(1);
            temp_max = maxTempArray.getString(1);
            temp_min = minTempArray.getString(1);
            rain = rainArray.getString(1);
            wind_speed = windSpeedArray.getString(1);
            status = wDataType.translateDescription(w_code);
            pic_path = wDataType.translateIcon(w_code);
            TomorrowDomain tomorrowDomain = new TomorrowDomain(status, temp_max, temp_min, rain, wind_speed, pic_path);
            TomorrowDomain.addTomorrow(tomorrowDomain);

        } catch (JSONException e) {
            Log.e(TAG, "Json Tomorrow parsing error: " + e.getMessage());
        }
    }

    public void createForecast(JSONObject obj) {
        String time;
        JSONArray timeArray, weatherArray, maxTempArray, minTempArray, rainArray, wind_speedArray;
        try {
            JSONObject daily = obj.getJSONObject("daily");//move to current object for current time first
            timeArray = daily.getJSONArray("time");
            weatherArray = daily.getJSONArray("weather_code");
            maxTempArray = daily.getJSONArray("temperature_2m_max");
            minTempArray = daily.getJSONArray("temperature_2m_min");
            rainArray = daily.getJSONArray("precipitation_probability_max");
            wind_speedArray = daily.getJSONArray("wind_speed_10m_max");

            String weekday, icon, status, maxTemp, minTemp, rain, wind_speed;
            ForecastDomain.forecastArrayList.clear();
            for (int i = 2; i < 8; i++){
                weekday = convertDate(timeArray.getString(i));
                icon = wDataType.translateIcon(weatherArray.getString(i));
                status = wDataType.translateDescription(weatherArray.getString(i));
                maxTemp = maxTempArray.getString(i);
                minTemp = minTempArray.getString(i);
                rain = rainArray.getString(i);
                wind_speed = wind_speedArray.getString(i);
                ForecastDomain theForecast = new ForecastDomain(weekday, icon, status, maxTemp, minTemp, rain, wind_speed);
                ForecastDomain.addForecastDomain(theForecast);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Forecast parsing error: " + e.getMessage());
        }

    }

    public String retrieveDate(String time) {
        //retrieve current date for CurrentDetail
        String dateStr = "";
        String newDate = "";
        for (int j = 0; j <= 10; j++) {
            dateStr += time.charAt(j);

        }
        try {
            Date theDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMM yyyy");
            newDate = (String) newDateFormat.format(theDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public String convertDate(String time) {
        //convert date to weekday for forecast
        String newDate = "";

        try {
            Date theDate = new SimpleDateFormat("yyyy-MM-dd").parse(time);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("EEE");
            newDate = (String) newDateFormat.format(theDate);

        } catch (ParseException e) {
            Log.e(TAG, "Json Forecast convert weekday error: " + e.getMessage());
        }
        return newDate;
    }

}
