package edu.kirkwood.controller.zach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the BoosterPackCalculator class.
 * This class tests the getFilePath method of the BoosterPackCalculator class.
 */
class BoosterPackCalculatorTest {


    @Test
    void start() {
    }


    /**
     * Tests the getFilePath method of BoosterPackCalculator for valid choices.
     * Ensures that the correct file paths are returned for each valid choice.
     */
    @Test
    void testGetFilePath_ValidChoice() {
        assertEquals("data/DRI-Pokemon-Destined-Rivals.csv", BoosterPackCalculator.getFilePath(1));
        assertEquals("data/BLK-Pokemon-Black-Bolt.csv", BoosterPackCalculator.getFilePath(2));
        assertEquals("data/PRE-Pokemon-Prismatic-Evolutions.csv", BoosterPackCalculator.getFilePath(3));
        assertEquals("data/JTG-Pokemon-Journey-Together.csv", BoosterPackCalculator.getFilePath(4));
    }


    /**
     * Tests the getFilePath method of BoosterPackCalculator for invalid choices.
     * Ensures that an IllegalArgumentException is thrown for invalid choices.
     */
    @Test
    void testGetFilePath_InvalidChoiceThrowsException() {
        int invalidChoiceUpper = 5;
        int invalidChoiceLower = 0;

        IllegalArgumentException upperException = assertThrows(IllegalArgumentException.class, () -> {
            BoosterPackCalculator.getFilePath(invalidChoiceUpper);
        });
        assertEquals("Invalid choice for booster pack: " + invalidChoiceUpper, upperException.getMessage(), "Message should specify the invalid choice.");

        IllegalArgumentException lowerException = assertThrows(IllegalArgumentException.class, () -> {
            BoosterPackCalculator.getFilePath(invalidChoiceLower);
        });
        assertEquals("Invalid choice for booster pack: " + invalidChoiceLower, lowerException.getMessage(), "Message should specify the invalid choice.");
    }

}