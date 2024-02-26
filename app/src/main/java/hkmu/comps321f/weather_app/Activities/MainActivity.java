package hkmu.comps321f.weather_app.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.*;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import hkmu.comps321f.weather_app.Adapter.HourlyAdapter;
import hkmu.comps321f.weather_app.Domains.Hourly;
import hkmu.comps321f.weather_app.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adaptorHourly;
    private RecyclerView recyclerView;

    final String APP_ID = "5a1b920d631fb8ee83f507ebc66efd19";
    final String lat ="22.3";
    final String log ="114.1";
    //final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    final String WEATHER_URL = "https://api.openweathermap.org/data/3.0/onecall?lat="+lat+"&lon="+log+"&appid="+APP_ID;


    DecimalFormat df = new DecimalFormat("#.##");

    TextView date, todayWeather, currentTemp, tempRange, rain, wind, humid;
    ImageView todayWeatherImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*todayWeather = findViewById(R.id.todayWeatherText);
        currentTemp = findViewById(R.id.currentTemp);
        tempRange = findViewById(R.id.tempRangeText);
        rain = findViewById(R.id.rainText);
        wind = findViewById(R.id.windText);
        humid = findViewById(R.id.humidText);
        todayWeatherImg = findViewById(R.id.todayWeatherImg);

        initRecyclerView();
        setVariable();//ForecastPageButton*/
    }

    /*public void getWeatherDetails(View view){
        RequestQueue
    }*/


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