package com.mistrmouse.physics.visual;

import com.mistrmouse.physics.GravitationalForce;
import com.mistrmouse.physics.PhysicsObject2D;
import com.mistrmouse.physics.objects.Circle;
import com.mistrmouse.physics.objects.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VisualTester {
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

        circles.add(new Circle(10, 0, 0, 0));
        circles.add(new Circle(100, jFrame.getSize().getWidth() / 2.0 - 100, jFrame.getSize().getHeight() / 2.0 - 100, 0));
        squares.add(new Square(10, 10, 10, 10, 45));
        final int FPS_60 = 1000 / 60;
        while (jFrame.isValid()) {
            long start = System.currentTimeMillis();
            jFrame.repaint();
            long delay = System.currentTimeMillis() - start;
            Thread.sleep(delay > FPS_60 ? 0 : (FPS_60 - delay));
        }
    }

    private static class DrawingComponent extends JComponent {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            int counter = 1;
            for (Square s1 : squares) {
                g2.rotate(s1.getTheta(), s1.getCx(), s1.getCy());
                g2.drawRect((int) s1.getCx(), (int) s1.getCy(), (int) s1.getW(), (int) s1.getH());

                for (int i = counter; i < squares.size(); i++) {
                    Square s2 = squares.get(i);
                    boolean collides = s1.detectCollision(s2);
                    if (!collides) {
                        applyGravity(s1, s2);
                    }
                }

                counter++;
            }

            counter = 1;
            for (Circle c1 : circles) {
                g2.rotate(c1.getTheta(), c1.getCx(), c1.getCy());
                g2.drawOval((int) c1.getCx(), (int) c1.getCy(), (int) c1.getR(), (int) c1.getR());

                for (int i = counter; i < circles.size(); i++) {
                    Circle c2 = circles.get(i);
                    boolean collides = c1.detectCollision(c2);
                    if (!collides) {
                        applyGravity(c1, c2);
                    }
                }

                for (int i = counter; i < squares.size(); i++) {
                    Square s2 = squares.get(i);
                    boolean collides = c1.detectCollision(s2);
                    if (!collides) {
                        applyGravity(c1, s2);
                    }
                }

                counter++;
            }
        }

        private void applyGravity(PhysicsObject2D po1, PhysicsObject2D po2) {
            GravitationalForce.calculate(po1);
        }
    }
}
