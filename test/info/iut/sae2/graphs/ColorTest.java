/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package info.iut.sae2.graphs;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author temet
 */
public class ColorTest {

    /**
     * Test of constructors of the class Color.
     */
    @Test
    public void testConstructors() {
        // Empty Constructor -> All components should be set to 0
        Color color = new Color();
        assertEquals(0, color.getR());
        assertEquals(0, color.getG());
        assertEquals(0, color.getB());
        assertEquals(0, color.getA());

        // Custom values
        color = new Color(22, 43, 12, 255);
        assertEquals(22, color.getR());
        assertEquals(43, color.getG());
        assertEquals(12, color.getB());
        assertEquals(255, color.getA());
    }

    /**
     * Test of getR method, of class Color.
     */
    @Test
    public void testGetR() {
        // r = 22
        Color color = new Color(22, 43, 12, 255);
        assertEquals(22, color.getR());

        // r = 0
        color = new Color(0, 12, 13, 0);
        assertEquals(0, color.getR());
    }

    /**
     * Test of getG method, of class Color.
     */
    @Test
    public void testGetG() {
        // g = 43
        Color color = new Color(22, 43, 12, 255);
        assertEquals(43, color.getG());

        //g = 12
        color = new Color(0, 12, 13, 0);
        assertEquals(12, color.getG());
    }

    /**
     * Test of getB method, of class Color.
     */
    @Test
    public void testGetB() {
        // b = 12
        Color color = new Color(22, 43, 12, 255);
        assertEquals(12, color.getB());

        // b = 13
        color = new Color(0, 12, 13, 0);
        assertEquals(13, color.getB());
    }

    /**
     * Test of getA method, of class Color.
     */
    @Test
    public void testGetA() {
        // a = 255
        Color color = new Color(22, 43, 12, 255);
        assertEquals(255, color.getA());

        // a = 0
        color = new Color(0, 12, 13, 0);
        assertEquals(0, color.getA());
    }

    /**
     * Test of setR method, of class Color. Also checks that the other
     * components of the color are not changed.
     */
    @Test
    public void testSetR() {
        Color color = new Color(0, 0, 0, 0);

        // r = 0 -> 255
        color.setR(255);
        assertEquals(255, color.getR());
        assertEquals(0, color.getG());
        assertEquals(0, color.getB());
        assertEquals(0, color.getA());

        // r = 255 -> 67
        color.setR(67);
        assertEquals(67, color.getR());
        assertEquals(0, color.getG());
        assertEquals(0, color.getB());
        assertEquals(0, color.getA());
    }

    /**
     * Test of setG method, of class Color. Also checks that the other
     * components of the color are not changed.
     */
    @Test
    public void testSetG() {
        Color color = new Color(0, 0, 0, 0);

        // g = 0 -> 255
        color.setG(255);
        assertEquals(255, color.getG());
        assertEquals(0, color.getR());
        assertEquals(0, color.getB());
        assertEquals(0, color.getA());

        // g = 255 -> 67
        color.setG(67);
        assertEquals(67, color.getG());
        assertEquals(0, color.getR());
        assertEquals(0, color.getB());
        assertEquals(0, color.getA());
    }

    /**
     * Test of setB method, of class Color. Also checks that the other
     * components of the color are not changed.
     */
    @Test
    public void testSetB() {
        Color color = new Color(0, 0, 0, 0);

        // b = 0 -> 255
        color.setB(255);
        assertEquals(255, color.getB());
        assertEquals(0, color.getR());
        assertEquals(0, color.getG());
        assertEquals(0, color.getA());

        // b = 255 -> 67
        color.setB(67);
        assertEquals(67, color.getB());
        assertEquals(0, color.getR());
        assertEquals(0, color.getG());
        assertEquals(0, color.getA());
    }

    /**
     * Test of setA method, of class Color. Also checks that the other
     * components of the color are not changed.
     */
    @Test
    public void testSetA() {
        Color color = new Color(0, 0, 0, 0);

        // a = 0 -> 255
        color.setA(255);
        assertEquals(255, color.getA());
        assertEquals(0, color.getR());
        assertEquals(0, color.getG());
        assertEquals(0, color.getB());

        // a = 255 -> 67
        color.setA(67);
        assertEquals(67, color.getA());
        assertEquals(0, color.getR());
        assertEquals(0, color.getG());
        assertEquals(0, color.getB());
    }

    /**
     * Test of interpolate method, of class Color.
     */
    @Test
    public void testInterpolate() {
        // step / nbSteps = 0 -> Should return the starting Color
        Color start = new Color(125, 43, 23, 162);
        Color end = new Color(23, 125, 67, 122);
        int step = 0;
        int nbStep = 10;

        Color result = Color.interpolate(start, end, step, nbStep);
        assertTrue(start.equals(result));

        // step / nbSteps = 1 -> Should return ending Color
        step = 10;
        result = Color.interpolate(start, end, step, nbStep);
        assertTrue(end.equals(result));

        // step / nbSteps = 0.5 -> Should return the color in the middle between the starting and ending Colors
        start = new Color(255, 0, 0, 255);
        end = new Color(0, 0, 255, 255);
        step = 5;
        result = Color.interpolate(start, end, step, nbStep);
        System.out.println(result .toString());
        Color expectedResult = new Color(128, 0, 128, 255);
        assertTrue(result.equals(expectedResult));
    }

    /**
     * Test of intToColor method, of class Color.
     */
    @Test
    public void testIntToColor() {
        // Same integer -> Should return the same Color
        int colorInteger = 0;
        Color color1 = Color.intToColor(colorInteger);
        Color color2 = Color.intToColor(colorInteger);
        assertTrue(color1.isColorValid());
        assertTrue(color2.isColorValid());
        assertTrue(color1.equals(color2));

        colorInteger = 120;
        color1 = Color.intToColor(colorInteger);
        color2 = Color.intToColor(colorInteger);
        assertTrue(color1.isColorValid());
        assertTrue(color2.isColorValid());
        assertTrue(color1.equals(color2));

        // Different integers -> Should return different Colors
        colorInteger = 0;
        int secondColorInteger = 1;
        color1 = Color.intToColor(colorInteger);
        color2 = Color.intToColor(secondColorInteger);
        assertTrue(color1.isColorValid());
        assertTrue(color2.isColorValid());
        assertFalse(color1.equals(color2));

        colorInteger = 67;
        secondColorInteger = 183;
        color1 = Color.intToColor(colorInteger);
        color2 = Color.intToColor(secondColorInteger);
        assertTrue(color1.isColorValid());
        assertTrue(color2.isColorValid());
        assertFalse(color1.equals(color2));
    }

    /**
     * Test of toString method, of class Color.
     */
    @Test
    public void testToString() {
        Color color = new Color(12, 52, 243, 2);
        String result = color.toString();
        String expectedResult = "(12, 52, 243, 2)";
        assertTrue(result.equals(expectedResult));

        color = new Color(124, 100, 2, 67);
        result = color.toString();
        expectedResult = "(124, 100, 2, 67)";
        assertTrue(result.equals(expectedResult));
    }

    /**
     * Test of hashCode method, of class Color.
     */
    @Test
    public void testHashCode() {
        // Same Colors -> Should get the same integer
        Color color1 = new Color(0, 0, 0, 0);
        Color color2 = new Color(0, 0, 0, 0);
        assertEquals(color1.hashCode(), color2.hashCode());

        color1 = new Color(63, 67, 13, 255);
        color2 = new Color(63, 67, 13, 255);
        assertEquals(color1.hashCode(), color2.hashCode());

        // Different Colors -> Should get different integers
        color2 = new Color(62, 67, 13, 255);
        assertNotEquals(color1.hashCode(), color2.hashCode());

        color2 = new Color(63, 68, 13, 255);
        assertNotEquals(color1.hashCode(), color2.hashCode());

        color2 = new Color(63, 67, 83, 255);
        assertNotEquals(color1.hashCode(), color2.hashCode());

        color2 = new Color(63, 67, 13, 0);
        assertNotEquals(color1.hashCode(), color2.hashCode());
    }

    /**
     * Test of equals method, of class Color.
     */
    @Test
    public void testEquals() {
        // For each test, we check that they are equal in both way around
        
        // Same Colors
        Color color1 = new Color(0, 0, 0, 0);
        Color color2 = new Color(0, 0, 0, 0);
        assertTrue(color1.equals(color2));
        assertTrue(color2.equals(color1));

        color1 = new Color(63, 67, 13, 255);
        color2 = new Color(63, 67, 13, 255);
        assertTrue(color1.equals(color2));
        assertTrue(color2.equals(color1));

        // Different Colors -> Should get different integers
        color2 = new Color(62, 67, 13, 255);
        assertFalse(color1.equals(color2));
        assertFalse(color2.equals(color1));

        color2 = new Color(63, 68, 13, 255);
        assertFalse(color1.equals(color2));
        assertFalse(color2.equals(color1));

        color2 = new Color(63, 67, 83, 255);
        assertFalse(color1.equals(color2));
        assertFalse(color2.equals(color1));

        color2 = new Color(63, 67, 13, 0);
        assertFalse(color1.equals(color2));
        assertFalse(color2.equals(color1));
    }

    /**
     * Test of isColorValid method, of class Color.
     */
    @Test
    public void testIsColorValid() {
        // Valid Color (all components between 0 and 255)
        Color color = new Color(0, 0, 0, 0);
        assertTrue(color.isColorValid());

        color = new Color(153, 12, 163, 243);
        assertTrue(color.isColorValid());

        color = new Color(255, 255, 255, 255);
        assertTrue(color.isColorValid());

        // Invalid Colors
        color = new Color(-1, 0, 0, 0);
        assertFalse(color.isColorValid());

        color = new Color(0, -1, 0, 0);
        assertFalse(color.isColorValid());

        color = new Color(0, 0, -1, 0);
        assertFalse(color.isColorValid());

        color = new Color(0, 0, 0, -1);
        assertFalse(color.isColorValid());

        color = new Color(256, 0, 0, 0);
        assertFalse(color.isColorValid());

        color = new Color(0, 256, 0, 0);
        assertFalse(color.isColorValid());

        color = new Color(0, 0, 256, 0);
        assertFalse(color.isColorValid());

        color = new Color(0, 0, 0, 256);
        assertFalse(color.isColorValid());

        color = new Color(-37, 245, 300, 0);
        assertFalse(color.isColorValid());
    }

}
