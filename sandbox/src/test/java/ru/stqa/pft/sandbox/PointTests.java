package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistanceWithPositiveValues() {
        Point p1 = new Point(4,2);
        Point p2 = new Point(8,5);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDistanceWithNegativeValues() {
        Point p1 = new Point(-4,-2);
        Point p2 = new Point(-8,-5);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDistanceWithPositiveAndNegativeValues() {
        Point p1 = new Point(-2,-2);
        Point p2 = new Point(2,1);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDistanceWithZeroValues() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(3,4);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDistanceWithSameValues() {
        Point p1 = new Point(2,2);
        Point p2 = new Point(2,2);

        Assert.assertEquals(p1.distance(p2), 0.0);
    }
}
