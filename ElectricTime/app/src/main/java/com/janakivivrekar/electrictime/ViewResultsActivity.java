package com.janakivivrekar.electrictime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;



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

    protected Fragment createFragment() {
        Intent intent = getIntent();

        Bundle args = new Bundle();
        args.putDouble(
                MainActivity.DISTANCE,
                intent.getDoubleExtra(MainActivity.DISTANCE, 0.00)
        );
        args.putDouble(
                MainActivity.TIME,
                intent.getDoubleExtra(MainActivity.TIME, Double.MAX_VALUE)
        );
        args.putSerializable(
                SelectTransportActivity.SELECTED_TRANSPORT,
                intent.getSerializableExtra(SelectTransportActivity.SELECTED_TRANSPORT)
        );

        ResultsListViewFragment resultsListViewFragment = new ResultsListViewFragment();
        resultsListViewFragment.setArguments(args);
        return resultsListViewFragment;
    }
}