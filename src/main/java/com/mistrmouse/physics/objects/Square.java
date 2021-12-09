package com.mistrmouse.physics.objects;

import com.mistrmouse.physics.Distance;
import com.mistrmouse.physics.PhysicsObject2D;

public class Square extends PhysicsObject2D {
    private double w, h;
    public Square(double density, double w, double h, double cx, double cy, double theta) {
        super(calculateArea(w, h), density, cx, cy, theta);
        this.w = w;
        this.h = h;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public void setW(double w) {
        this.w = w;
    }

    public void setH(double h) {
        this.h = h;
    }

    public boolean detectCollision(Circle c) {
        return Distance.betweenSquareAndCircle(w, h, getCx(), getCy(), getTheta(), c.getR(), c.getCx(), c.getCy()) < 0;
    }

    public boolean detectCollision(Square s) {
        return Distance.betweenSquares(w, h, getCx(), getCy(), getTheta(), s.getW(), s.getH(), s.getCx(), s.getCy(), s.getTheta()) < 0;
    }

    public static double calculateArea(double w, double h) {
        return w * h;
    }
}
