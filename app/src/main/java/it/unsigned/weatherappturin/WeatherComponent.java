package it.unsigned.weatherappturin;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Luca Bruzzone on 31/05/2015.
 */
@Singleton
@Component(modules = WeatherModule.class)
public interface WeatherComponent {
    Weather provideWeather();
}
