package com.mistrmouse.physics;

public class GravitationalForce {
    // Gravitational Force is force exerted between two objects
    // This class is used to help calculate the gravitational force
    // between two objects, as understood when using the Gravitational Constant

    public static final double G = 6.67430 * Math.pow(10, -11); // N·m²/kg²

    // Main calculation to determine Gravitational Force between two objects
    // Fgravity = GMm/d²
    // G = Gravitational Constant
    // M, m = mass of two objects
    // d = distance between the centers of the two objects
    public static double calculate(double M, double m, double d) {
        return G * M * m / Math.pow(d, 2); // N
    }

    // Account for rearranged equations
    public static double calculateDistance(double gForce, double M, double m) {
        return Math.sqrt(G * M * m / gForce); // m
    }

    public static double calculateMass(double gForce, double m, double d) {
        return gForce * Math.pow(d, 2) / (G * m); // kg
    }

    // Calculation to determine Gravitational Acceleration towards an object
    // a = Fgravity / m
    // Fgravity = Gravitational Force
    // m = mass of the object
    public static double calculateAcceleration(double gForce, double m) {
        return gForce / m; // m/s²
    }

    // Account for rearranged equations
    public static double calculate(double gAcceleration, double m) {
        return m * gAcceleration; // N
    }
}
