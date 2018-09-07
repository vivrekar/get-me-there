package com.janakivivrekar.electrictime;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import static com.janakivivrekar.electrictime.ElectricTransportUtils.DISTANCE;
import static com.janakivivrekar.electrictime.ElectricTransportUtils.TIME;
import static com.janakivivrekar.electrictime.ElectricTransportUtils.SELECTED_TRANSPORT;


public class ViewResultsActivity extends AppCompatActivity {
    double distance;
    double time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            System.out.println("fragment is null???");
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }

    /*TODO: create new titles*/

    protected Fragment createFragment() {
        Intent intent = getIntent();

        Bundle args = new Bundle();
        args.putDouble(
                DISTANCE,
                intent.getDoubleExtra(DISTANCE, 0.00)
        );
        args.putDouble(
                TIME,
                intent.getDoubleExtra(TIME, Double.MAX_VALUE)
        );
        args.putSerializable(
                SELECTED_TRANSPORT,
                intent.getSerializableExtra(SELECTED_TRANSPORT)
        );

        ResultsListViewFragment resultsListViewFragment = new ResultsListViewFragment();
        resultsListViewFragment.setArguments(args);
        return resultsListViewFragment;
    }
}