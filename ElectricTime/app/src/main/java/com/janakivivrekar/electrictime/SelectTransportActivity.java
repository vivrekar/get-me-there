package com.janakivivrekar.electrictime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class SelectTransportActivity extends AppCompatActivity {
    public static final String SELECTED_TRANSPORT = "com.janakivivrekar.electrictime.selected_transport";
    public static final String IN_RANGE_TRANSPORTS_BUNDLE = "com.janakivivrekar.electrictime.in_range_transports_bundle";
    public static final String IN_RANGE_TRANSPORTS_LIST = "com.janakivivrekar.electrictime.in_range_transports_list";
    private ArrayList<ElectricTransport> inRangeElectricTransports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_transport);
        // Get the Intent that started this activity and extract the user's inputted distance
        Intent intent = getIntent();
        Double distance = intent.getDoubleExtra(MainActivity.DISTANCE, 0.00);

        // Get a list of modes of electric transport that are in range
        this.inRangeElectricTransports = getInRangeElectricTransports(distance);

        Spinner select_transport = findViewById(R.id.select_transport);

        // Create an ArrayAdapter using the array of electric transports and a default spinner layout
        ArrayAdapter<ElectricTransport> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                inRangeElectricTransports
        );
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        select_transport.setAdapter(adapter);
    }

    /** Called when the user clicks the Next button */
    public void clickNext(View view) {
        Intent viewResultsActivityIntent = new Intent(this, ViewResultsActivity.class);
        Spinner select_transport = findViewById(R.id.select_transport);
        ElectricTransport selectedElectricTransport = (ElectricTransport) select_transport.getSelectedItem();
        // Send selected transport to next intent
        viewResultsActivityIntent.putExtra(SELECTED_TRANSPORT, selectedElectricTransport);
        // Send in-range transports to next intent
        Bundle inRangeTransportsArgs = new Bundle();
        inRangeTransportsArgs.putSerializable(IN_RANGE_TRANSPORTS_LIST, this.inRangeElectricTransports);
        viewResultsActivityIntent.putExtra(IN_RANGE_TRANSPORTS_BUNDLE, inRangeTransportsArgs);
        startActivity(viewResultsActivityIntent); // Go to next screen
    }

    /** Create a list of modes of electric transport that are in range. */
    private ArrayList<ElectricTransport> getInRangeElectricTransports(Double distance) {
        ArrayList<ElectricTransport> inRangeElectricTransports = new ArrayList<>();
        for (ElectricTransport transport : ElectricTransport.values()) {
            if (transport.inRange(distance)) {
                inRangeElectricTransports.add(transport);
            }
        }
        return inRangeElectricTransports;
    }
}