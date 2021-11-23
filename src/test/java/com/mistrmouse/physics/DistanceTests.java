package com.mistrmouse.physics;

import org.junit.Assert;
import org.junit.Test;

public class DistanceTests {
    @Test
    public void encompassingTest() {
        // Solve for between points on x,y
        // and x,y,z given with a sphere or cube
        // and for one object rx90, rx90 & ry90,
        // and rx90 & ry90 & rz90
        // x1 = 5
        // y1 = 0
        // z1 = 5
        // x2 = 0
        // y2 = 0
        // z2 = 5
        // For Both:
        // r = 2
        // w = 4
        // h = 2
        // d = 4

        double r = 2, w = 4, h = 2, d = 4, x1 = 5, y1 = 0, z1 = 5, x2 = 0, y2 = 0, z2 = 5;
        double d2d = Distance.betweenPoints(x1, y1, x2, y2);
        double d3d = Distance.betweenPoints(x1, y1, z1, x2, y2, z1);
        double dbss2d = Distance.betweenCircles(r, x1, y1, r, x2, y2);
        double dbss3d = Distance.betweenSpheres(r, x1, y1, z1, r, x2, y2, z2);
        double dbcs2d = Distance.betweenSquares(w, h, x1, y1, 0, w, h, x2, y2, 0);
        double dbcs3d = Distance.betweenCubes(w, h, d, x1, y1, z1, 0, 0, 0, w, h, d, x2, y2, z2, 0, 0, 0);
        double dbcsrx902d = Distance.betweenSquares(w, h, x1, y1, 90, w, h, x2, y2, 0);
        double dbcsrx903d = Distance.betweenCubes(w, h, d, x1, y1, z1, 90, 0, 0, w, h, d, x2, y2, z2, 0, 0, 0);
        double dbcsrx90y903d = Distance.betweenCubes(w, h, d, x1, y1, z1, 90, 90, 0, w, h, d, x2, y2, z2, 0, 0, 0);
        double dbcsrx90y90z903d = Distance.betweenCubes(w, h, d, x1, y1, z1, 90, 90, 90, w, h, d, x2, y2, z2, 0, 0, 0);
        double dbcsss2d = Distance.betweenSquareAndCircle(w, h, x1, y1, 0, r, x2, y2);
        double dbcsss3d = Distance.betweenCubeAndSphere(w, h, d, x1, y1, z1, 0, 0, 0, r, x2, y2, z2);
        double dbcsssrx902d = Distance.betweenSquareAndCircle(w, h, x1, y1, 90, r, x2, y2);
        double dbcsssrx903d = Distance.betweenCubeAndSphere(w, h, d, x1, y1, z1, 90, 0, 0, r, x2, y2, z2);
        double dbcsssrx90y903d = Distance.betweenCubeAndSphere(w, h, d, x1, y1, z1, 90, 90, 0, r, x2, y2, z2);
        double dbcsssrx90y90z903d = Distance.betweenCubeAndSphere(w, h, d, x1, y1, z1, 90, 90, 90, r, x2, y2, z2);
        System.out.println("Distance 2D: " + d2d + "\nDistance 3D: " + d3d);
        System.out.println("Distance Between Circle Surfaces: " + dbss2d + "\nDistance Between Sphere Surfaces: " + dbss3d);
        System.out.println("Distance Between Square Surfaces: " + dbcs2d + "\nDistance Between Cube Surfaces: " + dbcs3d);
        System.out.println("Distance Between Square and Circle Surfaces: " + dbcsss2d + "\nDistance Between Cube and Sphere Surfaces: " + dbcsss3d);
        System.out.println("Distance Between Square Surfaces, One xRotated 90: " + dbcsrx902d + "\nDistance Between Cube Surfaces, One xRotated 90: " + dbcsrx903d);
        System.out.println("Distance Between Square and Circle Surfaces, One xRotated 90: " + dbcsssrx902d + "\nDistance Between Cube and Sphere Surfaces, One xRotated 90: " + dbcsssrx903d);
        System.out.println("Distance Between Cube Surfaces, One xRotated 90 and yRotated 90: " + dbcsrx90y903d);
        System.out.println("Distance Between Cube and Sphere Surfaces, One xRotated 90 and yRotated 90: " + dbcsssrx90y903d);
        System.out.println("Distance Between Cube Surfaces, One xRotated 90 and yRotated 90 and zRotated 90: " + dbcsrx90y90z903d);
        System.out.println("Distance Between Cube and Sphere Surfaces, One xRotated 90 and yRotated 90 and zRotated 90: " + dbcsssrx90y90z903d);

        Assert.assertEquals(5, d2d, 0);
        Assert.assertEquals(5, d3d, 0);
        Assert.assertEquals(1, dbss2d, 0);
        Assert.assertEquals(1, dbss3d, 0);
        Assert.assertEquals(1, dbcs2d, 0);
        Assert.assertEquals(1, dbcs3d, 0);
        Assert.assertEquals(1, dbcsss2d, 0);
        Assert.assertEquals(1, dbcsss3d, 0);
        Assert.assertEquals(2, dbcsrx902d, 0);
        Assert.assertEquals(2, dbcsrx903d, 0);
        Assert.assertEquals(2, dbcsssrx902d, 0);
        Assert.assertEquals(2, dbcsssrx903d, 0);
        Assert.assertEquals(2, dbcsrx90y903d, 0);
        Assert.assertEquals(2, dbcsssrx90y903d, 0);
        Assert.assertEquals(1, dbcsrx90y90z903d, 0);
        Assert.assertEquals(1, dbcsssrx90y90z903d, 0);
    }
}
