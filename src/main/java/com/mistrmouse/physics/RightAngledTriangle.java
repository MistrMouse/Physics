package com.mistrmouse.physics;

public class RightAngledTriangle {
    // Right-angled triangles allow for the basic building blocks
    // in computation, keeping in mind a few different things.

    // Working with right-angled triangles follow the 180 degree
    // rule when dealing with angles. Where the sum of the internal
    // angles are always equal to 180, allowing for determination of
    // a missing angle if already having one.
    // 180 = 90 + theta1 + theta2
    // 90 = theta1 + theta2
    public static double solveForMissingAngle(double theta) {
        return 90 - theta;
    }

    // When dealing with right angles, then we can solve for
    // sides using Pythagorean Theorem
    //      /|
    //   c / | b     a² + b² = c²
    //    /__|
    //      a
    public static double solveForSide(double aORb, double c) {
        return Math.sqrt(Math.pow(c, 2) - Math.pow(aORb, 2));
    }

    public static double solveForHypotenuse(double a, double b) {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    // If we can break things down into right angles, then we
    // can solve for sides and angles using SOHCAHTOA
    // sin(θ) = opposite / hypotenuse
    // cos(θ) = adjacent / hypotenuse
    // tan(θ) = opposite / adjacent
    public static double solveForThetaUsingInverseSin(double opposite, double hypotenuse) {
        return Math.toDegrees(Math.asin(opposite / hypotenuse));
    }

    public static double solveForThetaUsingInverseCos(double adjacent, double hypotenuse) {
        return Math.toDegrees(Math.acos(adjacent / hypotenuse));
    }

    public static double solveForThetaUsingInverseTan(double opposite, double adjacent) {
        return Math.toDegrees(Math.atan(opposite / adjacent));
    }

    public static double solveForOppositeUsingSin(double theta, double hypotenuse) {
        return Math.sin(Math.toRadians(theta)) * hypotenuse;
    }

    public static double solveForOppositeUsingTan(double theta, double adjacent) {
        return Math.tan(Math.toRadians(theta)) * adjacent;
    }

    public static double solveForAdjacentUsingCos(double theta, double hypotenuse) {
        return Math.cos(Math.toRadians(theta)) * hypotenuse;
    }

    public static double solveForAdjacentUsingTan(double theta, double opposite) {
        return opposite / Math.tan(Math.toRadians(theta));
    }

    public static double solveForHypotenuseUsingSin(double theta, double opposite) {
        return opposite / Math.sin(Math.toRadians(theta));
    }

    public static double solveForHypotenuseUsingCos(double theta, double adjacent) {
        return adjacent / Math.cos(Math.toRadians(theta));
    }
}
