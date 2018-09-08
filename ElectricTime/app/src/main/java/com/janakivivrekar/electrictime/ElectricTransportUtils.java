package com.janakivivrekar.electrictime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

class ElectricTransportUtils {
    public static final String DISTANCE = "com.janakivivrekar.electrictime.distance";
    public static final String TIME = "com.janakivivrekar.electrictime.time";
    public static final String SELECTED_TRANSPORT = "com.janakivivrekar.electrictime.selected_transport";
    public static final String RESULTS_DESCRIPTION = "com.janakivivrekar.electrictime.results_description";

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

    /** Compare two ElectricTransport objects based on speed. */
    static class ElectricTransportComparator implements Comparator<ElectricTransport> {
        public int compare(ElectricTransport et1, ElectricTransport et2) {
            return et2.getSpeed().compareTo(et1.getSpeed());
        }
    }

    /** Represent time in hours and minute. */
    static class Time implements Serializable {
        int hours;
        int minutes;

        Time(int hours, int minutes) {
            this.hours = hours;
            this.minutes = minutes;
        }

        int toMinutes() {
            return this.minutes + this.hours * 60;
        }

        double toHours() {
            return ((double) this.toMinutes()) / 60;
        }


        @Override
        public String toString() {
            if (this.hours == 0) {
                return Integer.toString(this.minutes) + " min";
            } else {
                return Integer.toString(this.hours) + " hr " + Integer.toString(this.minutes) + " min";
            }
        }
    }

}
