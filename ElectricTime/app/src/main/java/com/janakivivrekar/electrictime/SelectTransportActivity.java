package com.janakivivrekar.electrictime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class SelectTransportActivity extends AppCompatActivity {
    public static final String TRANSPORT = "com.janakivivrekar.electrictime.transport";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_transport);
        // Get the Intent that started this activity and extract the user's inputted distance and time
        Intent intent = getIntent();
        Double distance = intent.getDoubleExtra(MainActivity.DISTANCE, 0.00);
        Double time = intent.getDoubleExtra(MainActivity.TIME, Double.MAX_VALUE);

        Spinner select_transport = findViewById(R.id.select_transport);
        ArrayList<ElectricTransport> electricTransports = new ArrayList<>();
        for (ElectricTransport transport : ElectricTransport.values()) {
            if (transport.inRange(distance)) {
                electricTransports.add(transport);
            }
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<ElectricTransport> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                electricTransports
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

        // Find out whether preferred transport field is populated
        boolean valid_transport = !TextUtils.isEmpty(select_transport.getContentDescription());

    }
}