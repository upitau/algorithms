package com.upit.algo.other;

import java.util.Comparator;

// todo: complete
public class GrahamScan {

    public void findConvexHull(Point2D[] points) {

    }

    public static class Point2D {
        private double x;
        private double y;;

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public static Comparator<Point2D> Y_COMPARATOR = new Comparator<Point2D>() {
            @Override
            public int compare(Point2D p1, Point2D p2) {
                return (int) (p1.y - p2.y);
            }
        };

        public Comparator<Point2D> POLAR_COMPARATOR = new Comparator<Point2D>() {
            @Override
            public int compare(Point2D p1, Point2D p2) {
                return (int) (p1.y - p2.y);
            }
        };
    }
}
