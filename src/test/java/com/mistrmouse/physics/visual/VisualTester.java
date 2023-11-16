package com.mistrmouse.physics.visual;

import com.mistrmouse.physics.Distance;
import com.mistrmouse.physics.GravitationalForce;
import com.mistrmouse.physics.PhysicsObject2D;
import com.mistrmouse.physics.RightAngledTriangle;
import com.mistrmouse.physics.objects.Circle;
import com.mistrmouse.physics.objects.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class VisualTester {
    private static final double MSPF_60 = 1000.0 / 60.0;
    private static final double SPF_60 = 1.0 / 60.0;
    private static double UNIVERSE_ZOOM = 31.25;
    private static double VIEW_X = 0;
    private static double VIEW_Y = 204000;

    private static JFrame jFrame;
    private static List<Square> squares = new ArrayList<>();
    private static List<Circle> circles = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Loading Visual Tester Suite!");
        System.out.println("NOTE: This should only be used for local testing purposes");

        jFrame = new JFrame("Visual Test");
        DrawingComponent drawingComponent = new DrawingComponent();
        jFrame.setContentPane(drawingComponent);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension maxSize = new Dimension();
        maxSize.setSize(1280, 1920);
        jFrame.setMaximumSize(maxSize);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setVisible(true);
        double earthMass = 5.9722 * Math.pow(10, 24);
        double earthRadius = 6371000;
        double earthArea = Circle.calculateArea(earthRadius);
        double earthDensity = earthMass / earthArea;

        double moonMass = 7.34767309 * Math.pow(10, 22);
        double moonRadius = 1737400;
        double moonArea = Circle.calculateArea(moonRadius);
        double moonDensity = moonMass / moonArea;

        // Random stuff in galaxy
        circles.add(new Circle(moonDensity, moonRadius, 0, -earthRadius - moonRadius - 1000, 0));
        // Earth
        circles.add(new Circle(earthDensity, earthRadius, 0, 0, 0));

        while (jFrame.isValid()) {
            long start = System.currentTimeMillis();
            jFrame.repaint();
            long delay = System.currentTimeMillis() - start;
            Thread.sleep(delay > MSPF_60 ? 0 : (int) (MSPF_60 - delay));
        }
    }

    private static class DrawingComponent extends JComponent {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Dimension fSize = jFrame.getSize();
            double hW = fSize.getWidth() / 2 + VIEW_X;
            double hH = fSize.getHeight() / 2 + VIEW_Y;
            AffineTransform old = g2.getTransform();
            for (Square s1 : squares) {
                int w = (int) (s1.getW() / UNIVERSE_ZOOM);
                int h = (int) (s1.getH() / UNIVERSE_ZOOM);
                int x = (int) ((s1.getCx() / UNIVERSE_ZOOM) - (w / 2) + hW);
                int y = (int) ((s1.getCy() / UNIVERSE_ZOOM) - (h / 2) + hH);
                g2.rotate(Math.toRadians(s1.getTheta()), x, y);
                g2.drawRect(x, y, w, h);
                g2.setTransform(old);
            }

            for (int i = 0; i < circles.size(); i++) {
                Circle c1 = circles.get(i);
                int r = (int) (c1.getR() / UNIVERSE_ZOOM);
                int x = (int) ((c1.getCx() / UNIVERSE_ZOOM) - r + hW);
                int y = (int) ((c1.getCy() / UNIVERSE_ZOOM) - r + hH);
                g2.rotate(Math.toRadians(c1.getTheta()), x, y);
                g2.drawOval(x, y, r * 2, r * 2);
                g2.setTransform(old);

                for (int ii = i + 1; ii < circles.size(); ii++) {
                    Circle c2 = circles.get(ii);
                    boolean collides = c1.detectCollision(c2);
                    if (!collides) {
                        applyGravity(c1, c2);
                    }
                }

                for (Square s2 : squares) {
                    boolean collides = c1.detectCollision(s2);
                    if (!collides) {
                        applyGravity(c1, s2);
                    }
                }
            }
        }

        private void applyGravity(PhysicsObject2D po1, PhysicsObject2D po2) {
            double gForce = GravitationalForce.calculate(po1.mass, po2.mass, Distance.betweenPoints(po1.getCx(), po1.getCy(), po2.getCx(), po2.getCy()));
            double a = GravitationalForce.calculateAcceleration(gForce, po1.mass);
            double relationX = po1.getCx() - po2.getCx();
            double relationY = po1.getCy() - po2.getCy();
            double thetaA = RightAngledTriangle.solveForThetaUsingInverseTan(relationY, relationX);
            double thetaB = RightAngledTriangle.solveForMissingAngle(thetaA);
            double ay = RightAngledTriangle.solveForOppositeUsingSin(thetaA, a);
            double ax = RightAngledTriangle.solveForOppositeUsingSin(thetaB, a);
            double velocityX = po1.getXVelocity() - ax * SPF_60;
            double velocityY = po1.getYVelocity() - ay * SPF_60;
            po1.setXVelocity(velocityX);
            po1.setYVelocity(velocityY);
            po1.setCx(po1.getCx() + velocityX * SPF_60);
            po1.setCy(po1.getCy() + velocityY * SPF_60);

            a = GravitationalForce.calculateAcceleration(gForce, po2.mass);
            ay = RightAngledTriangle.solveForOppositeUsingSin(thetaA, a);
            ax = RightAngledTriangle.solveForOppositeUsingSin(thetaB, a);
            velocityX = po2.getXVelocity() + ax * SPF_60;
            velocityY = po2.getYVelocity() + ay * SPF_60;
            po2.setXVelocity(velocityX);
            po2.setYVelocity(velocityY);
            po2.setCx(po2.getCx() + velocityX * SPF_60);
            po2.setCy(po2.getCy() + velocityY * SPF_60);
        }
    }
}
