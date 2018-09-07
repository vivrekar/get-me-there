package com.janakivivrekar.electrictime;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import static com.janakivivrekar.electrictime.ElectricTransportUtils.ElectricTransportComparator;
import static com.janakivivrekar.electrictime.ElectricTransportUtils.getInRangeElectricTransports;
import static com.janakivivrekar.electrictime.MainActivity.DISTANCE;
import static com.janakivivrekar.electrictime.MainActivity.TIME;
import static com.janakivivrekar.electrictime.SelectTransportActivity.SELECTED_TRANSPORT;

public class ResultsListViewFragment extends ListFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Unpack bundle of args
        Bundle args = getArguments();
        double distance = args.getDouble(DISTANCE);
        double time = args.getDouble(TIME);
        ElectricTransport selectedElectricTransport = (ElectricTransport) args.getSerializable(SELECTED_TRANSPORT);

        ArrayList<ElectricTransport> inRangeElectricTransports =  getInRangeElectricTransports(distance);
        inRangeElectricTransports.remove(selectedElectricTransport);
        inRangeElectricTransports.remove(ElectricTransport.NoPreference);

        // Sort list of all in range electric transports
        Collections.sort(inRangeElectricTransports, new ElectricTransportComparator());

        /* TODO: special adapter for selected transport */

        InRangeElectricTransportAdapter adapter = new InRangeElectricTransportAdapter(inRangeElectricTransports);
        setListAdapter(adapter);
    }

    private class InRangeElectricTransportAdapter extends ArrayAdapter<ElectricTransport> {
        public InRangeElectricTransportAdapter(ArrayList<ElectricTransport> electricTransports) {
            super(getActivity(), 0, electricTransports);
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.activity_view_results,null);
            }
            ElectricTransport electricTransport = getItem(position);

            // Set Name
            TextView electricTransportName = convertView.findViewById(R.id.electric_transport_name);
            electricTransportName.setText(electricTransport.getName());

            // Set description (about time or distance)
            TextView electricTransportDescription = convertView.findViewById(R.id.electric_transport_description);
            electricTransportDescription.setText("HELLOOOOO!!!!!");

            // Set image
            /*
            ImageView electricTransportImage = convertView.findViewById(R.id.electric_transport_image);
            if (electricTransport.getImage() != null) {
                electricTransportImage.setImageBitmap(electricTransport.getImage());
            } */
            return convertView;
        }
    }

}
