package com.demo.mcpserver;

import java.util.HashMap;
import java.util.Map;

public class MarsLogic {
    public static Map<String, String> getMarsWeather(String continent) {

        Map<String, String> weather = new HashMap<>();
        weather.put("continent", continent);

        switch (continent.toLowerCase()) {
            case "aonia":
                weather.put("condition", "Sunny");
                weather.put("temperture", "10°C");
                break;
            case "cimmeria":
                weather.put("condition", "Clear");
                weather.put("temperture", "-15°C");
                break;
            case "noachis":
                weather.put("condition", "Cloudy");
                weather.put("temperture", "-70°C");
                break;
            case "sabaea":
                weather.put("condition", "Rainy");
                weather.put("temperture", "-120°C");
                break;
            case "tyrrhena":
                weather.put("condition", "Sunny");
                weather.put("temperture", "0°C");
                break;
            default:
                weather.put("condition", "");
                weather.put("temperture", "");
                break;
        } 
        return weather;
    }
}
