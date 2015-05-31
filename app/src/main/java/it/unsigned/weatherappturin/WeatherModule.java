package it.unsigned.weatherappturin;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Luca Bruzzone on 31/05/2015.
 */
@Module
public class WeatherModule {
    @Provides @Singleton
    OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Provides @Singleton
    Weather weather() {
        return new Weather(new OkHttpClient());
    }
}
