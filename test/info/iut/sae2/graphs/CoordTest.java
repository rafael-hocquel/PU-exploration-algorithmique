package info.iut.sae2.graphs;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author temet
 */
public class CoordTest {

    /**
     * Test of constructors of the class Coord.
     */
    @Test
    public void testConstructors() {
        // Empty Constructor -> x and y should be set to 0.
        Coord coord = new Coord();
        assertEquals(0., coord.getX(), 0.01);
        assertEquals(0., coord.getY(), 0.01);

        // Custom values
        coord = new Coord(22.3, -43.67);
        assertEquals(22.3, coord.getX(), 0.01);
        assertEquals(-43.67, coord.getY(), 0.01);
    }

    /**
     * Test of getX method, of class Coord.
     */
    @Test
    public void testGetX() {
        // x = 22.
        Coord coord = new Coord(22., 43.);
        assertEquals(22., coord.getX(), 0.01);

        // x = -36.5
        coord = new Coord(-36.5, 12.);
        assertEquals(-36.5, coord.getX(), 0.01);
    }

    /**
     * Test of getY method, of class Coord.
     */
    @Test
    public void testGetY() {
        // y = 43.
        Coord coord = new Coord(22., 43.);
        assertEquals(43., coord.getY(), 0.01);

        // y = 12.
        coord = new Coord(-36.5, 12.2);
        assertEquals(12.2, coord.getY(), 0.01);
    }

    /**
     * Test of setX method, of class Coord.
     */
    @Test
    public void testSetX() {
        Coord coord = new Coord();

        // x = 0. -> 67.67
        coord.setX(67.67);
        assertEquals(67.67, coord.getX(), 0.01);
        assertEquals(0, coord.getY(), 0.01);

        // x = 67.67 -> -3.4
        coord.setX(-3.4);
        assertEquals(-3.4, coord.getX(), 0.01);
        assertEquals(0, coord.getY(), 0.01);
    }

    /**
     * Test of setY method, of class Coord.
     */
    @Test
    public void testSetY() {
        Coord coord = new Coord();

        // y = 0. -> 432.
        coord.setY(432.);
        assertEquals(0, coord.getX(), 0.01);
        assertEquals(432., coord.getY(), 0.01);

        // y = 432. -> -3.9
        coord.setY(-3.9);
        assertEquals(0, coord.getX(), 0.01);
        assertEquals(-3.9, coord.getY(), 0.01);
    }

    /**
     * Test of dist method, of class Coord.
     */
    @Test
    public void testDist() {
        // Basic Positive and Negative values
        Coord coord1 = new Coord();
        Coord coord2 = new Coord(10., 10.);

        // sqrt((-10)^2 + (-10)^2) = 14.1421356237
        double expectedDistance = 14.1421356237;

        assertEquals(coord1.dist(coord2), expectedDistance, 0.01);

        coord2 = new Coord(-10., -10.);
        assertEquals(coord1.dist(coord2), expectedDistance, 0.01);

        // Both positive and negative values
        coord1 = new Coord(67., -42.1);
        coord2 = new Coord(-12.24, -10.);

        // sqrt((67 + 12.24)^2 + (-42.1 + 10)^2) = 85.4949565764
        expectedDistance = 85.4949565764;
        assertEquals(coord1.dist(coord2), expectedDistance, 0.01);

        // Distance betwen a Coor and itself -> Should be 0
        assertEquals(0., coord1.dist(coord1), 0.01);
    }

    /**
     * Test of norm method, of class Coord.
     */
    @Test
    public void testNorm() {
        // As the norm of coord 1 should be equal to the distance between origin (0., 0.) and coord1
        // we can, for each test, check if the norm of coord1 is equal to coord1.distance(new Coord())
        // this allows to reinfoce both tests for the distance() and norm() methods
        Coord origin = new Coord();

        // Distance between origin and origin -> Should be 0
        Coord coord1 = new Coord();
        double expectedResult = 0.;
        assertEquals(expectedResult, coord1.norm(), 0.01);
        assertEquals(coord1.dist(origin), coord1.norm(), 0.01);

        // Random values distance
        coord1 = new Coord(-67.67, 49.3);
        // sqrt((-67.67)^2 + (49.3)^2) =  83.7240640437
        expectedResult = 83.7240640437;
        assertEquals(expectedResult, coord1.norm(), 0.01);
        assertEquals(coord1.dist(origin), coord1.norm(), 0.01);

        coord1 = new Coord(1642., 75.16);
        // sqrt((1642)^2 + (75.16)^2) = 1643.71926605
        expectedResult = 1643.71926605;
        assertEquals(expectedResult, coord1.norm(), 0.01);
        assertEquals(coord1.dist(origin), coord1.norm(), 0.01);
    }

    /**
     * Test of mult method, of class Coord.
     */
    @Test
    public void testMult_double() {
        // In the test function, we also use the .equals() methods, same as before, 
        // this helps check that both of the equals and mult functions are working as intended
        // in case of an error in the tests, we can still test methods individually

        // Multiply by 0 -> Should be 0
        Coord coord = new Coord(49.3, 5.);
        Coord expectedResult = new Coord();
        coord.mult(0.);
        assertTrue(coord.equals(expectedResult));

        // Multiply by 2 -> Should double both values
        coord = new Coord(49.3, 5.);
        expectedResult = new Coord(98.6, 10.);
        coord.mult(2.);
        assertTrue(coord.equals(expectedResult));

        // Negative Values
        coord = new Coord(-465.2, -2);
        expectedResult = new Coord(-3256.4, -14.);
        coord.mult(7.);
        assertTrue(coord.equals(expectedResult));

        // Both positive and negative values
        coord = new Coord(-465.2, 21.4);
        expectedResult = new Coord(-4652., 214.);
        coord.mult(10.);
        assertTrue(coord.equals(expectedResult));

        // Negative Multiplier -> All results should be inverted
        coord = new Coord(49.3, 5.);
        expectedResult = new Coord();
        coord.mult(-0.);
        assertTrue(coord.equals(expectedResult));

        coord = new Coord(49.3, 5.);
        expectedResult = new Coord(-98.6, -10.);
        coord.mult(-2.);
        assertTrue(coord.equals(expectedResult));

        coord = new Coord(-465.2, -2);
        expectedResult = new Coord(3256.4, 14.);
        coord.mult(-7.);
        assertTrue(coord.equals(expectedResult));

        coord = new Coord(-465.2, 21.4);
        expectedResult = new Coord(4652., -214.);
        coord.mult(-10.);
        assertTrue(coord.equals(expectedResult));
    }

    /**
     * Test of add method, of class Coord.
     */
    @Test
    public void testAdd_double_double() {
        // Starting from Origin -> x and y should be equal to the values we add
        Coord coord = new Coord();
        coord.add(0., 0.);
        Coord expectedResult = new Coord(0., 0.0);
        assertEquals(coord.getX(), expectedResult.getX(), 0.01);
        assertEquals(coord.getY(), expectedResult.getY(), 0.01);

        coord = new Coord();
        coord.add(73., 49.3);
        expectedResult = new Coord(73., 49.3);
        assertEquals(coord.getX(), expectedResult.getX(), 0.01);
        assertEquals(coord.getY(), expectedResult.getY(), 0.01);

        // Adding Positive Values
        coord = new Coord(73.1, 49.6);
        coord.add(465., 761.54);
        expectedResult = new Coord(538.1, 811.14);
        assertEquals(coord.getX(), expectedResult.getX(), 0.01);
        assertEquals(coord.getY(), expectedResult.getY(), 0.01);

        // Adding Negative Values
        coord = new Coord(37.5, 94.3);
        coord.add(-2.4, -76.5);
        expectedResult = new Coord(35.1, 17.8);
        assertEquals(coord.getX(), expectedResult.getX(), 0.01);
        assertEquals(coord.getY(), expectedResult.getY(), 0.01);

        // Adding both Positive and Negative Values
        coord = new Coord(37.5, 94.3);
        coord.add(2.4, -76.5);
        expectedResult = new Coord(39.9, 17.8);
        assertEquals(coord.getX(), expectedResult.getX(), 0.01);
        assertEquals(coord.getY(), expectedResult.getY(), 0.01);

        // Adding the opposite of the coordinated -> Should get Origin
        coord = new Coord(37.5, -94.3);
        coord.add(-37.5, 94.3);
        expectedResult = new Coord();
        assertEquals(coord.getX(), expectedResult.getX(), 0.01);
        assertEquals(coord.getY(), expectedResult.getY(), 0.01);
    }

    /**
     * Test of add method, of class Coord.
     */
    @Test
    public void testAdd_Coord() {

        // Starting from Origin -> x and y should be equals to the values we add
        Coord coord = new Coord();
        Coord coordsToAdd = new Coord(0., 0.);
        coord.add(coordsToAdd);
        Coord expectedResult = new Coord(0., 0.0);
        assertEquals(coord.getX(), expectedResult.getX(), 0.01);
        assertEquals(coord.getY(), expectedResult.getY(), 0.01);

        coord = new Coord();
        coordsToAdd = new Coord(73., 49.3);
        coord.add(coordsToAdd);
        expectedResult = new Coord(73., 49.3);
        assertEquals(coord.getX(), expectedResult.getX(), 0.01);
        assertEquals(coord.getY(), expectedResult.getY(), 0.01);

        // Adding Positive Values
        coord = new Coord(73.1, 49.6);
        coordsToAdd = new Coord(465., 761.54);
        coord.add(coordsToAdd);
        expectedResult = new Coord(538.1, 811.14);
        assertEquals(coord.getX(), expectedResult.getX(), 0.01);
        assertEquals(coord.getY(), expectedResult.getY(), 0.01);

        // Adding Negative Values
        coord = new Coord(37.5, 94.3);
        coordsToAdd = new Coord(-2.4, -76.5);
        coord.add(coordsToAdd);
        expectedResult = new Coord(35.1, 17.8);
        System.out.println(coord.toString());
        assertEquals(coord.getX(), expectedResult.getX(), 0.01);
        assertEquals(coord.getY(), expectedResult.getY(), 0.01);

        // Adding both Positive and Negative Values
        coord = new Coord(37.5, 94.3);
        coordsToAdd = new Coord(2.4, -76.5);
        coord.add(coordsToAdd);
        expectedResult = new Coord(39.9, 17.8);
        assertEquals(coord.getX(), expectedResult.getX(), 0.01);
        assertEquals(coord.getY(), expectedResult.getY(), 0.01);

        // Adding the opposite of the coordinated -> Should get Origin
        coord = new Coord(37.5, -94.3);
        coordsToAdd = new Coord(-37.5, 94.3);
        coord.add(coordsToAdd);
        expectedResult = new Coord();
        assertEquals(coord.getX(), expectedResult.getX(), 0.01);
        assertEquals(coord.getY(), expectedResult.getY(), 0.01);

    }

    /**
     * Test of add method, of class Coord.
     */
    @Test
    public void testAdd_Coord_Coord() {
        // Same as before, for each case, we can check that add(coord1, coord2) is equal to add(coord2, coord1)

        // Adding a Coord to the origin -> Should be the Coord which isn't the origin
        Coord coord1 = new Coord();
        Coord coord2 = new Coord(73.2, 49.3);
        Coord result = Coord.add(coord1, coord2);
        Coord expectedResult = new Coord(coord2);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);
        result = Coord.add(coord2, coord1);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);

        // Random Positive Values
        coord1 = new Coord(465.1, 1.);
        coord2 = new Coord(73.2, 49.3);
        result = Coord.add(coord1, coord2);
        expectedResult = new Coord(538.3, 50.3);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);
        result = Coord.add(coord2, coord1);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);

        // Random Negative Values
        coord1 = new Coord(-465.1, -1.);
        coord2 = new Coord(-73.2, -49.3);
        result = Coord.add(coord1, coord2);
        expectedResult = new Coord(-538.3, -50.3);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);
        result = Coord.add(coord2, coord1);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);

        // Both Positive and Negative Values
        coord1 = new Coord(-465.1, 1.);
        coord2 = new Coord(73.2, -49.3);
        result = Coord.add(coord1, coord2);
        expectedResult = new Coord(-391.9, -48.3);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);
        result = Coord.add(coord2, coord1);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);

        // Add a coordinate and it's opposite -> Should be 0
        coord1 = new Coord(-465.1, 1.);
        coord2 = new Coord(465.1, -1.);
        result = Coord.add(coord1, coord2);
        expectedResult = new Coord(0., 0.);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);
        result = Coord.add(coord2, coord1);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);
    }

    /**
     * Test of minus method, of class Coord. //TODO
     */
    @Test
    public void testMinus() {
        // Removing a Coord to the origin -> Should be the opposite of the coord we remove
        Coord coord1 = new Coord();
        Coord coord2 = new Coord(73.2, 49.3);
        Coord result = Coord.minus(coord1, coord2);
        Coord expectedResult = new Coord(-73.2, -49.3);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);

        // Random Positive Values
        coord1 = new Coord(465.1, 1.);
        coord2 = new Coord(73.2, 49.3);
        result = Coord.minus(coord1, coord2);
        expectedResult = new Coord(391.9, -48.3);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);

        // Random Negative Values
        coord1 = new Coord(-465.1, -1.);
        coord2 = new Coord(-73.2, -49.3);
        result = Coord.minus(coord1, coord2);
        expectedResult = new Coord(-391.9, 48.3);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);

        // Both Positive and Negative Values
        coord1 = new Coord(-465.1, 1.);
        coord2 = new Coord(73.2, -49.3);
        result = Coord.minus(coord1, coord2);
        expectedResult = new Coord(-538.3, 50.3);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);

        // Removing coord1 to coord1 -> Should be 0
        coord1 = new Coord(465.1, 1.);
        result = Coord.minus(coord1, coord1);
        expectedResult = new Coord(0., 0.);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);

        coord1 = new Coord(-465.1, -1.);
        result = Coord.minus(coord1, coord1);
        expectedResult = new Coord(0., 0.);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);

        coord1 = new Coord(465.1, -1.);
        result = Coord.minus(coord1, coord1);
        expectedResult = new Coord(0., 0.);
        assertEquals(result.getX(), expectedResult.getX(), 0.01);
        assertEquals(result.getY(), expectedResult.getY(), 0.01);
    }

    /**
     * Test of mult method, of class Coord.
     */
    @Test
    public void testMult_Coord_double() {
        // Multiply by 0 -> Should be 0
        Coord coord = new Coord(49.3, 5.);
        Coord expectedResult = new Coord();
        Coord result = Coord.mult(coord, 0.);
        assertTrue(result.equals(expectedResult));

        // Multiply by 2 -> Should double both values
        coord = new Coord(49.3, 5.);
        expectedResult = new Coord(98.6, 10.);
        result = Coord.mult(coord, 2.);
        assertTrue(result.equals(expectedResult));

        // Negative Values
        coord = new Coord(-465.2, -2);
        expectedResult = new Coord(-3256.4, -14.);
        result = Coord.mult(coord, 7.);
        assertTrue(result.equals(expectedResult));

        // Both positive and negative values
        coord = new Coord(-465.2, 21.4);
        expectedResult = new Coord(-4652., 214.);
        result = Coord.mult(coord, 10.);
        assertTrue(result.equals(expectedResult));

        // Negative Multiplier -> All results should be inverted
        coord = new Coord(49.3, 5.);
        expectedResult = new Coord();
        result = Coord.mult(coord, -0.);
        assertTrue(result.equals(expectedResult));

        coord = new Coord(49.3, 5.);
        expectedResult = new Coord(-98.6, -10.);
        result = Coord.mult(coord, -2.);
        assertTrue(result.equals(expectedResult));

        coord = new Coord(-465.2, -2);
        expectedResult = new Coord(3256.4, 14.);
        result = Coord.mult(coord, -7.);
        assertTrue(result.equals(expectedResult));

        coord = new Coord(-465.2, 21.4);
        expectedResult = new Coord(4652., -214.);
        result = Coord.mult(coord, -10.);
        assertTrue(result.equals(expectedResult));
    }

    /**
     * Test of toString method, of class Coord.
     */
    @Test
    public void testToString() {
        Coord coord = new Coord();
        String result = coord.toString();
        String expectedResult = "(0.0, 0.0)";
        assertEquals(result, expectedResult);

        coord = new Coord(67.67, 49.3);
        result = coord.toString();
        expectedResult = "(67.67, 49.3)";
        assertEquals(result, expectedResult);
    }

    /**
     * Test of equals method, of class Coord.
     */
    @Test
    public void testEquals() {
        // For each test, we check that they are equal in both way around

        // Same Coordinates
        Coord coord1 = new Coord(0., 0.);
        Coord coord2 = new Coord(0., 0.);
        assertTrue(coord1.equals(coord2));
        assertTrue(coord2.equals(coord1));

        coord1 = new Coord(63.1, -67.5);
        coord2 = new Coord(63.1, -67.5);
        assertTrue(coord1.equals(coord2));
        assertTrue(coord2.equals(coord1));

        // Different Colors -> Should get different integers
        coord2 = new Coord(63.0, -67.5);
        assertFalse(coord1.equals(coord2));
        assertFalse(coord2.equals(coord1));

        coord2 = new Coord(63.1, -67.4);
        assertFalse(coord1.equals(coord2));
        assertFalse(coord2.equals(coord1));
    }

}
