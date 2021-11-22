package com.mistrmouse.physics;

import org.junit.Assert;
import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class GravitationalForceTests {
    @Test
    public void encompassingTest() {
        // Solve for Gravitational Acceleration having:
        // Gravitational Force class constraints
        // m1 = 20kg
        // m2 = 10kg
        // d = 100m
        double m1 = 10, m2 = 20, d = 100;

        // Solve for Gravitational Force between two objects first
        double gForce = GravitationalForce.calculate(m2, m1, d);
        // Using gForce, solve m1 and m2 acceleration
        double m1Acceleration = GravitationalForce.calculateAcceleration(gForce, m2);
        double m2Acceleration = GravitationalForce.calculateAcceleration(gForce, m1);
        System.out.println("Gravitational Force: " + gForce + " N\nM Accelration: " + m1Acceleration + " m/s^2\nm Acceleration: " + m2Acceleration);

        // Assertions
        DecimalFormat df = new DecimalFormat("#.###############");
        df.setRoundingMode(RoundingMode.CEILING);
        Assert.assertEquals(.000000000001335, Double.parseDouble(df.format(gForce)), 0);
        Assert.assertEquals(.000000000000067, Double.parseDouble(df.format(m1Acceleration)), 0);
        Assert.assertEquals(.000000000000134, Double.parseDouble(df.format(m2Acceleration)), 0);
        Assert.assertEquals(d, GravitationalForce.calculateDistance(gForce, m2, m1), 0);
        Assert.assertEquals(m2, GravitationalForce.calculateMass(gForce, m1, d), .0001);
        Assert.assertEquals(m1, GravitationalForce.calculateMass(gForce, m2, d), .0001);
        Assert.assertEquals(gForce, GravitationalForce.calculate(m1Acceleration, m2), 0);
        Assert.assertEquals(gForce, GravitationalForce.calculate(m2Acceleration, m1), 0);
    }
}
