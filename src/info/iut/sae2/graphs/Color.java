package info.iut.sae2.graphs;

/**
 * Class used to represent rgb colors and perform operations on them.
 *
 * @author rbourqui
 */
public class Color {

    private int r, g, b, a;

    /**
     * Default constructor.
     */
    public Color() {
        this(0, 0, 0, 0);
    }

    /**
     * Copy constructor.
     *
     * @param c the Color to be copied
     */
    public Color(Color c) {
        this(c.r, c.g, c.b, c.a);
    }

    /**
     * Constructor with given red, green, blue and alpha channels.
     *
     * @param r red channel of the Color
     * @param g green channel of the Color
     * @param b blue channel of the Color
     * @param a alpha channel of the Color
     */
    public Color(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /**
     * Returns the red channel of the Color.
     *
     * @return the red channnel
     */
    public int getR() {
        return this.r;
    }

    /**
     * Returns the green channel of the Color.
     *
     * @return the green channnel
     */
    public int getG() {
        return this.g;
    }

    /**
     * Returns the blue channel of the Color.
     *
     * @return the blue channnel
     */
    public int getB() {
        return this.b;
    }

    /**
     * Returns the alpha channel of the Color.
     *
     * @return the alpha channnel
     */
    public int getA() {
        return this.a;
    }

    /**
     * Set the red channel of the Color to the given value.
     *
     * @param r the red channnel
     */
    public void setR(int r) {
        this.r = r;
    }

    /**
     * Set the green channel of the Color to the given value.
     *
     * @param g the green channnel
     */
    public void setG(int g) {
        this.g = g;
    }

    /**
     * Set the blue channel of the Color to the given value.
     *
     * @param b the blue channnel
     */
    public void setB(int b) {
        this.b = b;
    }

    /**
     * Set the alpha channel of the Color to the given value.
     *
     * @param a the alpha channnel
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * Returns a new Color result of a linear interpolation between the start
     * and end colors given the current step and the number of steps.
     *
     * @param start start color of the interpolation
     * @param end end color of the interpolation
     * @param step current step
     * @param nbSteps total number of steps
     * @return the interpolated color
     */
    public static Color interpolate(Color start, Color end, int step, int nbSteps) {
        float proggression = (float) step / nbSteps;
        
        int r = Math.round(start.r + (end.r - start.r) * proggression);
        int g = Math.round(start.g + (end.g - start.g) * proggression);
        int b = Math.round(start.b + (end.b - start.b) * proggression);
        int a = Math.round(start.a + (end.a - start.a) * proggression);
        
        
        return new Color(r, g, b, a);
    }

    /**
     * Returns a Color object corresponding to the conversion of the given hsv
     * color to rgb. Conversion function found on
     * https://stackoverflow.com/questions/7896280/converting-from-hsv-hsb-in-java-to-rgb-without-using-java-awt-color-disallowe
     * and adapted to the codebase.
     *
     * @param hue the hue component of the hsv color to convert
     * @param saturation the saturation component of the hsv color to convert
     * @param value the value component of the hsv color to convert
     * @return the Color object corresponding to the given hsv color
     */
    private static Color hsvToRgb(double hue, double saturation, double value) {
        double normalizedHue = hue / 360.0;
        int h = (int) (normalizedHue * 6);
        double f = normalizedHue * 6 - h;
        double p = value * (1 - saturation);
        double q = value * (1 - f * saturation);
        double t = value * (1 - (1 - f) * saturation);

        switch (h) {
            case 0 -> {
                return rgbToColor(value, t, p);
            }
            case 1 -> {
                return rgbToColor(q, value, p);
            }
            case 2 -> {
                return rgbToColor(p, value, t);
            }
            case 3 -> {
                return rgbToColor(p, q, value);
            }
            case 4 -> {
                return rgbToColor(t, p, value);
            }
            case 5 -> {
                return rgbToColor(value, p, q);
            }
            default ->
                throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + hue + ", " + saturation + ", " + value);
        }
    }

    /**
     * Helper function for Color.hsvToRgb(), returns a color object from rgb
     * values.
     *
     * @param r the red component from the rgb color
     * @param g the green component from the rgb color
     * @param b the blue component from the rgb color
     * @return a Color Object corresponding to the given r, g and blues values
     */
    private static Color rgbToColor(double r, double g, double b) {
        int rs = (int) (r * 256);
        int gs = (int) (g * 256);
        int bs = (int) (b * 256);
        return new Color(rs, gs, bs, 255);
    }

    /**
     * Function based on the golden angle principle (found on
     * https://fr.wikipedia.org/wiki/Angle_d%27or) to compute contrasting colors
     * from integers.
     *
     * @param colorInteger the integer-color to associate with a contrasting
     * color
     * @return the contrasting color from the given integer after applying the
     * golden angle principle
     */
    public static Color intToColor(int colorInteger) {
        double hue = (colorInteger * 137.5) % 360;
        return Color.hsvToRgb(hue, 0.8, 0.8);
    }

    /**
     * Returns the hashCode for the Color the method is applied on.
     *
     * @return hashcode of the color
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.r;
        hash = 59 * hash + this.g;
        hash = 59 * hash + this.b;
        hash = 59 * hash + this.a;
        return hash;
    }

    /**
     * Returns true if the given color and the color the method is applied on
     * are equal in values.
     *
     * @param obj the color to check equality with
     * @return true if the colors are equals in values or false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Color other = (Color) obj;
        if (this.r != other.r) {
            return false;
        }
        if (this.g != other.g) {
            return false;
        }
        if (this.b != other.b) {
            return false;
        }
        return this.a == other.a;
    }

    /**
     * Returns true if the Color the method is applied on is valid or false otherwise.
     * @return true if the color is valid or false otherwise
     */
    public boolean isColorValid() {
        if (this.getR() < 0 || this.getR() > 255) {
            return false;
        }

        if (this.getG() < 0 || this.getG() > 255) {
            return false;
        }

        if (this.getB() < 0 || this.getB() > 255) {
            return false;
        }

        if (this.getA() < 0 || this.getA() > 255) {
            return false;
        }
        
        return true;
    }

    /**
     * Returns a String representation of a Color Object (it's 3 red, green and
     * blue components).
     *
     * @return the String representation of the given Color Object
     */
    @Override
    public String toString() {
        return "(" + r + ", " + g + ", " + b + ", " + a + ")";
    }

}
