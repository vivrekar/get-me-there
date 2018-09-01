package com.janakivivrekar.electrictime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;


public class ViewResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);
        // Get the Intent that started this activity and extract the user's input
        Intent intent = getIntent();
        Double distance = intent.getDoubleExtra(MainActivity.DISTANCE, 0.00);
        Double time = intent.getDoubleExtra(MainActivity.TIME, Double.MAX_VALUE);
        ElectricTransport selectedElectricTransport = (ElectricTransport) intent.getSerializableExtra(SelectTransportActivity.SELECTED_TRANSPORT);
        Bundle args = intent.getBundleExtra(SelectTransportActivity.IN_RANGE_TRANSPORTS_BUNDLE);
        ArrayList<ElectricTransport> inRangeElectricTransports = (ArrayList<ElectricTransport>) args.getSerializable(SelectTransportActivity.IN_RANGE_TRANSPORTS_LIST);

        // Debugging toasts
        for (ElectricTransport et : inRangeElectricTransports) {
            Toast toast=Toast.makeText(getApplicationContext(), et.toString(), Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
        }

    }
}