package com.mistrmouse.physics;

import java.util.ArrayList;
import java.util.List;

public class Distance {
    // Computing distance can be done in three-dimensional
    // or two-dimensional aspects. This is the determination
    // of x,y or x,y,z

    // Distance uses the same concept of Pythagorean Theorem
    //  a^2 + b^2 = c^2
    //  a = Δx = x1 - x2
    //  b = Δy = y1 - y2
    //  c = distance
    // If we add another dimension, we simply add another point
    //  a^2 + b^2 + c^2 = d^2
    //  a = Δx = x1 - x2
    //  b = Δy = y1 - y2
    //  c = Δz = z1 - z2
    //  d = distance
    public static double betweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static double betweenPoints(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
    }

    // Points typically reference the center of an object, so
    // if we want to get the distance to an object we would have to
    // account for the surface. If the distance returned is negative,
    // this means the objects are inside each other, while 0 means they
    // are touching. This is easy to do with spheres, as we just take
    // the radius of both objects and subtract the sum of both from
    // the distance between the points.
    public static double betweenSpheres(double r1, double cx1, double cy1, double r2, double cx2, double cy2) {
        return betweenPoints(cx1, cy1, cx2, cy2) - (r1 + r2);
    }

    public static double betweenSpheres(double r1, double cx1, double cy1, double cz1, double r2, double cx2, double cy2, double cz2) {
        return betweenPoints(cx1, cy1, cz1, cx2, cy2, cz2) - (r1 + r2);
    }

    // When dealing with squares or cubes, determining distance changes
    // as we have to factor in angles as well as the min and max width
    // and height as well as depth when in the third-dimension. However,
    // we still follow the same format of getting the distance between the
    // points and then subtracting the sum of the min and maxes according
    // to location of each other
    public static double betweenSquares(double w1, double h1, double cx1, double cy1, double theta1, double w2, double h2, double cx2, double cy2, double theta2) {
        double distance = betweenPoints(cx1, cy1, cx2, cy2);
        double hW = w1 / 2;
        double hH = h1 / 2;
        List<Point> sqr1Points = new ArrayList<>();
        sqr1Points.add(rotatePoint(cx1 + hW, cy1 + hH, theta1));
        sqr1Points.add(rotatePoint(cx1 - hW, cy1 + hH, theta1));
        sqr1Points.add(rotatePoint(cx1 + hW, cy1 - hH, theta1));
        sqr1Points.add(rotatePoint(cx1 - hW, cy1 - hH, theta1));

        hW = w2 / 2;
        hH = h2 / 2;
        List<Point> sqr2Points = new ArrayList<>();
        sqr2Points.add(rotatePoint(cx2 + hW, cy2 + hH, theta2));
        sqr2Points.add(rotatePoint(cx2 - hW, cy2 + hH, theta2));
        sqr2Points.add(rotatePoint(cx2 + hW, cy2 - hH, theta2));
        sqr2Points.add(rotatePoint(cx2 - hW, cy2 - hH, theta2));

        Point sqrPoint = sqr1Points.get(0);
        double minX1 = sqrPoint.x, minY1 = sqrPoint.y, maxX1 = minX1, maxY1 = minY1;
        sqrPoint = sqr2Points.get(0);
        double minX2 = sqrPoint.x, minY2 = sqrPoint.y, maxX2 = minX2, maxY2 = minY2;
        for (int i = 1; i < 4; i++) {
            sqrPoint = sqr1Points.get(i);
            minX1 = Math.min(minX1, sqrPoint.x);
            minY1 = Math.min(minY1, sqrPoint.y);
            maxX1 = Math.max(maxX1, sqrPoint.x);
            maxY1 = Math.max(maxY1, sqrPoint.y);

            sqrPoint = sqr2Points.get(i);
            minX2 = Math.min(minX2, sqrPoint.x);
            minY2 = Math.min(minY2, sqrPoint.y);
            maxX2 = Math.max(maxX2, sqrPoint.x);
            maxY2 = Math.max(maxY2, sqrPoint.y);
        }

        boolean isLeft = (cx1 < cx2);
        boolean isBelow = (cy1 < cy2);
        double diffX = (isLeft) ? (maxX1 - cx1 + (cx2 - minX2)) : ((cx1 != cx2) ? (cx1 - minX1 + (maxX1 - cy2)) : 0);
        double diffY = (isBelow) ? (maxY1 - cy1 + (cy2 - minY2)) : ((cy1 != cy2) ? (cy1 - minY1 + (maxY2 - cy2)) : 0);
        double diff = RightAngledTriangle.solveForHypotenuse(diffX, diffY);
        return distance - diff;
    }

    private static Point rotatePoint(double x, double y, double theta) {
        Point point = new Point();
        point.x = x * Math.cos(theta) - y * Math.sin(theta);
        point.y = x * Math.sin(theta) + y * Math.cos(theta);
        return point;
    }

    public static double betweenCubes(double w1, double h1, double d1, double cx1, double cy1, double cz1, double thetaX1, double thetaY1, double thetaZ1, double w2, double h2, double d2, double cx2, double cy2, double cz2, double thetaX2, double thetaY2, double thetaZ2) {
        double distance = betweenPoints(cx1, cy1, cz1, cx2, cy2, cz2);
        double hW = w1 / 2;
        double hH = h1 / 2;
        double hD = d1 / 2;
        List<Point> sqr1Points = new ArrayList<>();
        sqr1Points.add(rotatePoint(cx1 + hW, cy1 + hH, cz1 + hD, thetaX1, thetaY1, thetaZ1));
        sqr1Points.add(rotatePoint(cx1 + hW, cy1 - hH, cz1 - hD, thetaX1, thetaY1, thetaZ1));
        sqr1Points.add(rotatePoint(cx1 + hW, cy1 + hH, cz1 - hD, thetaX1, thetaY1, thetaZ1));
        sqr1Points.add(rotatePoint(cx1 + hW, cy1 - hH, cz1 + hD, thetaX1, thetaY1, thetaZ1));
        sqr1Points.add(rotatePoint(cx1 - hW, cy1 + hH, cz1 + hD, thetaX1, thetaY1, thetaZ1));
        sqr1Points.add(rotatePoint(cx1 - hW, cy1 - hH, cz1 + hD, thetaX1, thetaY1, thetaZ1));
        sqr1Points.add(rotatePoint(cx1 - hW, cy1 + hH, cz1 - hD, thetaX1, thetaY1, thetaZ1));
        sqr1Points.add(rotatePoint(cx1 - hW, cy1 - hH, cz1 - hD, thetaX1, thetaY1, thetaZ1));

        hW = w2 / 2;
        hH = h2 / 2;
        hD = d2 / 2;
        List<Point> sqr2Points = new ArrayList<>();
        sqr2Points.add(rotatePoint(cx2 + hW, cy2 + hH, cz2 + hD, thetaX2, thetaY2, thetaZ2));
        sqr2Points.add(rotatePoint(cx2 + hW, cy2 - hH, cz2 - hD, thetaX2, thetaY2, thetaZ2));
        sqr2Points.add(rotatePoint(cx2 + hW, cy2 + hH, cz2 - hD, thetaX2, thetaY2, thetaZ2));
        sqr2Points.add(rotatePoint(cx2 + hW, cy2 - hH, cz2 + hD, thetaX2, thetaY2, thetaZ2));
        sqr2Points.add(rotatePoint(cx2 - hW, cy2 + hH, cz2 + hD, thetaX2, thetaY2, thetaZ2));
        sqr2Points.add(rotatePoint(cx2 - hW, cy2 - hH, cz2 + hD, thetaX2, thetaY2, thetaZ2));
        sqr2Points.add(rotatePoint(cx2 - hW, cy2 + hH, cz2 - hD, thetaX2, thetaY2, thetaZ2));
        sqr2Points.add(rotatePoint(cx2 - hW, cy2 - hH, cz2 - hD, thetaX2, thetaY2, thetaZ2));

        Point sqrPoint = sqr1Points.get(0);
        double minX1 = sqrPoint.x, minY1 = sqrPoint.y, minZ1 = sqrPoint.z, maxX1 = minX1, maxY1 = minY1, maxZ1 = minZ1;
        sqrPoint = sqr2Points.get(0);
        double minX2 = sqrPoint.x, minY2 = sqrPoint.y, minZ2 = sqrPoint.z, maxX2 = minX2, maxY2 = minY2, maxZ2 = minZ2;
        for (int i = 1; i < 4; i++) {
            sqrPoint = sqr1Points.get(i);
            minX1 = Math.min(minX1, sqrPoint.x);
            minY1 = Math.min(minY1, sqrPoint.y);
            minZ1 = Math.min(minZ1, sqrPoint.z);
            maxX1 = Math.max(maxX1, sqrPoint.x);
            maxY1 = Math.max(maxY1, sqrPoint.y);
            maxZ1 = Math.max(maxZ1, sqrPoint.z);

            sqrPoint = sqr2Points.get(i);
            minX2 = Math.min(minX2, sqrPoint.x);
            minY2 = Math.min(minY2, sqrPoint.y);
            minZ2 = Math.min(minZ2, sqrPoint.z);
            maxX2 = Math.max(maxX2, sqrPoint.x);
            maxY2 = Math.max(maxY2, sqrPoint.y);
            maxZ2 = Math.max(maxZ2, sqrPoint.z);
        }

        boolean isLeft = (cx1 < cx2);
        boolean isBelow = (cy1 < cy2);
        boolean isBehind = (cz1 < cz2);
        double diffX = (isLeft) ? (maxX1 - cx1 + (cx2 - minX2)) : ((cx1 != cx2) ? (cx1 - minX1 + (maxX1 - cx2)) : 0);
        double diffY = (isBelow) ? (maxY1 - cy1 + (cy2 - minY2)) : ((cy1 != cy2) ? (cy1 - minY1 + (maxY2 - cy2)) : 0);
        double diffZ = (isBehind) ? (maxZ1 - cz1 + (cz2 - minZ2)) : ((cz1 != cz2) ? (cz1 - minZ1 + (maxZ2 - cz2)) : 0);
        double diff = betweenPoints(0, 0, 0, diffX, diffY, diffZ);
        return distance - diff;
    }

    private static Point rotatePoint(double x, double y, double z, double thetaX, double thetaY, double thetaZ) {
        Point point = new Point();
        point.x = x * Math.cos(thetaX) - y * Math.sin(thetaX);
        point.y = x * Math.sin(thetaX) + y * Math.cos(thetaX);
        point.z = z;

        y = point.y;
        point.y = y * Math.cos(thetaY) - point.z * Math.sin(thetaY);
        point.z = y * Math.sin(thetaY) + point.z * Math.cos(thetaY);

        z = point.z;
        point.z = z * Math.cos(thetaZ) - point.x * Math.sin(thetaZ);
        point.x = z * Math.sin(thetaZ) + point.x * Math.cos(thetaZ);
        return point;
    }
}
