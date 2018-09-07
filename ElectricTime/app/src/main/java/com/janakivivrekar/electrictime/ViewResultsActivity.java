package com.janakivivrekar.electrictime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import static com.janakivivrekar.electrictime.ElectricTransportUtils.getInRangeElectricTransports;
import static com.janakivivrekar.electrictime.ElectricTransportUtils.ElectricTransportComparator;


public class ViewResultsActivity extends AppCompatActivity {
    double distance;
    double time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);
        // Get the Intent that started this activity and extract the user's input distance, time, and selectedElectricTransport
        Intent intent = getIntent();
        this.distance = intent.getDoubleExtra(MainActivity.DISTANCE, 0.00);
        this.time = intent.getDoubleExtra(MainActivity.TIME, Double.MAX_VALUE);
        System.out.println("distance in ViewResultsActivity: " + Double.toString(distance));
        ElectricTransport selectedElectricTransport = (ElectricTransport) intent.getSerializableExtra(SelectTransportActivity.SELECTED_TRANSPORT);

        ArrayList<ElectricTransport> inRangeElectricTransports =  getInRangeElectricTransports(this.distance);
        inRangeElectricTransports.remove(selectedElectricTransport);
        inRangeElectricTransports.remove(ElectricTransport.NoPreference);

        // Sort list of all in range electric transports
        Collections.sort(inRangeElectricTransports, new ElectricTransportComparator());

        // Get layout to display results
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_view_results, null);
        // Find the ScrollView
        ScrollView results_scroll_view = v.findViewById(R.id.results_scroll_view);
        // Create a LinearLayout element
        LinearLayout results_linear_layout = new LinearLayout(this);
        results_linear_layout.setOrientation(LinearLayout.VERTICAL);

        /* TODO: special view for selected transport */
        if (selectedElectricTransport != null) {
            this.setElectricTransportResultView(selectedElectricTransport, results_linear_layout);
        }

        for (ElectricTransport electricTransport : inRangeElectricTransports) {
            this.setElectricTransportResultView(electricTransport, results_linear_layout);
        }

        // Add the LinearLayout element to the ScrollView
        results_scroll_view.addView(results_linear_layout);

        // Display the view
        setContentView(v);
    }

    private void setElectricTransportResultView(ElectricTransport electricTransport, LinearLayout results_linear_layout) {
        // Add electricTransportName
        TextView electricTransportName = new TextView(this);
        electricTransportName.setText(electricTransport.toString());
        // Add time or distance
        TextView electricTransportInfo = new TextView(this);
        if (this.distance != 0.0) {
            electricTransportInfo.setText("time: " + this.convertDistanceToTime(electricTransport).toString());
        } else {
            electricTransportInfo.setText(String.format("distance: %.1f miles", this.convertTimeToDistance(electricTransport)));
        }
        results_linear_layout.addView(electricTransportName);
        results_linear_layout.addView(electricTransportInfo);
    }

    private Time convertDistanceToTime(ElectricTransport electricTransport) {
        double totalTime = this.distance / electricTransport.getSpeed();
        int hours = (int) totalTime;
        int minutes = (int) (totalTime * 60) % 60;

        return new Time(hours, minutes);
    }

    private double convertTimeToDistance(ElectricTransport electricTransport) {
        return this.time * electricTransport.getSpeed();
    }



    private static class Time {
        int hours;
        int minutes;

        Time(int hours, int minutes) {
            this.hours = hours;
            this.minutes = minutes;
        }

        @Override
        public String toString() {
            if (this.hours == 0) {
                return Integer.toString(this.minutes) + " min";
            } else {
                return Integer.toString(this.hours) + " hr " + Integer.toString(this.minutes) + " min";
            }
        }
    }
}