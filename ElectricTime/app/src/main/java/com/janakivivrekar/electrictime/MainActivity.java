package com.janakivivrekar.electrictime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View.OnFocusChangeListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import static com.janakivivrekar.electrictime.ElectricTransportUtils.DISTANCE;
import static com.janakivivrekar.electrictime.ElectricTransportUtils.TIME;
import static com.janakivivrekar.electrictime.ElectricTransportUtils.RESULTS_DESCRIPTION;
import static com.janakivivrekar.electrictime.ElectricTransportUtils.Time;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText enter_distance = findViewById(R.id.enter_distance);
        EditText enter_hours = findViewById(R.id.enter_hours);
        EditText enter_minutes = findViewById(R.id.enter_minutes);
        Button next_button = findViewById(R.id.main_next_button);
        next_button.setEnabled(false);

        enter_distance.setOnFocusChangeListener(editTextOnFocusChangeListener);
        enter_hours.setOnFocusChangeListener(editTextOnFocusChangeListener);
        enter_minutes.setOnFocusChangeListener(editTextOnFocusChangeListener);
    }

    /** Called when the user clicks the Next button */
    public void clickNext(View view) {
        Intent selectTransportActivityIntent = new Intent(this, SelectTransportActivity.class);
        Intent viewResultsActivityIntent = new Intent(this, ViewResultsActivity.class);

        EditText enter_distance = findViewById(R.id.enter_distance);
        EditText enter_hours = findViewById(R.id.enter_hours);
        EditText enter_minutes = findViewById(R.id.enter_minutes);

        // Find out whether distance and time fields are populated
        boolean valid_distance = !TextUtils.isEmpty(enter_distance.getText());
        boolean valid_hours = !(TextUtils.isEmpty(enter_hours.getText()));
        boolean valid_minutes = !(TextUtils.isEmpty(enter_minutes.getText()));

        /* TODO: if no vehicles in range then go to a screen that says that */

        // Convert time to Time
        int minutes;
        Time time;
        if (valid_hours && valid_minutes) {
            minutes = (Integer.parseInt(enter_hours.getText().toString()) * 60) + Integer.parseInt(enter_minutes.getText().toString());
        } else if (valid_hours) {
            minutes = (Integer.parseInt(enter_hours.getText().toString()) * 60);
        } else if (valid_minutes) {
            minutes = Integer.parseInt(enter_minutes.getText().toString());
        } else {
            minutes = Integer.MAX_VALUE;
        }
        time = new Time(minutes / 60, minutes % 60);

        double distance;
        if (valid_distance && (valid_hours || valid_minutes)) {
            sendDistanceEditTextInputToIntent(DISTANCE, enter_distance, viewResultsActivityIntent);
            viewResultsActivityIntent.putExtra(TIME, time);
            distance = Double.parseDouble(enter_distance.getText().toString());
            viewResultsActivityIntent.putExtra(
                    RESULTS_DESCRIPTION,
                    String.format("Here's how long it takes for some modes of electric transport to cover %.1f miles in " + time.toString(), distance));
            startActivity(viewResultsActivityIntent); // Go to next screen
        } else if (valid_distance) {
            sendDistanceEditTextInputToIntent(DISTANCE, enter_distance, selectTransportActivityIntent);
            distance = Double.parseDouble(enter_distance.getText().toString());
            selectTransportActivityIntent.putExtra(
                    RESULTS_DESCRIPTION,
                    String.format("Here's how long it takes for some modes of electric transport to cover %.1f miles: ", distance));
            startActivity(selectTransportActivityIntent); // Go to next screen
        } else if (valid_hours || valid_minutes) {
            selectTransportActivityIntent.putExtra(TIME, time);
            selectTransportActivityIntent.putExtra(
                    RESULTS_DESCRIPTION,
                    "Here's how far some modes of electric transport can take you in " + time.toString());
            startActivity(selectTransportActivityIntent); // Go to next screen
        }
    }

    /** Extract user inputted double from EditText field and send to specified intent.*/
    private void sendDistanceEditTextInputToIntent(String name, EditText entered_distance, Intent intent) {
        double input = Double.parseDouble(entered_distance.getText().toString());
        intent.putExtra(name, input);
    }

    /** Listens to focus changes in edit text fields and triggers up. */
    private OnFocusChangeListener editTextOnFocusChangeListener =  new OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            // When focus is lost check that the text field has valid values
            if (!hasFocus) { checkEnteredTextAndUpdateNextButton(); }
        }
    };

    /** Checks enter_distance and enter_time EditText fields. If both fields are empty, then the
     *  next button is disabled. Else if either field is non-empty the next button is enabled. */
    private void checkEnteredTextAndUpdateNextButton() {
        EditText enter_distance = findViewById(R.id.enter_distance);
        EditText enter_hours = findViewById(R.id.enter_hours);
        EditText enter_minutes = findViewById(R.id.enter_minutes);
        Button next_button = findViewById(R.id.main_next_button);
        if (TextUtils.isEmpty(enter_distance.getText())
                && TextUtils.isEmpty(enter_hours.getText())
                && TextUtils.isEmpty(enter_minutes.getText())) {
            next_button.setEnabled(false);
        } else {
            next_button.setEnabled(true);
        }
    }
}