package hkmu.comps321f.weather_app.Domains;

import java.util.HashMap;
import java.util.Map;

public class wDataType {
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
    public static Map<String, String> wCodeList;
    static {
        wCodeList = new HashMap<>();
        wCodeList.put("0","Clear Sky");
        wCodeList.put("1","Mainly Clear");
        wCodeList.put("2","Partly Cloudy");
        wCodeList.put("3","Overcast");
        wCodeList.put("45","Fog");
        wCodeList.put("48","Rime Fog");
        wCodeList.put("51","Drizzle");
        wCodeList.put("53","Drizzle");
        wCodeList.put("55","Dense Drizzle");
        wCodeList.put("56","Freezing Drizzle");
        wCodeList.put("57","Dense Freezing Drizzle");
        wCodeList.put("61","Slightly Rain");
        wCodeList.put("63","Rain");
        wCodeList.put("65","Heavy Rain");
        wCodeList.put("66","Freezing Rain");
        wCodeList.put("67","Heavy Freezing Rain");
        wCodeList.put("71","Slight Snow");
        wCodeList.put("73","Snow");
        wCodeList.put("75","Heavy Snow");
        wCodeList.put("77","Snow Grain");
        wCodeList.put("80","Slight Rain Shower");
        wCodeList.put("81","Rain Shower");
        wCodeList.put("82","Violent Rain Shower");
        wCodeList.put("85","Snow Shower");
        wCodeList.put("86","Heavy Snow Shower");
        wCodeList.put("96","Thunderstorm");
    }

    public static String translateIcon(String w_code) {
        return wIconMap.get(w_code);
    }
    public static String translateDescription(String w_code) {return wCodeList.get(w_code);}

}
