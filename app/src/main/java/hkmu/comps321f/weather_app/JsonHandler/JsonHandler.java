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
import java.util.ArrayList;

import hkmu.comps321f.weather_app.Domains.CurrentDetail;
import hkmu.comps321f.weather_app.Domains.Hourly;
import hkmu.comps321f.weather_app.Domains.weatherIconMap;

public class JsonHandler extends Thread {
    private static final String TAG = "JsonHandler";

    private String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=22.2783&longitude=114.1747&current=temperature_2m,relative_humidity_2m,is_day,precipitation,rain,weather_code,wind_speed_10m&hourly=temperature_2m,precipitation_probability,weather_code&daily=weather_code,temperature_2m_max,temperature_2m_min,rain_sum,wind_speed_10m_max";

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
                //get current weather
                JSONObject jsonObj = new JSONObject(weatherStr);
                CurrentDetail currentWeather = new CurrentDetail(jsonObj);
                CurrentDetail.addCurrentDetail(currentWeather);
                //get hourly weather
                JSONObject current = jsonObj.getJSONObject("current");//move to current object for current time first
                String time = retrieveTime(current.getString("time"));
                JSONObject hourly = jsonObj.getJSONObject("hourly");
                JSONArray tempArray = hourly.getJSONArray("temperature_2m");
                JSONArray weatherArray = hourly.getJSONArray("weather_code");

                int theTime = Integer.parseInt(time);
                int endTime = theTime+12;
                String temp, icon;
                ArrayList<Hourly> hourlyArrayList = new ArrayList<>();
                for (int i = theTime; i < endTime; i++) {
                    String string_i = Integer.toString(i);
                    temp = tempArray.getString(i);
                    icon = weatherIconMap.wIconMap.get(weatherArray.getString(i));
                    Hourly theHourly = new Hourly(string_i, temp, icon);
                    Hourly.addHourly(theHourly);
                }


                //updateUI(current);


            } catch (final JSONException e) {
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

}
