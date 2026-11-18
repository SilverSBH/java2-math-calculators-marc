package edu.kirkwood.model.morgan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import edu.kirkwood.model.morgan.Coordinate;

/**
 * Unit tests for {@link Coordinate}.
 * <p>
 * AI CITATION: was drafted some help from Claude(Anthropic)
 * <p>
 * Expected values:
 * <ul>
 *   <li>One degree of arc on a 6371 km sphere is 111.194927 km.</li>
 *   <li>A quarter of the equator (90 degrees) is 10007.543398 km.</li>
 *   <li>Pole to pole (180 degrees) is 20015.086796 km.</li>
 *   <li>London to Paris and New York to Los Angeles are widely published
 *       reference distances, so those tests use a looser tolerance.</li>
 * </ul>
 */
class CoordinateTest {

    private static final double TIGHT = 0.01;
    private static final double DEGREE_KM = 111.194927;

    // ---------- constructor and getters ----------

    /* Test that the constructor stores the latitude and longitude correctly */
    @Test
    void constructor_storesLatitudeAndLongitude() {
        Coordinate c = new Coordinate(40.7128, -74.0060);
        assertEquals(40.7128, c.getLatitude(), 1e-9);
        assertEquals(-74.0060, c.getLongitude(), 1e-9);
    }

    /* Test that the constructor accepts out-of-range values without throwing */
    @Test
    void constructor_acceptsOutOfRangeValuesWithoutThrowing() {
        // Validation is isValid()'s job, so the app can re-prompt (see flowchart).
        Coordinate c = new Coordinate(500, -900);
        assertEquals(500, c.getLatitude(), 1e-9);
        assertEquals(-900, c.getLongitude(), 1e-9);
    }

    // ---------- isValid: good coordinates ----------

    /* Test that isValid() returns true for the equator / prime meridian point */
    @Test
    void isValid_equatorAndPrimeMeridianIsValid() {
        assertTrue(new Coordinate(0, 0).isValid());
    }

    /* Test that isValid() returns true for New York City */
    @Test
    void isValid_newYorkCityIsValid() {
        assertTrue(new Coordinate(40.7128, -74.0060).isValid());
    }

    /* Test that isValid() returns true for London */
    @Test
    void isValid_londonIsValid() {
        assertTrue(new Coordinate(51.5074, -0.1278).isValid());
    }

    /* Test that isValid() returns true at the north pole, east edge of longitude */
    @Test
    void isValid_northPoleEastEdgeIsValid() {
        assertTrue(new Coordinate(90, 180).isValid());
    }

    /* Test that isValid() returns true at the south pole, west edge of longitude */
    @Test
    void isValid_southPoleWestEdgeIsValid() {
        assertTrue(new Coordinate(-90, -180).isValid());
    }

    /* Test that isValid() returns true at the north pole, west edge of longitude */
    @Test
    void isValid_northPoleWestEdgeIsValid() {
        assertTrue(new Coordinate(90, -180).isValid());
    }

    /* Test that isValid() returns true at the south pole, east edge of longitude */
    @Test
    void isValid_southPoleEastEdgeIsValid() {
        assertTrue(new Coordinate(-90, 180).isValid());
    }

    // ---------- isValid: bad latitude ----------

    /* Test that isValid() returns false just above the maximum latitude */
    @Test
    void isValid_latitudeJustAboveMaxIsInvalid() {
        assertFalse(new Coordinate(90.0001, 0).isValid());
    }

    /* Test that isValid() returns false just below the minimum latitude */
    @Test
    void isValid_latitudeJustBelowMinIsInvalid() {
        assertFalse(new Coordinate(-90.0001, 0).isValid());
    }

    /* Test that isValid() returns false for latitude 91 */
    @Test
    void isValid_latitude91IsInvalid() {
        assertFalse(new Coordinate(91, 0).isValid());
    }

    /* Test that isValid() returns false for latitude -91 */
    @Test
    void isValid_latitudeNegative91IsInvalid() {
        assertFalse(new Coordinate(-91, 0).isValid());
    }

    /* Test that isValid() returns false for a wildly out-of-range latitude */
    @Test
    void isValid_latitude1000IsInvalid() {
        assertFalse(new Coordinate(1000, 0).isValid());
    }

    // ---------- isValid: bad longitude ----------

    /* Test that isValid() returns false just above the maximum longitude */
    @Test
    void isValid_longitudeJustAboveMaxIsInvalid() {
        assertFalse(new Coordinate(0, 180.0001).isValid());
    }

    /* Test that isValid() returns false just below the minimum longitude */
    @Test
    void isValid_longitudeJustBelowMinIsInvalid() {
        assertFalse(new Coordinate(0, -180.0001).isValid());
    }

    /* Test that isValid() returns false for longitude 181 */
    @Test
    void isValid_longitude181IsInvalid() {
        assertFalse(new Coordinate(0, 181).isValid());
    }

    /* Test that isValid() returns false for longitude -181 */
    @Test
    void isValid_longitudeNegative181IsInvalid() {
        assertFalse(new Coordinate(0, -181).isValid());
    }

    /* Test that isValid() returns false for a wildly out-of-range longitude */
    @Test
    void isValid_longitude360IsInvalid() {
        assertFalse(new Coordinate(0, 360).isValid());
    }

    // ---------- isValid: other bad input ----------

    /* Test that isValid() returns false when both values are out of range */
    @Test
    void isValid_returnsFalseWhenBothValuesAreOutOfRange() {
        assertFalse(new Coordinate(95, 200).isValid());
    }

    /* Test that isValid() returns false for NaN */
    @Test
    void isValid_returnsFalseForNaN() {
        assertFalse(new Coordinate(Double.NaN, 0).isValid());
        assertFalse(new Coordinate(0, Double.NaN).isValid());
    }

    /* Test that isValid() returns false for infinity */
    @Test
    void isValid_returnsFalseForInfinity() {
        assertFalse(new Coordinate(Double.POSITIVE_INFINITY, 0).isValid());
        assertFalse(new Coordinate(0, Double.NEGATIVE_INFINITY).isValid());
    }

    // ---------- computeDistance ----------

    /* Test that computeDistance() returns 0 for the same point */
    @Test
    void computeDistance_samePointIsZero() {
        Coordinate p = new Coordinate(40.7128, -74.0060);
        assertEquals(0.0, Coordinate.computeDistance(p, p), 1e-9);
    }

    /* Test that computeDistance() returns 0 for equal but separate objects */
    @Test
    void computeDistance_equalButSeparateObjectsIsZero() {
        Coordinate a = new Coordinate(12.5, 45.5);
        Coordinate b = new Coordinate(12.5, 45.5);
        assertEquals(0.0, Coordinate.computeDistance(a, b), 1e-9);
    }

    /* Test that computeDistance() for one degree of longitude at the equator */
    @Test
    void computeDistance_oneDegreeOfLongitudeAtEquator() {
        double d = Coordinate.computeDistance(new Coordinate(0, 0), new Coordinate(0, 1));
        assertEquals(DEGREE_KM, d, TIGHT);
    }

    /* Test that computeDistance() for one degree of latitude */
    @Test
    void computeDistance_oneDegreeOfLatitude() {
        double d = Coordinate.computeDistance(new Coordinate(0, 0), new Coordinate(1, 0));
        assertEquals(DEGREE_KM, d, TIGHT);
    }

    /* Test that computeDistance() for a quarter of the equator */
    @Test
    void computeDistance_quarterOfTheEquator() {
        double d = Coordinate.computeDistance(new Coordinate(0, 0), new Coordinate(0, 90));
        assertEquals(10007.543398, d, TIGHT);
    }

    /* Test that computeDistance() from pole to pole */
    @Test
    void computeDistance_poleToPole() {
        double d = Coordinate.computeDistance(new Coordinate(90, 0), new Coordinate(-90, 0));
        assertEquals(20015.086796, d, TIGHT);
    }

    /* Test that computeDistance() across the equator in the southern and northern hemispheres */
    @Test
    void computeDistance_acrossTheEquatorInTheSouthernAndNorthernHemispheres() {
        double d = Coordinate.computeDistance(new Coordinate(-45, 0), new Coordinate(45, 0));
        assertEquals(10007.543398, d, TIGHT);
    }

    /* Test that computeDistance() crossing the antimeridian takes the short way */
    @Test
    void computeDistance_crossingTheAntimeridianTakesTheShortWay() {
        // 179 E to 179 W is 2 degrees apart, not 358.
        double d = Coordinate.computeDistance(new Coordinate(0, 179), new Coordinate(0, -179));
        assertEquals(2 * DEGREE_KM, d, TIGHT);
    }

    /* Test that computeDistance() from London to Paris */
    @Test
    void computeDistance_londonToParis() {
        Coordinate london = new Coordinate(51.5074, -0.1278);
        Coordinate paris = new Coordinate(48.8566, 2.3522);
        assertEquals(343.5, Coordinate.computeDistance(london, paris), 1.0);
    }

    /* Test that computeDistance() from New York to Los Angeles */
    @Test
    void computeDistance_newYorkToLosAngeles() {
        Coordinate newYork = new Coordinate(40.7128, -74.0060);
        Coordinate losAngeles = new Coordinate(34.0522, -118.2437);
        assertEquals(3936.0, Coordinate.computeDistance(newYork, losAngeles), 5.0);
    }

    /* Test that computeDistance() is symmetric */
    @Test
    void computeDistance_isSymmetric() {
        Coordinate a = new Coordinate(51.5074, -0.1278);
        Coordinate b = new Coordinate(-33.8688, 151.2093);
        assertEquals(Coordinate.computeDistance(a, b), Coordinate.computeDistance(b, a), 1e-9);
    }

    /* Test that computeDistance() handles coordinates near the poles correctly */
    @Test
    void computeDistance_mirroredNegativeCoordinatesGiveTheSameDistance() {
        double positive = Coordinate.computeDistance(new Coordinate(10, 20), new Coordinate(30, 40));
        double negative = Coordinate.computeDistance(new Coordinate(-10, -20), new Coordinate(-30, -40));
        assertEquals(positive, negative, 1e-9);
    }

    /* Test that computeDistance() never returns a negative value */
    @Test
    void computeDistance_neverReturnsANegativeValue() {
        double d = Coordinate.computeDistance(new Coordinate(-60, 170), new Coordinate(60, -170));
        assertTrue(d >= 0);
    }

    /* Test that computeDistance() throws when the first point is null */
    @Test
    void computeDistance_throwsWhenFirstPointIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> Coordinate.computeDistance(null, new Coordinate(0, 0)));
    }

    /* Test that computeDistance() throws when the second point is null */
    @Test
    void computeDistance_throwsWhenSecondPointIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> Coordinate.computeDistance(new Coordinate(0, 0), null));
    }

    /* Test that computeDistance() throws when the first point is invalid */
    @Test
    void computeDistance_throwsWhenFirstPointIsInvalid() {
        Coordinate bad = new Coordinate(91, 0);
        assertThrows(IllegalArgumentException.class,
                () -> Coordinate.computeDistance(bad, new Coordinate(0, 0)));
    }

    /* Test that computeDistance() throws when the second point is invalid */
    @Test
    void computeDistance_throwsWhenSecondPointIsInvalid() {
        Coordinate bad = new Coordinate(0, 181);
        assertThrows(IllegalArgumentException.class,
                () -> Coordinate.computeDistance(new Coordinate(0, 0), bad));
    }

    /* Test that equals() returns true for the same object */
    @Test 
    void equals_sameObjectIsEqualToItself()
    {
        Coordinate c = new Coordinate(40.71,-74.00);
        assertTrue(c.equals(c));
    }

    /* Test that equals() returns true for separate objects with the same values */
    @Test
    void equals_seperateObjectsWithSameValuesAreEqual()
    {
        Coordinate c1 = new Coordinate(40.71,-74.00);
        Coordinate c2 = new Coordinate(40.71,-74.00);
        assertTrue(c1.equals(c2));
    }

    /* Test that equals() returns false for different latitude values */
    @Test 
    void equals_differentLatitudeIsNotEqual()
    {
        Coordinate a = new Coordinate(40.71,-74.00);
        Coordinate b = new Coordinate(40.72,-74.00);

        assertFalse(a.equals(b));
    }

    /* Test that equals() returns false for different longitude values */
    @Test 
    void equals_differentLongitudeIsNotEqual()
    {
        Coordinate a = new Coordinate(40.71,-74.00);
        Coordinate b = new Coordinate(40.71,-74.01);

        assertFalse(a.equals(b));
    }

    /* Test that equals() returns false for null values*/
    @Test 
    void equals_returnsFalseForNull()
    {
        Coordinate a = new Coordinate(40.71,-74.00);
        assertFalse(a.equals(null));
    }

    /* Test that equals() returns false for a different type */
    @Test 
    void equals_returnsFalseForADifferrentType()
    {
        Coordinate a = new Coordinate(40.71,-74.00);

        assertFalse(a.equals("Not a Coordinate"));
    }

    @Test 
    void hashCode_isEqualForEqualCoordinates()
    {
        Coordinate a = new Coordinate(42.55,-112.35);
        Coordinate b = new Coordinate(42.55,-112.35);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test 
    void hashCode_isDifferentForDifferentCoordinates()
    {
        Coordinate a = new Coordinate(42.55,-112.35);
        Coordinate b = new Coordinate(42.56,-112.36);
        assertNotEquals(a.hashCode(), b.hashCode());
    }

}