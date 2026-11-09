package edu.kirkwood.controller.leesonj;

import edu.kirkwood.model.leesonj.DiePool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProbabilityCalculatorTest {

    @Test
    void parseDiePoolTest() {
        // Assemble
        String str1 = "3d6";
        String str2 = "1d10";
        String str3 = "4d100";

        // Act
        DiePool result1 = ProbabilityCalculator.parseDiePool(str1);
        DiePool result2 = ProbabilityCalculator.parseDiePool(str2);
        DiePool result3 = ProbabilityCalculator.parseDiePool(str3);


        // Assert
        assertEquals(3, result1.getDice());
        assertEquals(6, result1.getSides());
        assertEquals(1, result2.getDice());
        assertEquals(10, result2.getSides());
        assertEquals(4, result3.getDice());
        assertEquals(100, result3.getSides());
    }

    @Test
    void parseDiePoolTestCapitalD() {
        // Assemble
        String str1 = "8D4";

        // Act
        DiePool result1 = ProbabilityCalculator.parseDiePool(str1);

        // Assert
        assertEquals(8, result1.getDice());
        assertEquals(4, result1.getSides());
    }

    @Test
    void parseDiePoolZeroes() {
        // Assemble
        String str1 = "0d4";
        String result1 = "A die pool must have dice.";
        String str2 = "1d0";
        String result2 = "Can't have dice without sides.";


        // Act and Assert
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str1));
        assertEquals(result1, e1.getMessage());
        Exception e2 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str2));
        assertEquals(result2, e2.getMessage());
    }

    @Test
    void parseDiePoolBadDiceValue() {
        // Assemble
        String str1 = "Ad4"; // Alpha
        String parseDiceFailure = "Unable to parse a valid number of dice, please enter a positive integer. (You entered: %s)";
        String str2 = "1.0d4"; // Decimal
        String str3 = "-1d6"; // Negative
        String str4 = "%d8"; // Symbol

        // Act and Assert
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str1));
        assertEquals(String.format(parseDiceFailure, "a"), e1.getMessage());
        Exception e2 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str2));
        assertEquals(String.format(parseDiceFailure, "1.0"), e2.getMessage());
        Exception e3 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str3));
        assertEquals(String.format(parseDiceFailure, "-1"), e3.getMessage());
        Exception e4 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str4));
        assertEquals(String.format(parseDiceFailure, "%"), e4.getMessage());

    }
    @Test
    void parseDiePoolTooManyDice() {
        // Assemble
        String str1 = "100d10"; // Number too large (2-digit max)
        String tooManyDiceFailure = "Too many dice, please enter a smaller number (less than 100).";

        // Act and Assert
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str1));
        assertEquals(tooManyDiceFailure, e1.getMessage());

        // Assemble
        String str2 = "99d8";

        // Act
        DiePool result2 = ProbabilityCalculator.parseDiePool(str2);

        // Assert
        assertEquals(99, result2.getDice());
        assertEquals(8, result2.getSides());

    }

    @Test
    void parseDiePoolNoDice() {
        // Assemble
        String str1 = "d10";
        String badDiceFailure = "Unable to parse a valid number of dice, please enter a positive integer. (You entered: %s)";

        // Act and Assert
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str1));
        assertEquals(String.format(badDiceFailure, ""), e1.getMessage());

    }

    @Test
    void parseDiePoolNoSides() {
        // Assemble
        String str1 = "4d";
        String tooManyDiceFailure = "Unable to parse a valid number of sides, please enter a positive integer.";

        // Act and Assert
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str1));
        assertEquals(tooManyDiceFailure, e1.getMessage());

    }


    @Test
    void parseDiePoolBadSidesValue() {
        // Assemble
        String str1 = "1dA"; // Alpha
        String parseSidesFailure = "Unable to parse a valid number of sides, please enter a positive integer. (You entered: %s)";
        String str2 = "1d6.1"; // Decimal
        String str3 = "1d-14"; // Negative
        String str4 = "1d*"; // Symbol

        // Act and Assert
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str1));
        assertEquals(String.format(parseSidesFailure, "a"), e1.getMessage());
        Exception e2 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str2));
        assertEquals(String.format(parseSidesFailure, "6.1"), e2.getMessage());
        Exception e3 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str3));
        assertEquals(String.format(parseSidesFailure, "-14"), e3.getMessage());
        Exception e4 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str4));
        assertEquals(String.format(parseSidesFailure, "*"), e4.getMessage());


    }

    @Test
    void parseDiePoolTooManySides() {
        // Assemble
        String str1 = "1d100000001"; // Too Many Digits
        String tooManySidesFailure = "Too many sides, please enter a smaller number (max 100000000).";

        // Act and Assert
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str1));
        assertEquals(tooManySidesFailure, e1.getMessage());

        // Assemble
        String str2 = "1d100000000";

        // Act
        DiePool result2 = ProbabilityCalculator.parseDiePool(str2);

        // Assert
        assertEquals(1, result2.getDice());
        assertEquals(100000000, result2.getSides());


    }
    @Test
    void parseDiePoolNoSeparator() {
        // Assemble
        String str1 = "1 3 4";
        String noSeparatorFailure = "Unable to find the 'd' separator.";

        // Act and Assert
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str1));
        assertEquals(noSeparatorFailure, e1.getMessage());
    }
    @Test
    void parseDiePoolTooManySeparators() {
        // Assemble
        String str1 = "1d3d4";
        String noSeparatorFailure = "Found too many 'd' separators.";

        // Act and Assert
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> ProbabilityCalculator.parseDiePool(str1));
        assertEquals(noSeparatorFailure, e1.getMessage());
    }

}