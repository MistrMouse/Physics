package com.mistrmouse.physics;

public abstract class PhysicsObject2D {
    public final double volume, density;
    private double cx, cy, theta;

    public PhysicsObject2D(double volume, double density, double cx, double cy, double theta) {
        this.volume = volume;
        this.density = density;
        this.cx = cx;
        this.cy = cy;
        this.theta = theta;
    }

    public double getCx() {
        return cx;
    }

    public double getCy() {
        return cy;
    }

    public double getTheta() {
        return theta;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }
}
