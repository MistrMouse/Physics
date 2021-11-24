package com.mistrmouse.physics;

public abstract class PhysicsObject3D {
    public final double volume, density;
    private double cx, cy, cz, thetaX, thetaY, thetaZ;

    public PhysicsObject3D(double volume, double density, double cx, double cy, double cz, double thetaX, double thetaY, double thetaZ) {
        this.volume = volume;
        this.density = density;
        this.cx = cx;
        this.cy = cy;
        this.cz = cz;
        this.thetaX = thetaX;
        this.thetaY = thetaY;
        this.thetaZ = thetaZ;
    }

    public double getCx() {
        return cx;
    }

    public double getCy() {
        return cy;
    }

    public double getCz() {
        return cz;
    }

    public double getThetaX() {
        return thetaX;
    }

    public double getThetaY() {
        return thetaY;
    }

    public double getThetaZ() {
        return thetaZ;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public void setCz(double cz) {
        this.cz = cz;
    }

    public void setThetaX(double thetaX) {
        this.thetaX = thetaX;
    }

    public void setThetaY(double thetaY) {
        this.thetaY = thetaY;
    }

    public void setThetaZ(double thetaZ) {
        this.thetaZ = thetaZ;
    }
}
