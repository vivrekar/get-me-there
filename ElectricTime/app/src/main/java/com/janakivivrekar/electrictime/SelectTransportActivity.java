package com.janakivivrekar.electrictime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import static com.janakivivrekar.electrictime.ElectricTransportUtils.getInRangeElectricTransports;
import static com.janakivivrekar.electrictime.ElectricTransportUtils.DISTANCE;
import static com.janakivivrekar.electrictime.ElectricTransportUtils.TIME;
import static com.janakivivrekar.electrictime.ElectricTransportUtils.SELECTED_TRANSPORT;


public class SelectTransportActivity extends AppCompatActivity {
    double distance;
    double time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_transport);
        // Get the Intent that started this activity and extract the user's inputted distance
        Intent intent = getIntent();
        this.distance = intent.getDoubleExtra(DISTANCE, 0.00);
        this.time = intent.getDoubleExtra(TIME, Double.MAX_VALUE);

        Spinner select_transport = findViewById(R.id.select_transport);

        // Create an ArrayAdapter using the array of electric transports and a default spinner layout
        ArrayAdapter<ElectricTransport> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                getInRangeElectricTransports(this.distance)
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
        // Send time, distance, and selected transport to next intent
        viewResultsActivityIntent.putExtra(DISTANCE, this.distance);
        viewResultsActivityIntent.putExtra(TIME, this.time);
        viewResultsActivityIntent.putExtra(SELECTED_TRANSPORT, selectedElectricTransport);
        startActivity(viewResultsActivityIntent); // Go to next screen
    }

}