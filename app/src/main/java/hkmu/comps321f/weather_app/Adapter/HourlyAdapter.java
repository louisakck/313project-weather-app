package hkmu.comps321f.weather_app.Adapter;

import android.view.*;
import android.widget.*;
import android.content.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import hkmu.comps321f.weather_app.R;
import hkmu.comps321f.weather_app.Domains.Hourly;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {
    ArrayList<Hourly> items;

    public HourlyAdapter(ArrayList<Hourly> items) {
        this.items = items;
    }

    Context context;

    @NonNull
    @Override
    public HourlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate=LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly,parent, false);
        context=parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyAdapter.ViewHolder holder, int position) {
        int theHour = Integer.parseInt(items.get(position).getHour());
        String theHourText;
        if(theHour > 24){
            theHourText = theHour % 12 + " am";
        }else if(theHour == 24){
            theHourText = "12 pm";
        }
        else if(theHour == 12){
            theHourText = "12 noon";
        }
        else if(theHour > 12){
            theHourText = theHour % 12 + " pm";
        }else{
            theHourText = theHour + " am";
        }
        holder.hourText.setText(theHourText);
        holder.tempText.setText(items.get(position).getTemp()+"°");

        int drawableResourceId=holder.itemView.getResources().getIdentifier(items.get(position).getPic_path(), "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.hourWeatherImg);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hourText, tempText;
        ImageView hourWeatherImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hourText=itemView.findViewById(R.id.hourText);
            tempText=itemView.findViewById(R.id.tempText);
            hourWeatherImg=itemView.findViewById(R.id.hourWeatherImg);
        }
    }
}
