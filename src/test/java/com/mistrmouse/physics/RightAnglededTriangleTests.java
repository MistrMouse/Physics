package com.mistrmouse.physics;

import org.junit.Assert;
import org.junit.Test;

public class RightAnglededTriangleTests {
    @Test
    public void encompassingTest() {
        // Solve for all sides and angles given:
        // a = 3
        // c = 5
        double a = 3, c = 5;
        double b = RightAngledTriangle.solveForSide(a, c);
        double thetaA = RightAngledTriangle.solveForThetaUsingInverseSin(a, c);
        double thetaB = RightAngledTriangle.solveForThetaUsingInverseCos(a, c);
        System.out.println("SIDE B: " + b + "\nTheta A: " + thetaA + "\nTheta B: " + thetaB);

        // Assertions
        Assert.assertEquals(RightAngledTriangle.solveForMissingAngle(thetaA), thetaB, .0001);
        Assert.assertEquals(RightAngledTriangle.solveForThetaUsingInverseTan(a, b), thetaA, 0);
        Assert.assertEquals(c, RightAngledTriangle.solveForHypotenuse(a, b), 0);
        Assert.assertEquals(a, RightAngledTriangle.solveForOppositeUsingSin(thetaA, c), 0);
        Assert.assertEquals(a, RightAngledTriangle.solveForOppositeUsingTan(thetaA, b), 0);
        Assert.assertEquals(b, RightAngledTriangle.solveForAdjacentUsingCos(thetaA, c), 0);
        Assert.assertEquals(b, RightAngledTriangle.solveForAdjacentUsingTan(thetaA, a), 0);
        Assert.assertEquals(c, RightAngledTriangle.solveForHypotenuseUsingSin(thetaA, a), 0);
        Assert.assertEquals(c, RightAngledTriangle.solveForHypotenuseUsingCos(thetaA, b), 0);
    }
}
