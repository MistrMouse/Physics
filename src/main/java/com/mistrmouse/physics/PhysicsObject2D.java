package com.mistrmouse.physics;

public abstract class PhysicsObject2D {
    public final double area, density, mass;
    private double cx, cy, theta, xVelocity, yVelocity;

    public PhysicsObject2D(double area, double density, double cx, double cy, double theta) {
        this.area = area;
        this.density = density;
        this.cx = cx;
        this.cy = cy;
        this.theta = theta;
        mass = area * density;
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

    public double getXVelocity() {
        return xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
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

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }
}
