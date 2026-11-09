package edu.kirkwood.controller.oleksiy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuadraticEquationCalculatorTest {

    @Test
    void convertFractionToDecimal() {
        // Arrange
        String fraction = "1/2";

        // Act
        double expected = 0.5;
        double actual = QuadraticEquationCalculator.convertFractionToDecimal(fraction);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void convertNegativeFractionToDecimal(){
        // Arrange
        String fraction = "-1/2";

        // Act
        double expected = -0.5;
        double actual = QuadraticEquationCalculator.convertFractionToDecimal(fraction);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void throwErrorWhenFractionHasMultipleDivisorSigns(){
        // Arrange
        String fraction = "1//2";

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> QuadraticEquationCalculator.convertFractionToDecimal(fraction));
    }

    @Test
    void convertFractionToDecimalWithExtraWhitespace() {
        // Arrange
        String extraWhitespacefraction = "   1/2   ";

        // Act
        double expected = 0.5;
        double actual = QuadraticEquationCalculator.convertFractionToDecimal(extraWhitespacefraction);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void convertFractionToRepeatingDecimal(){
        // Arrange
        String fraction = "1/3";

        // Act
        double expected = 0.3333333333333333;
        double actual = QuadraticEquationCalculator.convertFractionToDecimal(fraction);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void throwErrorIfFractionInputNull(){
        String fraction = "";

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> QuadraticEquationCalculator.convertFractionToDecimal(fraction));
    }

    @Test
    void throwErrorWhenDenomIsZero(){
        // Arrange
        String fraction = "1/0";

        // Act & Assert
        assertThrows(ArithmeticException.class, () -> QuadraticEquationCalculator.convertFractionToDecimal(fraction));
    }

    @Test
    void throwErrorWhenLetterOrSymbolInFraction(){
        // Arrange
        String fraction = "1/e";
        String fraction2 = "1/2*";

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> QuadraticEquationCalculator.convertFractionToDecimal(fraction));
        assertThrows(NumberFormatException.class, () -> QuadraticEquationCalculator.convertFractionToDecimal(fraction2));
    }
}