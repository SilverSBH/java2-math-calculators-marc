package edu.kirkwood.controller.Jade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the formatting behavior of the DecayCalculator controller.
 * Verifies the cutoff between decimal and scientific notation.
 *
 * AI Assistance: I used ChatGPT to help organize the tests, improve
 * comments and Javadocs, and troubleshoot failing tests.
 */
public class DecayCalculatorTest {

    // -------------------- Format Validation Tests --------------------

    /**
     * Tests that a value exactly at the cutoff of 0.0001
     * is displayed in regular decimal notation.
     */
    @Test
    void formatResultAtCutoffUsesDecimalNotation() {
        // Arrange
        double value = 0.0001;
        String expected = "0.0001";

        // Act
        String actual = DecayCalculator.formatResult(value);

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests that a value smaller than the cutoff of 0.0001
     * is displayed using scientific notation.
     */
    @Test
    void formatResultBelowCutoffUsesScientificNotation() {
        // Arrange
        double value = 0.00009;
        String expected = "9E-5";

        // Act
        String actual = DecayCalculator.formatResult(value);

        // Assert
        assertEquals(expected, actual);
    }
}