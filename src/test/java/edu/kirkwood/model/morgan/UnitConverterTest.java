package edu.kirkwood.model.morgan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.kirkwood.model.morgan.DistanceUnit;
import edu.kirkwood.model.morgan.UnitConverter;

/**
 * Unit tests for {@link UnitConverter}.
 * <p>
 * AI CITATION:  was drafted some help from Claude
 * (Anthropic). .
 * <p>
 * The conversion factor under test is 1 km = 0.621371 mi.
 */
class UnitConverterTest {

    private UnitConverter converter;

    @BeforeEach
    void setUp() {
        converter = new UnitConverter();
    }

    // ---------- kilometers ----------

    /* Test that converting kilometers to kilometers returns the same value */
    @Test
    void convert_kilometersReturnsTheSameValue() {
        double result = converter.convert(100.0, DistanceUnit.KILOMETERS);
        assertEquals(100.0, result, 1e-9);
    }

    /* Test that converting kilometers keeps the decimal part */
    @Test
    void convert_kilometersKeepsDecimals() {
        assertEquals(343.55, converter.convert(343.55, DistanceUnit.KILOMETERS), 1e-9);
    }

    // ---------- miles ----------

    /* Test that converting 1 kilometer to miles uses the standard factor */
    @Test
    void convert_oneKilometerToMiles() {
        UnitConverter converter = new UnitConverter();
        double result = converter.convert(1.0, DistanceUnit.MILES);
        assertEquals(0.621371, result, 1e-4);
    }

    /* Test that converting 10 kilometers to miles uses the standard factor */
    @Test
    void convert_tenKilometersToMiles() {
        UnitConverter converter = new UnitConverter();
        double result = converter.convert(10.0, DistanceUnit.MILES);
        assertEquals(6.21371, result, 1e-4);
    }

    /* Test that converting 100 kilometers to miles uses the standard factor */
    @Test
    void convert_hundredKilometersToMiles() {
        UnitConverter converter = new UnitConverter();
        double result = converter.convert(100.0, DistanceUnit.MILES);
        assertEquals(62.1371, result, 1e-4);
    }

    /* Test that converting 1000 kilometers to miles uses the standard factor */
    @Test
    void convert_thousandKilometersToMiles() {
        UnitConverter converter = new UnitConverter();
        double result = converter.convert(1000.0, DistanceUnit.MILES);
        assertEquals(621.371, result, 1e-4);
    }

    /* Test that converting half a kilometer to miles uses the standard factor */
    @Test
    void convert_halfKilometerToMiles() {
        UnitConverter converter = new UnitConverter();
        double result = converter.convert(0.5, DistanceUnit.MILES);
        assertEquals(0.3106855, result, 1e-4);
    }

    /* Test that converting a marathon distance in kilometers gives the correct miles */
    @Test
    void convert_marathonDistanceInMiles() {
        UnitConverter converter = new UnitConverter();
        double result = converter.convert(42.195, DistanceUnit.MILES);
        assertEquals(26.219, result, 1e-3);
    }

    /* Test that the miles result is smaller than the kilometers input */
    @Test
    void convert_milesResultIsSmallerThanKilometers() {
        double km = 250.0;
        double miles = converter.convert(km, DistanceUnit.MILES);
        assertTrue(miles < km);
    }

    // ---------- edge values ----------

    /* Test that zero stays zero in both units */
    @Test
    void convert_zeroStaysZeroInBothUnits() {
        assertEquals(0.0, converter.convert(0.0, DistanceUnit.KILOMETERS), 1e-9);
        assertEquals(0.0, converter.convert(0.0, DistanceUnit.MILES), 1e-9);
    }

    /* Test that the converter handles the largest possible Earth distance */
    @Test
    void convert_handlesTheLargestPossibleEarthDistance() {
        // Pole to pole is about 20015 km, roughly 12437 miles.
        assertEquals(12436.8, converter.convert(20015.087, DistanceUnit.MILES), 0.1);
    }

    // ---------- invalid input ----------

    /* Test that the converter throws for negative distance */
    @Test
    void convert_throwsForNegativeDistance() {
        assertThrows(IllegalArgumentException.class,
                () -> converter.convert(-1.0, DistanceUnit.MILES));
    }

    /* Test that the converter throws for NaN */
    @Test
    void convert_throwsForNaN() {
        assertThrows(IllegalArgumentException.class,
                () -> converter.convert(Double.NaN, DistanceUnit.KILOMETERS));
    }

    /* Test that the converter throws for infinity */
    @Test
    void convert_throwsForInfinity() {
        assertThrows(IllegalArgumentException.class,
                () -> converter.convert(Double.POSITIVE_INFINITY, DistanceUnit.MILES));
    }

    /* Test that the converter throws for null unit */
    @Test
    void convert_throwsForNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> converter.convert(10.0, null));
    }
}