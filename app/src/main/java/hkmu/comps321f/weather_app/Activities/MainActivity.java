package hkmu.comps321f.weather_app.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.*;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import hkmu.comps321f.weather_app.Adapter.HourlyAdapter;
import hkmu.comps321f.weather_app.Domains.CurrentDetail;
import hkmu.comps321f.weather_app.Domains.Hourly;
import hkmu.comps321f.weather_app.JsonHandler.JsonHandler;
import hkmu.comps321f.weather_app.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adaptorHourly;
    private RecyclerView recyclerView;

    private String TAG = "MainActivity";

    final String lat = "22.3";
    final String lon = "114.1";

    //private String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=22.2783&longitude=114.1747&current=temperature_2m,relative_humidity_2m,is_day,precipitation,rain,weather_code,wind_speed_10m&hourly=temperature_2m,precipitation_probability,weather_code&daily=weather_code,temperature_2m_max,temperature_2m_min,rain_sum,wind_speed_10m_max";

    //final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    // URL to get contacts JSON file
    TextView date, todayWeather, currentTemp, tempRange, rain, wind, humid;
    ImageView todayWeatherImg;
    LinearLayout currentLayout;
    //private List<CurrentDetail> list;

    //color theme
    Switch switcher;
    boolean nightMODE;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //-------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //----------------color theme----------------------------
        switcher = findViewById(R.id.switcher);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMODE = sharedPreferences.getBoolean("night", false);

        if(nightMODE){
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (nightMODE){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();
            }
        });
        //----------------------------------
        currentLayout = findViewById(R.id.cLayout);

        date = findViewById(R.id.date);
        todayWeather = findViewById(R.id.todayWeatherText);
        currentTemp = findViewById(R.id.currentTemp);

        tempRange = findViewById(R.id.tempRangeText);
        rain = findViewById(R.id.rainText);
        wind = findViewById(R.id.windText);
        humid = findViewById(R.id.humidText);
        todayWeatherImg = findViewById(R.id.todayWeatherImg);

        /*-----------------------------------*/
        JsonHandler jsonHandler = new JsonHandler("22.2783", "114.1747");//feed JsonHandler with coordinate

        jsonHandler.start();
        try{
            jsonHandler.join();
         } catch (InterruptedException e) {
        Log.e(TAG, "InterruptedException: " + e.getMessage());
        }
        CurrentDetail current = CurrentDetail.currentDetailArrayList.get(0);
        date.setText(current.getDate());
        todayWeather.setText(current.getDescription());
        currentTemp.setText(current.getTemp() + " °");
        tempRange.setText("H: " + current.getTemp_max()+" °  L: " + current.getTemp_min() + " °");
        rain.setText(current.getRain() + " mm");
        wind.setText(current.getWind_speed() + " Km/h");
        humid.setText(current.getHumidity() + " %");

        int cWeatherIcon = getResources().getIdentifier(current.getPic_path(), "drawable", getPackageName());
        todayWeatherImg.setImageResource(cWeatherIcon);

        /*-----------------------------------*/

        initRecyclerView(Hourly.hourlyArrayList);// hourly view
        setVariable();//ForecastPageButton*/
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
    private void initRecyclerView(ArrayList<Hourly> hourlyArrayList) {

        /*ArrayList<Hourly> items= new ArrayList<>();
        items.add(new Hourly("10 pm",20,"cloudy"));
        items.add(new Hourly("11 pm",20,"cloudy"));
        items.add(new Hourly("12 pm",20,"day_rain"));
        items.add(new Hourly("1 am",20,"cloudy"));
        items.add(new Hourly("1 am",20,"cloudy"));*/

        recyclerView=findViewById(R.id.hourlyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adaptorHourly=new HourlyAdapter(hourlyArrayList);
        recyclerView.setAdapter(adaptorHourly);


        }
    }