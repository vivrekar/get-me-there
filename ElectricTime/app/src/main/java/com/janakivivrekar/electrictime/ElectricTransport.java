package com.janakivivrekar.electrictime;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

import static com.janakivivrekar.electrictime.ElectricTransportUtils.Time;


/**
 * Enumerates forms of personal electric transport.
 * */
public enum ElectricTransport implements Serializable {
    NoPreference("No Preference", 0, Double.MAX_VALUE, R.drawable.walking),
    Walking("Walking", 3.1, 30, R.drawable.walking),
    BoostedMiniSBoard("Boosted Mini S Board", 18, 7, R.drawable.boosted_mini_s_board),
    EvolveSkateboard("Evolve Skateboard", 26, 31, R.drawable.evolve_skateboard),
    OneWheel("OneWheel", 19, 7, R.drawable.onewheel),
    MotoTecSkateboard("MotoTec Skateboard", 22, 10, R.drawable.mototec_skateboard),
    SegwayNinebotOneS1("Segway Ninebot One S1", 12.5, 15, R.drawable.segway_ninebot_one_s1),
    SegwayI2SE("Segway i2 SE", 12.5, 24, R.drawable.segway_i2_se),
    RazorScooter("Razor Scooter", 10, 7, R.drawable.razor_scooter),
    GeoBlade500("GeoBlade 500", 15, 8, R.drawable.geoblade_500),
    HovertraxHoverboard("Hovertrax Hoverboard", 8, 8, R.drawable.hovertrax_hoverboard);

    /** Name of mode of transport. */
    private String name;

    /** Speed of transport in miles per hour. */
    double speed;

    /** Distance range of transport in miles. */
    double range;

    /** Drawable image of transport. */
    int drawable;

    ElectricTransport(String name, double speed, double range, int drawable) {
        this.name = name;
        this.speed = speed;
        this.range = range;
        this.drawable = drawable;

    }

    public String getName() {
        return this.name;
    }

    public Double getSpeed() {
        return this.speed;
    }

    public double getRange() {
        return this.range;
    }

    public boolean inRange(double distance) {
        return distance <= this.getRange();
    }

    /* TODO: add padding to image views */
    public int getDrawable() {
        return this.drawable;
    }

    private Time convertDistanceToTime(double distance) {
        double totalTime = distance / this.getSpeed();
        int hours = (int) totalTime;
        int minutes = (int) (totalTime * 60) % 60;

        return new Time(hours, minutes);
    }

    private double convertTimeToDistance(double time) {
        return time * this.getSpeed();
    }

    public String getDescription(double distance, double time) {
        if (distance != 0.0) {
            return "time: " + convertDistanceToTime(distance).toString();
        } else {
            return String.format("distance: %.1f miles", convertTimeToDistance(time));
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
