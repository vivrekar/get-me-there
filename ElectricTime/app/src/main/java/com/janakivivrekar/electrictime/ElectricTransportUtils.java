package com.janakivivrekar.electrictime;

import java.util.ArrayList;
import java.util.Comparator;

class ElectricTransportUtils {
    /** Create a list of modes of electric transport that are in range. */
    static ArrayList<ElectricTransport> getInRangeElectricTransports(double distance) {
        ArrayList<ElectricTransport> inRangeElectricTransports = new ArrayList<>();
        for (ElectricTransport transport : ElectricTransport.values()) {
            if (transport.inRange(distance)) {
                inRangeElectricTransports.add(transport);
            }
        }
        return inRangeElectricTransports;
    }

    static class ElectricTransportComparator implements Comparator<ElectricTransport> {
        public int compare(ElectricTransport et1, ElectricTransport et2) {
            return et2.getSpeed().compareTo(et1.getSpeed());
        }
    }

}
