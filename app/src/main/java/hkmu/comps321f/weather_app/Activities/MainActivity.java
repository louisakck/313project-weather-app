package hkmu.comps321f.weather_app.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.*;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONException;

import hkmu.comps321f.weather_app.Adapter.HourlyAdapter;
import hkmu.comps321f.weather_app.Domains.CurrentDetail;
import hkmu.comps321f.weather_app.Domains.Hourly;
import hkmu.comps321f.weather_app.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adaptorHourly;
    private RecyclerView recyclerView;

    final String APP_ID = "5a1b920d631fb8ee83f507ebc66efd19";
    final String lat = "22.3";
    final String lon = "114.1";

    private String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?lat=22.3&lon=114.1&appid=5a1b920d631fb8ee83f507ebc66efd19";
            //"https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=APP" + APP_ID;
    final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    // URL to get contacts JSON file
    TextView date, todayWeather, currentTemp, tempRange, rain, wind, humid;
    ImageView todayWeatherImg;

    private static final String TAG = "JsonHandler";
    String response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todayWeather = findViewById(R.id.todayWeatherText);
        currentTemp = findViewById(R.id.currentTemp);
        tempRange = findViewById(R.id.tempRangeText);
        rain = findViewById(R.id.rainText);
        wind = findViewById(R.id.windText);
        humid = findViewById(R.id.humidText);
        todayWeatherImg = findViewById(R.id.todayWeatherImg);

        todayWeather.setText("testing");

        /*-----------------------------------*/

        try {
            URL url = new URL(weatherUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = inputStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }

        JSONObject jsonObj = null;
        if (response != null) {
            try {
                jsonObj = new JSONObject(response);
                CurrentDetail current = new CurrentDetail(jsonObj);
                updateUI(current);

            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }



        /*-----------------------------------*/

        initRecyclerView();
        setVariable();//ForecastPageButton*/
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
    private void updateUI(CurrentDetail current){
        todayWeather.setText(current.getStatus());
        currentTemp.setText(current.getTemp());
        String tempRangeText = "H: " + current.getTemp_max() + "  " + "L: " + current.getTemp_min();
        tempRange.setText(tempRangeText);
        //rain.setText;
        wind.setText(current.getWindSpeed());
        humid.setText(current.getHumidity());

    }

    /*-----------ForecastPageButton-------------*/
    private void setVariable(){
        TextView nextBtn=findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForecastActivity.class));
            }
        });
    }

    /*-----------Hourly RecyclerView-------------*/
    private void initRecyclerView() {
        ArrayList<Hourly> items= new ArrayList<>();
        items.add(new Hourly("10 pm",20,"cloudy"));
        items.add(new Hourly("11 pm",20,"cloudy"));
        items.add(new Hourly("12 pm",20,"day_rain"));
        items.add(new Hourly("1 am",20,"cloudy"));
        items.add(new Hourly("1 am",20,"cloudy"));

        recyclerView=findViewById(R.id.hourlyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adaptorHourly=new HourlyAdapter(items);
        recyclerView.setAdapter(adaptorHourly);


        }
    }