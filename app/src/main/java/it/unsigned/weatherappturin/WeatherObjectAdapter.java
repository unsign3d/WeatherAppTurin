package it.unsigned.weatherappturin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Luca Bruzzone on 10/05/2015.
 */
public class WeatherObjectAdapter extends ArrayAdapter<WeatherObject> {
    private final Context context;
    private final List<WeatherObject> values;


    public WeatherObjectAdapter(Context context, List<WeatherObject> values) {
        super(context, R.layout.fragment_main, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {
        // declare the inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // get the views
        View rowView = inflater.inflate(R.layout.wheather_fragment, parent, false);

        TextView day = (TextView) rowView.findViewById(R.id.day_view_fragment);
        TextView min = (TextView) rowView.findViewById(R.id.min_view_fragment);
        TextView max = (TextView) rowView.findViewById(R.id.max_view_fragment);
        TextView details = (TextView) rowView.findViewById(R.id.details_view_fragment);

        WeatherObject tmp = values.get(position);

        day.setText(tmp.day);
        min.setText(tmp.min);
        max.setText(tmp.max);
        details.setText(tmp.description);

        return rowView;

    }
}
