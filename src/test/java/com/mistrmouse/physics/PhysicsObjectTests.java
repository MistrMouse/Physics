package com.mistrmouse.physics;

import com.mistrmouse.physics.objects.Circle;
import com.mistrmouse.physics.objects.Cube;
import com.mistrmouse.physics.objects.Sphere;
import com.mistrmouse.physics.objects.Square;
import org.junit.Assert;
import org.junit.Test;

public class PhysicsObjectTests {
    @Test
    public void encompassingTest() {
        // Test object interaction and methods
        double w = 4, h = 4, d = 4, r = 2, cx = 1, cy = 2, cz = 3, theta = 90;
        Circle circle1 = new Circle(0, 0, 0, 0, 0), circle2 = new Circle(0, r, 0, 0, 0);
        Square square1 = new Square(0, 0, 0, 0, 0, 0), square2 = new Square(0, w, h, 0, 0, 0);
        Sphere sphere1 = new Sphere(0, 0, 0, 0, 0, 0, 0, 0), sphere2 = new Sphere(0, r, 0, 0, 0, 0, 0, 0);
        Cube cube1 = new Cube(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), cube2 = new Cube(0, w, h, d, 0, 0, 0, 0, 0, 0);

        // Test getters and setters
        circle1.setCx(cx);
        circle1.setCy(cy);
        circle1.setR(r);
        circle1.setTheta(theta);
        square1.setCx(cx);
        square1.setCy(cy);
        square1.setW(w);
        square1.setH(h);
        square1.setTheta(theta);
        sphere1.setCx(cx);
        sphere1.setCy(cy);
        sphere1.setCz(cz);
        sphere1.setR(r);
        sphere1.setThetaX(theta);
        sphere1.setThetaY(theta);
        sphere1.setThetaZ(theta);
        cube1.setCx(cx);
        cube1.setCy(cy);
        cube1.setCz(cz);
        cube1.setW(w);
        cube1.setH(h);
        cube1.setD(d);
        cube1.setThetaX(theta);
        cube1.setThetaY(theta);
        cube1.setThetaZ(theta);

        // Assertions
        Assert.assertEquals(cx, circle1.getCx(), 0);
        Assert.assertEquals(cy, circle1.getCy(), 0);
        Assert.assertEquals(r, circle1.getR(), 0);
        Assert.assertEquals(theta, circle1.getTheta(), 0);
        Assert.assertEquals(cx, square1.getCx(), 0);
        Assert.assertEquals(cy, square1.getCy(), 0);
        Assert.assertEquals(w, square1.getW(), 0);
        Assert.assertEquals(h, square1.getH(), 0);
        Assert.assertEquals(theta, square1.getTheta(), 0);
        Assert.assertEquals(cx, sphere1.getCx(), 0);
        Assert.assertEquals(cy, sphere1.getCy(), 0);
        Assert.assertEquals(cz, sphere1.getCz(), 0);
        Assert.assertEquals(r, sphere1.getR(), 0);
        Assert.assertEquals(theta, sphere1.getThetaX(), 0);
        Assert.assertEquals(theta, sphere1.getThetaY(), 0);
        Assert.assertEquals(theta, sphere1.getThetaZ(), 0);
        Assert.assertEquals(cx, cube1.getCx(), 0);
        Assert.assertEquals(cy, cube1.getCy(), 0);
        Assert.assertEquals(cz, cube1.getCz(), 0);
        Assert.assertEquals(w, cube1.getW(), 0);
        Assert.assertEquals(h, cube1.getH(), 0);
        Assert.assertEquals(d, cube1.getD(), 0);
        Assert.assertEquals(theta, cube1.getThetaX(), 0);
        Assert.assertEquals(theta, cube1.getThetaY(), 0);
        Assert.assertEquals(theta, cube1.getThetaZ(), 0);
        Assert.assertTrue(circle1.detectCollision(circle2));
        Assert.assertTrue(circle1.detectCollision(square2));
        Assert.assertTrue(square1.detectCollision(square2));
        Assert.assertTrue(sphere1.detectCollision(sphere2));
        Assert.assertTrue(sphere1.detectCollision(cube2));
        Assert.assertTrue(cube1.detectCollision(cube2));
    }
}
