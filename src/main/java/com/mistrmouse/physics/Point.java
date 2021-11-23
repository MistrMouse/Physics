package com.mistrmouse.physics;

import java.util.ArrayList;
import java.util.List;

public class Point {
    public double x, y, z;

    public static List<Point> getSquareList(double w, double h, double cx, double cy, double theta) {
        double hW = w / 2;
        double hH = h / 2;
        List<Point> points = new ArrayList<>();
        points.add(Point.rotate(cx, cy, cx + hW, cy + hH, theta));
        points.add(Point.rotate(cx, cy, cx + hW, cy - hH, theta));
        points.add(Point.rotate(cx, cy, cx - hW, cy + hH, theta));
        points.add(Point.rotate(cx, cy, cx - hW, cy - hH, theta));
        return points;
    }

    public static List<Point> getCubicList(double w, double h, double d, double cx, double cy, double cz, double thetaX, double thetaY, double thetaZ) {
        double hW = w / 2;
        double hH = h / 2;
        double hD = d / 2;
        List<Point> points = new ArrayList<>();
        points.add(Point.rotate(cx, cy, cz, cx + hW, cy + hH, cz + hD, thetaX, thetaY, thetaZ));
        points.add(Point.rotate(cx, cy, cz, cx + hW, cy - hH, cz - hD, thetaX, thetaY, thetaZ));
        points.add(Point.rotate(cx, cy, cz, cx + hW, cy + hH, cz - hD, thetaX, thetaY, thetaZ));
        points.add(Point.rotate(cx, cy, cz, cx + hW, cy - hH, cz + hD, thetaX, thetaY, thetaZ));
        points.add(Point.rotate(cx, cy, cz, cx - hW, cy + hH, cz + hD, thetaX, thetaY, thetaZ));
        points.add(Point.rotate(cx, cy, cz, cx - hW, cy - hH, cz + hD, thetaX, thetaY, thetaZ));
        points.add(Point.rotate(cx, cy, cz, cx - hW, cy + hH, cz - hD, thetaX, thetaY, thetaZ));
        points.add(Point.rotate(cx, cy, cz, cx - hW, cy - hH, cz - hD, thetaX, thetaY, thetaZ));
        return points;
    }

    public static Point rotate(double cx, double cy, double x, double y, double theta) {
        double rad = Math.toRadians(theta);
        double sin = Math.sin(rad);
        double cos = Math.cos(rad);
        double originX = x - cx;
        double originY = y - cy;

        double xRotated = originX * cos - originY * sin;
        double yRotated = originX * sin + originY * cos;

        Point point = new Point();
        point.x = xRotated + cx;
        point.y = yRotated + cy;
        return point;
    }

    public static Point rotate(double cx, double cy, double cz, double x, double y, double z, double thetaX, double thetaY, double thetaZ) {
        double radX = Math.toRadians(thetaX);
        double radY = Math.toRadians(thetaY);
        double radZ= Math.toRadians(thetaZ);
        double sinX = Math.sin(radX);
        double cosX = Math.cos(radX);
        double sinY = Math.sin(radY);
        double cosY = Math.cos(radY);
        double sinZ = Math.sin(radZ);
        double cosZ = Math.cos(radZ);
        double originX = x - cx;
        double originY = y - cy;
        double originZ = z - cz;

        double xRotated = originX * cosX - originY * sinX;
        double yRotated = originX * sinX + originY * cosX;

        originY = yRotated;
        yRotated = originY * cosY - originZ * sinY;
        double zRotated = originY * sinY + originZ * cosY;

        originZ = zRotated;
        originX = xRotated;
        zRotated = originZ * cosZ - originX * sinZ;
        xRotated = originZ * sinZ + originX * cosZ;

        Point point = new Point();
        point.x = xRotated + cx;
        point.y = yRotated + cy;
        point.z = zRotated + cz;
        return point;
    }
}
