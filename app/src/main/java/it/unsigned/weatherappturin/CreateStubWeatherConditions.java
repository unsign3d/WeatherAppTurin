package it.unsigned.weatherappturin;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Luca Bruzzone on 10/05/2015.
 */
public class CreateStubWeatherConditions {
    List<WeatherObject> list;

    public CreateStubWeatherConditions(){
        this.list = new ArrayList<WeatherObject>(5);
        WeatherObject t1 = new WeatherObject("-1", "+1", "oggi", "bello");
        WeatherObject t2 = new WeatherObject("-1", "+1", "oggi", "bello");
        WeatherObject t3 = new WeatherObject("-1", "+1", "oggi", "bello");
        WeatherObject t4 = new WeatherObject("-1", "+1", "oggi", "bello");
        WeatherObject t5 = new WeatherObject("-1", "+1", "oggi", "bello");

        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
    }
}
