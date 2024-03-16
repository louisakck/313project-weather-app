package hkmu.comps321f.weather_app.Activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import hkmu.comps321f.weather_app.Domains.CurrentDetail;
import hkmu.comps321f.weather_app.Domains.TomorrowDomain;
import hkmu.comps321f.weather_app.JsonHandler.JsonHandler;
import hkmu.comps321f.weather_app.R;
import hkmu.comps321f.weather_app.Domains.ForecastDomain;
import hkmu.comps321f.weather_app.Adapter.ForecastAdapter;


public class ForecastActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterForecast;
    private RecyclerView recyclerView;
    private String TAG = "ForecastActivity";

    TextView tomorrowTemp, tomorrowWeather, rain,  wind;
    ImageView tomorrowWeatherImg;
    LinearLayout tomorrowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        tomorrowLayout = findViewById(R.id.tomorrowLayout);
        tomorrowWeather = findViewById(R.id.tomorrowWeatherText);
        tomorrowTemp = findViewById(R.id.tomorrowTemp);
        rain = findViewById(R.id.rainText);
        wind = findViewById(R.id.windText);
        tomorrowWeatherImg = findViewById(R.id.tomorrowWeatherImg);

        /*-----------------------------------*/
        TomorrowDomain tomorrowDomain = TomorrowDomain.tomorrowArrayList.get(0);
        tomorrowWeather.setText(tomorrowDomain.getDescription());
        tomorrowTemp.setText("H: " + tomorrowDomain.getTemp_max()+"°  L: " + tomorrowDomain.getTemp_min() + "°");
        rain.setText(tomorrowDomain.getRain() + " mm");
        wind.setText(tomorrowDomain.getWind_speed() + " Km/h");

        int tTomorrowIcon = getResources().getIdentifier(tomorrowDomain.getPic_path(), "drawable", getPackageName());
        tomorrowWeatherImg.setImageResource(tTomorrowIcon);
        /*-----------------------------------*/

        initRecyclerView(ForecastDomain.forecastArrayList);
        setVariable();
    }

    private void setVariable(){
        ConstraintLayout backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForecastActivity.this, MainActivity.class));
            }
        });
    }

    private void initRecyclerView(ArrayList<ForecastDomain> forecastArrayList){
        /*ArrayList<ForecastDomain> items= new ArrayList<>();
        items.add(new ForecastDomain("Wed","fog", "Fog", "23", "20"));
        items.add(new ForecastDomain("Thur","cloudy", "Cloudy", "15", "14"));
        items.add(new ForecastDomain("Fri","rain", "Rainy", "14", "13"));
        items.add(new ForecastDomain("Sat","snow", "Snow", "6", "3"));
        items.add(new ForecastDomain("Sun","snow", "Snow", "6", "3"));*/

        recyclerView=findViewById(R.id.forecastView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterForecast= new ForecastAdapter(forecastArrayList);
        //adapterForecast= new ForecastAdapter(items);
        recyclerView.setAdapter(adapterForecast);


    }
}