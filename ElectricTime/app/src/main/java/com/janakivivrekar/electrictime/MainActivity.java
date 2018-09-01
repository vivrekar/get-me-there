package com.janakivivrekar.electrictime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View.OnFocusChangeListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String DISTANCE = "com.janakivivrekar.electrictime.distance";
    public static final String TIME = "com.janakivivrekar.electrictime.time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText enter_distance = findViewById(R.id.enter_distance);
        EditText enter_time = findViewById(R.id.enter_time);
        Button next_button = findViewById(R.id.main_next_button);
        next_button.setEnabled(false);

        enter_distance.setOnFocusChangeListener(editTextOnFocusChangeListener);
        enter_time.setOnFocusChangeListener(editTextOnFocusChangeListener);
    }

    /** Called when the user clicks the Next button */
    public void clickNext(View view) {
        Intent selectTransportActivityIntent = new Intent(this, SelectTransportActivity.class);
        Intent viewResultsActivityIntent = new Intent(this, ViewResultsActivity.class);

        EditText enter_distance = findViewById(R.id.enter_distance);
        EditText enter_time = findViewById(R.id.enter_time);

        // Find out whether distance and time fields are populated
        boolean valid_distance = !TextUtils.isEmpty(enter_distance.getText());
        boolean valid_time = !TextUtils.isEmpty(enter_time.getText());

        if (valid_distance && valid_time) {
            sendDoubleEditTextInputToIntent(DISTANCE, enter_distance, viewResultsActivityIntent);
            sendDoubleEditTextInputToIntent(TIME, enter_time, viewResultsActivityIntent);
            startActivity(viewResultsActivityIntent); // Go to next screen
        } else if (valid_distance) {
            sendDoubleEditTextInputToIntent(DISTANCE, enter_distance, selectTransportActivityIntent);
            sendDoubleEditTextInputToIntent(DISTANCE, enter_distance, viewResultsActivityIntent);
            startActivity(selectTransportActivityIntent); // Go to next screen
        } else if (valid_time) {
            sendDoubleEditTextInputToIntent(TIME, enter_time, selectTransportActivityIntent);
            sendDoubleEditTextInputToIntent(TIME, enter_time, viewResultsActivityIntent);
            startActivity(selectTransportActivityIntent); // Go to next screen
        }
    }

    /** Extract user inputted double from EditText field and send to specified intent.*/
    private void sendDoubleEditTextInputToIntent(String name, EditText entered_input, Intent intent) {
        Double input = Double.parseDouble(entered_input.getText().toString());
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
        EditText enter_time = findViewById(R.id.enter_time);
        Button next_button = findViewById(R.id.main_next_button);
        if (TextUtils.isEmpty(enter_distance.getText()) && TextUtils.isEmpty(enter_time.getText())) {
            next_button.setEnabled(false);
        } else {
            next_button.setEnabled(true);
        }
    }
}