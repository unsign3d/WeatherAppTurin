package it.unsigned.weatherappturin;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    Weather mWeather;
    private WeatherObjectAdapter mWeatherObjectAdapter;

    public MainActivityFragment() {
        // this is the Dagger magic to do DI
        mWeather = DaggerWeatherComponent.builder().weatherModule(new WeatherModule()).build().provideWeather();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // get the weather

        FetchWeatherTask weatherTask = new FetchWeatherTask();
        weatherTask.execute();

        CreateStubWeatherConditions stub = new CreateStubWeatherConditions();

        List<WeatherObject> weatherObjects = stub.list;
        mWeatherObjectAdapter = new WeatherObjectAdapter(getActivity(), weatherObjects);


        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mWeatherObjectAdapter);

        return rootView;
    }

    public class FetchWeatherTask extends AsyncTask<Void, Void, List<WeatherObject>> {

        private List<WeatherObject> mWheatherList;

        public FetchWeatherTask() {
        }

        @Override
        // retrive and bind the view
        protected void onPreExecute(){

        }

        @Override
        // this is done in the other 3d so you sould not touch
        // the ui
        protected List<WeatherObject> doInBackground(Void... params) {

            String exception = "";
            mWheatherList = mWeather.getWeather(exception);

            if(!exception.isEmpty()) {
                Log.e("weatherapp", "problem retriving weather" + exception);
            }

            return mWheatherList;
        }

        @Override
        // we process the ui here
        protected void onPostExecute(List<WeatherObject> result){
            if(result != null) {
                mWeatherObjectAdapter.clear();
                for(WeatherObject tmp : result) {
                    mWeatherObjectAdapter.add(tmp);
                }
            }
        }
    }



}
