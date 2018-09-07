package com.janakivivrekar.electrictime;

import android.graphics.Bitmap;

import java.io.Serializable;

import static com.janakivivrekar.electrictime.ElectricTransportUtils.Time;


/**
 * Enumerates forms of personal electric transport.
 * */
public enum ElectricTransport implements Serializable {
    NoPreference("No Preference", 0, Double.MAX_VALUE),
    Walking("Walking", 3.1, 30),
    BoostedMiniSBoard("Boosted Mini S Board", 18, 7),
    EvolveSkateboard("Evolve Skateboard", 26, 31),
    OneWheel("OneWheel", 19, 7),
    MotoTecSkateboard("MotoTec Skateboard", 22, 10),
    SegwayNinebotOneS1("Segway Ninebot One S1", 12.5, 15),
    SegwayI2SE("Segway i2 SE", 12.5, 24),
    RazorScooter("Razor Scooter", 10, 7),
    GeoBlade500("GeoBlade 500", 15, 8),
    HovertraxHoverboard("Hovertrax Hoverboard", 8, 8);

    /** Name of mode of transport. */
    private String name;

    /** Speed of transport in miles per hour. */
    double speed;

    /** Distance range of transport in miles. */
    double range;

    ElectricTransport(String name, double speed, double range) {
        this.name = name;
        this.speed = speed;
        this.range = range;
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


    /*TODO*/
    public Bitmap getImage() {
        return null;
    }

    public Time convertDistanceToTime(double distance) {
        double totalTime = distance / this.getSpeed();
        int hours = (int) totalTime;
        int minutes = (int) (totalTime * 60) % 60;

        return new Time(hours, minutes);
    }

    public double convertTimeToDistance(double time) {
        return time * this.getSpeed();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
