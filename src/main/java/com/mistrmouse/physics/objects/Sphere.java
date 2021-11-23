package com.mistrmouse.physics.objects;

import com.mistrmouse.physics.Distance;
import com.mistrmouse.physics.PhysicsObject3D;

public class Sphere extends PhysicsObject3D {
    private double r;

    public Sphere(double r, double cx, double cy, double cz, double thetaX, double thetaY, double thetaZ) {
        super(cx, cy, cz, thetaX, thetaY, thetaZ);
        this.r = r;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean detectCollision(Sphere s) {
        return Distance.betweenSpheres(r, getCx(), getCy(), getCz(), s.getR(), s.getCx(), s.getCy(), s.getCz()) < 0;
    }

    public boolean detectCollision(Cube c) {
        return c.detectCollision(this);
    }
}
