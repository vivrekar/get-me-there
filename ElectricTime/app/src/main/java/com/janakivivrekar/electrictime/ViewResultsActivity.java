package com.janakivivrekar.electrictime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);
        // Get the Intent that started this activity and extract the user input doubles
        Intent intent = getIntent();
        Double distance = intent.getDoubleExtra(MainActivity.DISTANCE, 0.00);
        Double time = intent.getDoubleExtra(MainActivity.TIME, Double.MAX_VALUE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText("ViewResultsActivity");
    }
}