package com.mistrmouse.physics.objects;

import com.mistrmouse.physics.Distance;
import com.mistrmouse.physics.PhysicsObject3D;

public class Cube extends PhysicsObject3D {
    private double w, h, d;
    public Cube(double density, double w, double h, double d, double cx, double cy, double cz, double thetaX, double thetaY, double thetaZ) {
        super(calculateVolume(w, h, d), density, cx, cy, cz, thetaX, thetaY, thetaY);
        this.w = w;
        this.h = h;
        this.d = d;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public double getD() {
        return d;
    }

    public void setW(double w) {
        this.w = w;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setD(double d) {
        this.d = d;
    }

    public boolean detectCollision(Sphere s) {
        return Distance.betweenCubeAndSphere(w, h, d, getCx(), getCy(), getCz(), getThetaX(), getThetaY(), getThetaZ(), s.getR(), s.getCx(), s.getCy(), s.getCz()) < 0;
    }

    public boolean detectCollision(Cube c) {
        return Distance.betweenCubes(w, h, d, getCx(), getCy(), getCz(), getThetaX(), getThetaY(), getThetaZ(), c.getW(), c.getH(), c.getD(), c.getCx(), c.getCy(), c.getCz(), c.getThetaX(), c.getThetaY(), c.getThetaZ()) < 0;
    }

    public static double calculateVolume(double w, double h, double d) {
        return w * h * d;
    }
}
