package it.unsigned.weatherappturin;

import android.text.format.Time;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Luca Bruzzone on 09/05/2015.
 */
@Module
public class Weather {
    private String mLoc = "Turin";
    private String mUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+mLoc+"&units=metric&cnt=7";


    public Weather(){
        // dependency injection for okhttpclient
    }

    @Provides @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    /**
     *
     * @return
     * @throws IOException when he can't download the weather, the handling is left to the higher level
     * so you can actually handle with android notification
     */
    @Provides @Singleton
    public String getJSONWheather(OkHttpClient okHttpClient, String exc){
        // build the request
        Request request = new Request.Builder().url(mUrl).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            exc = e.getLocalizedMessage();
            return "problem retriving wheather";
        }

    }

    public List<WeatherObject> getWeather(String exc){
        String exc_s = "";
        String jsonString = getJSONWheather(provideOkHttpClient(), exc_s);

        List<WeatherObject> result = new ArrayList<WeatherObject>(7);
        if(!exc_s.isEmpty()) {
            exc = exc_s;
            return null;
        }

        try {
            JSONObject jObj = new JSONObject(jsonString);
            JSONArray days = jObj.getJSONArray("list");

            // Since this data is also sent in-order and the first day is always the
            // current day, we're going to take advantage of that to get a nice
            // normalized UTC date for all of our weather.

            // Since this data is also sent in-order and the first day is always the
            // current day, we're going to take advantage of that to get a nice
            // normalized UTC date for all of our weather.

            Time dayTime = new Time();
            dayTime.setToNow();

            // we start at the day returned by local time. Otherwise this is a mess.
            int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);

            // now we work exclusively in UTC
            dayTime = new Time();


            long datetime;
            WeatherObject tmp;
            JSONObject weatherObject;
            JSONObject temperatureObject;

            for (int i = 0; i < days.length() ; i++) {
                tmp = new WeatherObject();

                JSONObject dayForecast = days.getJSONObject(i);
                datetime = dayTime.setJulianDay(julianStartDay + i);
                tmp.day = getReadableDateString(datetime);

                weatherObject = dayForecast.getJSONArray("weather").getJSONObject(0);
                tmp.description = weatherObject.getString("main");

                temperatureObject = dayForecast.getJSONObject("temp");
                tmp.min = temperatureObject.getString("min");
                tmp.max = temperatureObject.getString("max");

                result.add(tmp);

            }


        } catch (JSONException e) {
            exc = e.getLocalizedMessage();
            return null;
        }

        return result;
    }

    /* The date/time conversion code is going to be moved outside the asynctask later,
         * so for convenience we're breaking it out into its own method now.
         */
    private String getReadableDateString(long time){
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
        return shortenedDateFormat.format(time);
    }



}
