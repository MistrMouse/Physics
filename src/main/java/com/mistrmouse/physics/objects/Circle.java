package com.mistrmouse.physics.objects;

import com.mistrmouse.physics.Distance;
import com.mistrmouse.physics.PhysicsObject2D;

public class Circle extends PhysicsObject2D {
    private double r;

    public Circle(double density, double r, double cx, double cy, double theta) {
        super(r * r, density, cx, cy, theta);
        this.r = r;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean detectCollision(Circle c) {
        return Distance.betweenCircles(r, getCx(), getCy(), c.getR(), c.getCx(), c.getCy()) < 0;
    }

    public boolean detectCollision(Square s) {
        return s.detectCollision(this);
    }
}
