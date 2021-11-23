package com.mistrmouse.physics;

public abstract class PhysicsObject2D {
    private double cx, cy, theta;

    public PhysicsObject2D(double cx, double cy, double theta) {
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
