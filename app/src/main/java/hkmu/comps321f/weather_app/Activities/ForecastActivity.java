package hkmu.comps321f.weather_app.Activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import hkmu.comps321f.weather_app.R;
import hkmu.comps321f.weather_app.Domains.ForecastDomain;
import hkmu.comps321f.weather_app.Adapter.ForecastAdapter;


public class ForecastActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterForecast;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        initRecyclerView();
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

    private void initRecyclerView(){
        ArrayList<ForecastDomain> items= new ArrayList<>();
        items.add(new ForecastDomain("Wed","fog", "Fog", 23, 20));
        items.add(new ForecastDomain("Thur","cloudy", "Cloudy", 15, 14));
        items.add(new ForecastDomain("Fri","rain", "Rainy", 14, 13));
        items.add(new ForecastDomain("Sat","snow", "Snow", 6, 3));
        items.add(new ForecastDomain("Sun","snow", "Snow", 6, 3));

        recyclerView=findViewById(R.id.forecastView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterForecast= new ForecastAdapter(items);
        recyclerView.setAdapter(adapterForecast);


    }
}