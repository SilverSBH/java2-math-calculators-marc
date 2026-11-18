package edu.kirkwood.model.morgan;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.kirkwood.model.morgan.DistanceUnit;

/**
 * Unit tests for {@link DistanceUnit}.
 * <p>
 * AI CITATION: was drafted some help from Claude(Anthropic)
 * <p>
 * The enum has no behavior yet, so these tests guard the two constants the
 * design calls for. They pass as soon as the enum compiles.
 */
class DistanceUnitTest {

    /*
     * Test that values contains exactly KILOMETERS and MILES
     */
    @Test
    void values_containsExactlyKilometersAndMiles() {
        assertArrayEquals(
                new DistanceUnit[] { DistanceUnit.KILOMETERS, DistanceUnit.MILES },
                DistanceUnit.values());
    }

    /* Test that values contains exactly KILOMETERS and MILES */
    @Test
    void valueOf_findsEachConstantByName() {
        assertEquals(DistanceUnit.KILOMETERS, DistanceUnit.valueOf("KILOMETERS"));
        assertEquals(DistanceUnit.MILES, DistanceUnit.valueOf("MILES"));
    }

    /* Test that valueOf rejects unknown names */
    @Test
    void valueOf_rejectsUnknownNames() {
        assertThrows(IllegalArgumentException.class, () -> DistanceUnit.valueOf("FURLONGS"));
    }
}