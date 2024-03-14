//--------------------Currently unused------------------------------
package hkmu.comps321f.weather_app.Adapter;

import android.content.*;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import java.util.List;

import hkmu.comps321f.weather_app.Domains.CurrentDetail;
import hkmu.comps321f.weather_app.R;

public class CurrentAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<CurrentDetail> list;

    public CurrentAdapter(Context context, List<CurrentDetail> list){
        this.layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }
    //listView item count
    public int getCount(){
        return 1;
    }
    //listView item
    public Object getItem(int position){
        return list.get(position);
    }
    //listView item id
    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.activity_main, null, false);
            holder = new ViewHolder();
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.todayWeather = (TextView) convertView.findViewById(R.id.todayWeatherText);
            holder.currentTemp = (TextView) convertView.findViewById(R.id.currentTemp);
            holder.tempRange = (TextView) convertView.findViewById(R.id.tempRangeText);
            holder.rain = (TextView) convertView.findViewById(R.id.rainText);
            holder.wind = (TextView) convertView.findViewById(R.id.windText);
            holder.humid = (TextView) convertView.findViewById(R.id.humidText);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        CurrentDetail currentDetail = list.get(position);
        holder.date.setText(currentDetail.getDate());
        holder.todayWeather.setText(currentDetail.getDescription());
        holder.currentTemp.setText(currentDetail.getTemp() + "°");
        holder.tempRange.setText("H: " + currentDetail.getTemp_max()+"° L: " + currentDetail.getTemp_min() + "°");
        holder.rain.setText(currentDetail.getRain());
        holder.wind.setText(currentDetail.getWind_speed());
        holder.humid.setText(currentDetail.getHumidity());
        Log.v("currentAdapter", "getView End, position:" + position);
        return convertView;

    }
    class ViewHolder{
        TextView date, todayWeather, currentTemp, tempRange, rain, wind, humid;
    }

}


/*
            this.c_time = current.getString("time");
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
            this.temp_max = dailyMinTempArray.getString(0);

            this.description = translateDescription(w_code);
 */
