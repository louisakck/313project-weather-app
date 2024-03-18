package hkmu.comps321f.weather_app.Adapter;

import android.content.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import hkmu.comps321f.weather_app.Domains.ForecastDomain;
import hkmu.comps321f.weather_app.R;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    ArrayList<ForecastDomain> items;
    Context context;
    private static final String TAG = "ForecastAdapter";

    public ForecastAdapter(ArrayList<ForecastDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate=LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_forecast,parent, false);
        context=parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapter.ViewHolder holder, int position) {
        holder.dayText.setText(items.get(position).getDay());
        holder.statusText.setText(items.get(position).getStatus());
        holder.highText.setText(items.get(position).getHighTemp()+" °");
        holder.lowText.setText(items.get(position).getLowTemp()+" °");
        holder.rainText.setText(items.get(position).getLowTemp()+" %");
        holder.windText.setText(items.get(position).getLowTemp()+" km/h");

        int drawableResourceId=holder.itemView.getResources().getIdentifier(items.get(position).getPicPath(), "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.weatherImg);

        ForecastDomain forecastdomain = items.get(position);
        boolean isVisible = forecastdomain.isVisibility();
        holder.expandedLayout.setVisibility(isVisible? View.VISIBLE:View.GONE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void changeStateOfItems(int p){
        for (int i = 0 ; i < items.size(); i++){
            if (i == p){
                continue;
            }else{
            items.get(i).setVisibility(false);
            Log.e(TAG, "getItemPosition: " + i + "visible: " + items.get(i).isVisibility());
            }
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dayText, statusText, lowText, highText, rainText, windText;
        ImageView weatherImg;
        LinearLayout expandedLayout, briefWeatherHead;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText=itemView.findViewById(R.id.dayText);
            statusText=itemView.findViewById(R.id.statusText);
            lowText=itemView.findViewById(R.id.lowText);
            highText=itemView.findViewById(R.id.highText);
            weatherImg=itemView.findViewById(R.id.weatherImg);
            rainText=itemView.findViewById(R.id.rainText);
            windText=itemView.findViewById(R.id.windText);
            briefWeatherHead=itemView.findViewById(R.id.briefWeatherHead);
            expandedLayout=itemView.findViewById(R.id.expandedLayout);


            briefWeatherHead.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    ForecastDomain theForecastDomain = items.get(getAdapterPosition());
                    if (theForecastDomain.isVisibility())
                        theForecastDomain.setVisibility(false);
                    else{
                        theForecastDomain.setVisibility(true);
                        Log.e(TAG, "getAdapterPosition: " + getAdapterPosition());
                        changeStateOfItems(getAdapterPosition());
                    }
                    //theForecastDomain.setVisibility(!theForecastDomain.isVisibility());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
