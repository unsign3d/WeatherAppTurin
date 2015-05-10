package it.unsigned.weatherappturin;

/**
 * Created by Luca Bruzzone on 10/05/2015.
 */
public class WeatherObject {
    public String min;
    public String max;
    public String day;
    public String description;

    public WeatherObject(){}

    public WeatherObject(String min, String max, String day, String description) {
        this.min = min;
        this.max = max;
        this.day = day;
        this.description = description;
    }
}