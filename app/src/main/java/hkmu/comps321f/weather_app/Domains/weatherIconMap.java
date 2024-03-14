package hkmu.comps321f.weather_app.Domains;

import java.util.HashMap;
import java.util.Map;

public class weatherIconMap {
    public static Map<String, String> wIconMap;
    static {
        wIconMap = new HashMap<>();
        wIconMap.put("0","clear");
        wIconMap.put("1","clear");
        wIconMap.put("2","partly_cloudy");
        wIconMap.put("3","overcast");
        wIconMap.put("45","fog");
        wIconMap.put("48","fog");
        wIconMap.put("51","day_rain");
        wIconMap.put("53","day_rain");
        wIconMap.put("55","day_rain");
        wIconMap.put("56","sleet");
        wIconMap.put("57","sleet");
        wIconMap.put("61","day_rain");
        wIconMap.put("63","rain");
        wIconMap.put("65","rain");
        wIconMap.put("66","sleet");
        wIconMap.put("67","sleet");
        wIconMap.put("71","snow");
        wIconMap.put("73","snow");
        wIconMap.put("75","snow");
        wIconMap.put("77","snow");
        wIconMap.put("80","rain");
        wIconMap.put("81","rain");
        wIconMap.put("82","rain");
        wIconMap.put("85","snow");
        wIconMap.put("86","snow");
        wIconMap.put("96","thunder");
    }
}
